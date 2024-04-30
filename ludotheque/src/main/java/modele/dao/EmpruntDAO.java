
package modele.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modele.Emprunt;

/**
 * @clef_composé idAdherent + idJeuPhysique + dateEmprunt 
 */
public class EmpruntDAO extends DAO<Emprunt> {
	

	private static final String TABLE = "Emprunt";
	private static final String ID_ADHERENT = "idAdherent";
	private static final String ID_JEUPHYSIQUE = "idJeuPhysique";

	private static final String DATE_EMPRUNT = "dateEmprunt"; 
	private static final String DATE_RETOUR = "dateRetour";  
	
	private static final String WHERE_CLEF_PRIMAIRE = " WHERE " + ID_ADHERENT + " = ?, " + ID_JEUPHYSIQUE + " = ?, " + DATE_EMPRUNT + " = ?";
	
	/** Patron de conception Singleton
	 * 
	 */
	private static EmpruntDAO instance=null;

	public static EmpruntDAO getInstance(){
		if (instance==null){
			instance = new EmpruntDAO();
		}
		return instance;
	}

	private EmpruntDAO() {
		super();
	}


	// TODO adapter 
	@Override
	public boolean create(Emprunt emprunt) {
		boolean succes=true;
		try {
			
			// commentaires de Charles :
			// j'ai éjà les infos sur les tables JeuPhysique et Adherent, 
			// donc je les mets juste en brut, attention, c'est des clés étrangères,
			// classe à finir !!! 
			String requete = "INSERT INTO "+TABLE+" ("+ID_JEUPHYSIQUE+", "+ID_ADHERENT+", "+DATE_RETOUR+", "+DATE_EMPRUNT+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			// on ex�cute la mise � jour
			pst.executeUpdate();
			pst.setInt(1, emprunt.getIdJeuPhysique());
			pst.setInt(2, emprunt.getIdAdherent());
			Date dateRetour = Date.valueOf(emprunt.getDateRetour().toLocalDate());
			pst.setDate(3, dateRetour);
			Date dateEmprunt = Date.valueOf(emprunt.getDateEmprunt().toLocalDate());
			pst.setDate(4, dateEmprunt);
			
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				emprunt.setIdJeuPhysique(rs.getInt(1));
			}
			donnees.put(this.getClefDonne(emprunt), emprunt);

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}

	// commentaires de Charles : 
// en dessous c'est la partie que j'ai copié du code de Thibault, 
// important de modifier pour que ça colle à mon code à moi, je boss sur es clés étrangères,
// Thibault a bossé sur une clé primaire, donc bien différent
	@Override
	public boolean delete(Emprunt emprunt) {
		boolean succes = true;
		try {
			int idAdherent = emprunt.getIdAdherent();
			int idJeu = emprunt.getIdJeuPhysique();
			Date dateEmprunt = Date.valueOf(emprunt.getDateEmprunt().toLocalDate());
			String requete = "DELETE FROM "+TABLE+ WHERE_CLEF_PRIMAIRE;
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idAdherent);
			pst.setInt(2, idJeu);
			pst.setDate(3, dateEmprunt);

			pst.executeUpdate();
			donnees.remove(this.getClefDonne(emprunt));
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean update(Emprunt emprunt) {
		boolean succes=true;

		try {
			String requete = "UPDATE "+TABLE+" SET "+ DATE_RETOUR +" = ? " + WHERE_CLEF_PRIMAIRE;
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			// fonction différente donc refaire les pst
			pst.setInt(1, emprunt.getIdJeuPhysique()); 

			pst.executeUpdate();

			donnees.put(this.getClefDonne(emprunt), emprunt);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	/**
	 * @NE_PAS_UTILISER_CETTE_FONCTION utiliser readByAdherent à la place
	 */
	@Override
	public Emprunt read(int idEmprunt) {

		throw new UnsupportedOperationException("Cette méthode n'est pas utilisable dans la classe fille, utiliser readByAdherent à la place");	
	}
	
	// retourne la liste des emprunts associés à un adhérent
	public List<Emprunt> readByAdherent(int idAdherent) {
		
		List<Emprunt> listeEmprunts = new ArrayList<Emprunt>();
		System.out.println("recherch� dans la BD");
		try {

			String requete = "SELECT * FROM "+TABLE+" WHERE " + ID_ADHERENT + " = ?";

			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idAdherent);

			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				int idJeu = rs.getInt(ID_JEUPHYSIQUE);
				LocalDateTime dateEmprunt = rs.getTimestamp(DATE_EMPRUNT).toLocalDateTime();
				LocalDateTime dateRetour = rs.getTimestamp(DATE_RETOUR).toLocalDateTime();
					
				Emprunt emprunt = new Emprunt(idAdherent, idJeu, dateEmprunt, dateRetour);
				
				listeEmprunts.add(emprunt);
				
				int clefHash = this.getClefDonne(emprunt);
				if (!donnees.containsKey(clefHash)) {
					donnees.put(clefHash, emprunt);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeEmprunts;
	}


	public Integer getClefDonne(Emprunt emprunt) {

		return (emprunt.getIdAdherent() + emprunt.getIdJeuPhysique() +"" + emprunt.getDateEmprunt()).hashCode();
	}

	public void afficheSelectEtoileAdherent() {
		System.out.println("--- "+ TABLE +" non utilis� ---");
		String clauseWhere = " WHERE "+ ID_ADHERENT + 
				" = ? AND " +ID_JEUPHYSIQUE+ " = ? AND " + DATE_EMPRUNT + "= ?"+" NOT IN (SELECT "+" WHERE "+ ID_ADHERENT + 
				" = ? AND " +ID_JEUPHYSIQUE+ " = ? AND " + DATE_EMPRUNT + "= ?";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

		System.out.println("--- "+ TABLE +" contraint par adherent --- ");
		String clauseWhere1 = "WHERE " + ID_ADHERENT + 
				" = ? AND " +ID_JEUPHYSIQUE+ " = ? AND " + DATE_EMPRUNT + "= ?"+" IN (SELECT "+"WHERE " + ID_ADHERENT + 
				" = ? AND " +ID_JEUPHYSIQUE+ " = ? AND " + DATE_EMPRUNT + "= ?"+" From "+ TABLE +")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere1);

	}

}

