
package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Jeu;
import modele.JeuPhysique;

public class JeuPhysiqueDAO extends DAO<JeuPhysique> {

	private static final String TABLE = "JeuPhysique";
	private static final String CLE_PRIMAIRE = "id";
	private static final String ETAT = "etat";

	private static final String EST_DISPONIBLE = "estDisponible"; 
	private static final String ID_JEU = "idJeu";  
	
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
			
			String requete = "INSERT INTO "+TABLE+" ("+ETAT+", "+EST_DISPONIBLE+", "+ID_JEU+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, jeuPhysique.getEtat());
			pst.setBoolean(2, jeuPhysique.getEstDisponible());
			pst.setInt(3, jeuPhysique.getIdJeu());

			// on ex�cute la mise � jour
			pst.executeUpdate();

			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
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

		try {
			String requete = "UPDATE "+TABLE+" SET "+ ETAT+" = ?, "
						+EST_DISPONIBLE+" = ?, "+ ID_JEU +" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setString(1, jeuPhysique.getEtat()); 
			pst.setBoolean(2, jeuPhysique.getEstDisponible());
			pst.setInt(3, jeuPhysique.getIdJeu());
			pst.setInt(4, jeuPhysique.getId());

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
		JeuPhysique jeuPhysique = null;
		if (donnees.containsKey(id)) {
			System.out.println("r�cup�r�");
			jeuPhysique = donnees.get(id);
		}
		else {
			System.out.println("recherch� dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+ id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();

				int idNew = rs.getInt(CLE_PRIMAIRE);
				String etat = rs.getString(ETAT);
				Boolean disponible = rs.getBoolean(EST_DISPONIBLE);
				int idJeu = rs.getInt(ID_JEU);

				JeuDAO jDao = JeuDAO.getInstance();
				Jeu jeu = jDao.read(idJeu);
				jeuPhysique = new JeuPhysique (jeu, etat, disponible);
				jeuPhysique.setId(idNew);

				donnees.put(idNew, jeuPhysique);

			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return jeuPhysique;
	}
	
	public List<JeuPhysique> readByIdJeu(int idJeu) {

		List<JeuPhysique> jeuList = new ArrayList<JeuPhysique>();

		try {

			String requete = "SELECT " + CLE_PRIMAIRE + " FROM " + TABLE + " WHERE " + ID_JEU + " = ?";

			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idJeu);

			ResultSet rs = pst.executeQuery(); 

			System.out.println(idJeu);
			while (rs.next()) {

				JeuPhysique jeuPhysique;
				int idJeuPhysique = rs.getInt(CLE_PRIMAIRE);
				

				if (donnees.containsKey(idJeuPhysique)) {
					System.out.println("r�cup�r�");
					jeuPhysique = donnees.get(idJeuPhysique);
				} else {
					jeuPhysique = this.read(idJeuPhysique);
				}

				jeuList.add(jeuPhysique);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jeuList;
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

