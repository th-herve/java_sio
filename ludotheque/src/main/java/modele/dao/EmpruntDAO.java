
package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.sql.Date;

import modele.Adherent;
import modele.Emprunt;

public class EmpruntDAO extends DAO<Emprunt> {
	
	// IMPORTANT : clé composée de idAdherent + idJeuPhysique + dateEmprunt 
	// à faire 

	private static final String TABLE = "Emprunt";
	private static final String ID_ADHERENT = "idAdherent";
	private static final String ID_JEUPHYSIQUE = "idJeuPhysique";

	private static final String DATE_EMPRUNT = "dateEmprunt"; 
	private static final String DATE_RETOUR = "dateRetour";  
	
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
			pst.setInt(2, emprunt.getIdJeuPhysique());
			Date dateEmprunt = Date.valueOf(emprunt.getDateEmprunt().toLocalDate());
			pst.setDate(3, DateEmprunt());
			Date dateRetour = Date.valueOf(emprunt.getDateRetour().toLocalDate());
			pst.setDate(4, DateRetour());
			
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				emprunt.setIdJeuPhysique(rs.getInt(1));
			}
			donnees.put(emprunt.getIdJeuPhysique(), emprunt);

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}

private Date DateRetour() {
		// TODO Auto-generated method stub
		return null;
	}

private Date DateEmprunt() {
		// TODO Auto-generated method stub
		return null;
	}

	// commentaires de Charles : 
// en dessous c'est la partie que j'ai copié du code de Thibault, 
// important de modifier pour que ça colle à mon code à moi, je boss sur es clés étrangères,
// Thibault a bossé sur une clé primaire, donc bien différent
	@Override
	public boolean delete(Emprunt emprunt) {
		boolean succes = true;
		try {
			int id = emprunt.getIdJeuPhysique();
			String requete = "DELETE FROM "+TABLE+" WHERE "+"WHERE " + ID_ADHERENT + 
					" = ? AND " +ID_JEUPHYSIQUE+ " = ? AND " + DATE_EMPRUNT + "= ?"+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();
			donnees.remove(id);
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean update(Emprunt emprunt) {
		boolean succes=true;

		byte idAdherent = (byte) (emprunt.isIdAdherent() ? 1 : 0); 
		// pas de boolean en sql serveur, donc il faut convertire en bit

		LocalDateTime dateEmprunt =emprunt.getDateEmprunt();
		LocalDateTime dateRetour = emprunt.getDateRetour();

		try {
			String requete = "UPDATE "+TABLE+" SET "+ID_JEUPHYSIQUE+" = ?, "+ID_ADHERENT+" = ?, "
						+DATE_EMPRUNT+" = ? "+ DATE_RETOUR +" = ? WHERE " + ID_ADHERENT + 
						" = ? AND " +ID_JEUPHYSIQUE+ " = ? AND " + DATE_EMPRUNT + "= ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			// fonction différente donc refaire les pst
			pst.setInt(1, emprunt.getIdJeuPhysique()); 
			pst.setBoolean(2, emprunt.isIdAdherent()); 

			pst.executeUpdate();

			donnees.put(emprunt.getIdAherent(), emprunt);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public Emprunt read(int idAdherent) {
		Emprunt adherent = null;
		if (donnees.containsKey(idAdherent)) {
			System.out.println("r�cup�r�");
			adherent = donnees.get(idAdherent);
		}
		else {
			System.out.println("recherch� dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+ ID_ADHERENT + 
				" = ? AND " +ID_JEUPHYSIQUE+ " = ? AND " + DATE_EMPRUNT + "= ?";
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();

				Boolean IdAdherent = rs.getBoolean(ID_ADHERENT);
				String  idJeuPhysique= rs.getString(ID_JEUPHYSIQUE);
				LocalDateTime dateEmprunt = rs.getTimestamp(DATE_EMPRUNT).toLocalDateTime();
				LocalDateTime dateRetour = rs.getTimestamp(DATE_RETOUR).toLocalDateTime();

				Emprunt emprunt = new Emprunt (IdAdherent, idJeuPhysique, IdAdherent, dateEmprunt, dateRetour);

				

			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return adherent;
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

