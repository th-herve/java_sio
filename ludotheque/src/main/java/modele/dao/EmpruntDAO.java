
package modele.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
	
	private static final String WHERE_CLEF_PRIMAIRE = " WHERE " + ID_ADHERENT + " = ? AND " + ID_JEUPHYSIQUE + " = ? AND " + DATE_EMPRUNT + " = ?";
	
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
			String requete = "INSERT INTO "+TABLE+" ("+ID_ADHERENT+", "+ID_JEUPHYSIQUE+", "+DATE_EMPRUNT+", "+DATE_RETOUR+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			// on ex�cute la mise � jour
			pst.setInt(1, emprunt.getIdAdherent());
			pst.setInt(2, emprunt.getIdJeuPhysique());
//			Date dateRetour = Date.valueOf(emprunt.getDateRetour().toLocalDate());
//			Date dateEmprunt = Date.valueOf(emprunt.getDateEmprunt().toLocalDate());
			pst.setObject(3, emprunt.getDateEmprunt());
			pst.setObject(4, emprunt.getDateRetour());
			pst.executeUpdate();
			
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
			String requete = "DELETE FROM "+TABLE+ " " + WHERE_CLEF_PRIMAIRE;
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

	public boolean deleteByAdherent(int idAdherent) {
		boolean succes = true;
		try {
			
			List<Emprunt> lesEmprunts = this.readByAdherent(idAdherent);

			String requete = "DELETE FROM "+TABLE+ " WHERE " + ID_ADHERENT + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idAdherent);

			pst.executeUpdate();
			for (Emprunt emprunt : lesEmprunts) {
				donnees.remove(this.getClefDonne(emprunt));
			}
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
			pst.setObject(1, emprunt.getDateRetour()); 

			pst.setObject(2, emprunt.getIdAdherent()); 
			pst.setObject(3, emprunt.getIdJeuPhysique()); 
			pst.setObject(4, emprunt.getDateEmprunt()); 

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

				// pour la date de retour, comme elle peut etre null dans la bd, il faut controler
				Timestamp timestamp = rs.getTimestamp(DATE_RETOUR);
				LocalDateTime dateRetour = null;
				if (timestamp != null) {
					dateRetour = timestamp.toLocalDateTime();
				}
					
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

	public List<Emprunt> readAll() {

		List<Emprunt> empruntList = new ArrayList<Emprunt>();

		try {

			String requete = "SELECT * FROM "  + TABLE;
			ResultSet rs = Connexion.executeQuery(requete);

			while (rs.next()) {

				LocalDateTime dateEmprunt = rs.getTimestamp(DATE_EMPRUNT).toLocalDateTime();

				// pour la date de retour, comme elle peut etre null dans la bd, il faut controler
				Timestamp timestamp = rs.getTimestamp(DATE_RETOUR);
				LocalDateTime dateRetour = null;
				if (timestamp != null) {
					dateRetour = timestamp.toLocalDateTime();
				}
				Emprunt emprunt = new Emprunt(rs.getInt(ID_JEUPHYSIQUE), rs.getInt(ID_ADHERENT), dateEmprunt, dateRetour);
				
				// recupere la clef utilisee dans la hashmap donnee
				int idEmprunt = getClefDonne(emprunt);

				// si emprunt deja dans donne, on ajoute cette instance
				if (donnees.containsKey(idEmprunt)) {
					System.out.println("r�cup�r�");
					emprunt = donnees.get(idEmprunt);
				} 
				empruntList.add(emprunt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empruntList;
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
