package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.sql.Date;

import modele.Adherent;

public class AdherentDAO extends DAO<Adherent> {

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
			String requete = "INSERT INTO "+TABLE+" ("+ID _JEUPHYSIQUE+", "+ID_ADHERENT+", "+DATE_RETOUR+", "+DATE_EMPRUNT+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			Date dateEmprunt = Date.valueOf(emprunt.getDateEmprunt().toLocalDate());
			pst.setDate(4, dateEmprunt);
			// on ex�cute la mise � jour
			pst.executeUpdate();
			Date dateEmprunt = Date.valueOf(emprunt.getDateEmprunt().toLocalDate());
			pst.setDate(4, dateEmprunt);
			// on ex�cute la mise � jour
			pst.executeUpdate();

			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				emprunt.setAdherent(rs.getInt(1));
			}
			donnees.put(emprunt.getIdJeuPhysique(), emprunt);
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				emprunt.setJeuPhysique(rs.getInt(1));
			}
			donnees.put(emprunt.getIdJeuPhysique(), emprunt);

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
	public boolean delete(Adherent adherent) {
		boolean succes = true;
		try {
			int id = adherent.getIdPersonne();
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
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
	public boolean update(Adherent adherent) {
		boolean succes=true;

		byte actif = (byte) (adherent.isActif() ? 1 : 0); // pas de boolean en sql serveur, donc il faut convertire en bit

		String remarque =adherent.getRemarques();
		String numCIN = adherent.getNumCIN();
		Date dateInscription = Date.valueOf(adherent.getDateInscription().toLocalDate());

		try {
			String requete = "UPDATE "+TABLE+" SET "+EST_ACTIF+" = ?, "+REMARQUES+" = ?, "
						+NUM_CIN+" = ? "+ DATE_INSCRIPTION +" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setByte(1, actif); 
			pst.setString(2, remarque); 
			pst.setString(3, numCIN);
			pst.setDate(4, dateInscription);

			pst.executeUpdate();

			donnees.put(adherent.getIdPersonne(), adherent);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public Adherent read(int idAdherent) {
		Adherent adherent = null;
		if (donnees.containsKey(idAdherent)) {
			System.out.println("r�cup�r�");
			adherent = donnees.get(idAdherent);
		}
		else {
			System.out.println("recherch� dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+idAdherent;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();

				Boolean estActif = rs.getBoolean(EST_ACTIF);
				String remarque = rs.getString(REMARQUES);
				String numCIN = rs.getString(NUM_CIN);
				LocalDateTime dateInscription = rs.getTimestamp(DATE_INSCRIPTION).toLocalDateTime();

				adherent = new Adherent (idAdherent, estActif, remarque, numCIN, dateInscription);

				donnees.put(idAdherent, adherent);

			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return adherent;
	}

	public void afficheSelectEtoileAdherent() {
		System.out.println("--- "+ TABLE +" non utilis� ---");
		String clauseWhere = CLE_PRIMAIRE+" NOT IN (SELECT "+CLE_PRIMAIRE+" From "+ TABLE +")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

		System.out.println("--- "+ TABLE +" contraint par adherent --- ");
		clauseWhere = CLE_PRIMAIRE+" IN (SELECT "+CLE_PRIMAIRE+" From "+ TABLE +")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

	}

}
