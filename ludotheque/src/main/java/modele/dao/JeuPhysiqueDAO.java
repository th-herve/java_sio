
package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.sql.Date;

import modele.Adherent;
import modele.JeuPhysique;

public class JeuPhysiqueDAO extends DAO<JeuPhysique> {

	private static final String TABLE = "JeuPhysique";
	private static final String CLE_PRIMAIRE = "id";
	private static final String ETAT = "etat";

	private static final String DISPONIBLE = "disponible"; 
	private static final String ID_JEU = "idjeu";  
	
	/** Patron de conception Singleton
	 * 
	 */
	private static JeuPhysiqueDAO instance=null;

	public static JeuPhysiqueDAO getInstance(){
		if (instance==null){
			instance = new JeuPhysiqueDAO();
		}
		return instance;
	}

	private JeuPhysiqueDAO() {
		super();
	}


	// TODO adapter 
	@Override
	public boolean create(JeuPhysique jeuPhysique) {
		boolean succes=true;
		try {
			
			String requete = "INSERT INTO "+TABLE+" ("+CLE_PRIMAIRE+", "+ETAT+", "+DISPONIBLE+", "+ID_JEU+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(2, jeuPhysique.getEtat());
			// on ex�cute la mise � jour
			pst.executeUpdate();
			pst.setString(3, jeuPhysique.getDisponible());
			pst.setInt(4, jeuPhysique.getIdJeu());
			pst.setInt(1, jeuPhysique.getId());
			// on ex�cute la mise � jour
			pst.executeUpdate();

			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				jeuPhysique.setId(rs.getInt(1));
			}
			donnees.put(jeuPhysique.getId(), jeuPhysique);
			//R�cup�rer la cle qui a ete genere et la pousser dans l'objet initial
			if (rs.next()) {
				jeuPhysique.setId(rs.getInt(1)); 
			}
			donnees.put(jeuPhysique.getId(), jeuPhysique);

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
	public boolean delete(JeuPhysique jeuPhysique) {
		boolean succes = true;
		try {
			int id = jeuPhysique.getId();
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
	public boolean update(JeuPhysique jeuPhysique) {
		boolean succes=true;

		byte actif = (byte) (jeuPhysique.isId() ? 1 : 0); 
		// pas de boolean en sql serveur, donc il faut convertire en bit

		int id =jeuPhysique.getId();
		String etat = jeuPhysique.getEtat();
		Date dateInscription = Date.valueOf(jeuPhysique().toLocalDate());

		try {
			String requete = "UPDATE "+TABLE+" SET "+CLE_PRIMAIRE+" = ?, "+ETAT+" = ?, "
						+DISPONIBLE+" = ? "+ ID_JEU +" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setInt(1, CLE_PRIMAIRE); 
			pst.setString(2, ETAT); 
			pst.setString(3, DISPONIBLE);
			pst.setInt(4, ID_JEU);

			pst.executeUpdate();

			donnees.put(jeuPhysique.getId(), jeuPhysique);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	} 
 
	@Override
	public JeuPhysique read(int id) {
		JeuPhysique idJeuPhysique = null;
		if (donnees.containsKey(id)) {
			System.out.println("r�cup�r�");
			idJeuPhysique = donnees.get(id);
		}
		else {
			System.out.println("recherch� dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+idAdherent;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();

				Boolean id = rs.getBoolean(ID);
				String etat = rs.getString(ETAT);
				String disponible = rs.getString(DISPONIBLE);
				LocalDateTime idJeu = rs.getTimestamp(ID_JEU).toLocalDateTime();

				IdJeuPhysique = new IdJeuPhysique (ID, ETAT, DISPONIBLE, ID_JEU);

				donnees.put(idJeuPhysique, jeuPhysique);

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

