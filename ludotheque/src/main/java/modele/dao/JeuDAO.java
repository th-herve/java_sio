package modele.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import ludotheque.Jeu;

public class JeuDAO extends DAO<Jeu> {
	private static final String TABLE = "Jeu";
	private static final String CLE_PRIMAIRE = "id";

	private static final String NOM = "nom";
	private static final String TYPE = "type";
	private static final String DESCRIPTIF = "descriptif";
	private static final String QUANTITE = "quantite";
	private static final String NBR_JOUEURS_MINI = "nbr_joueurs_mini";
	private static final String NBR_JOUEURS_MAXI = "nbr_joueurs_maxi";
	private static final String AGE_MINI = "ageMini";
	private static final String DUREE_MINI = "duree_mini";
	private static final String DUREE_MAXI = "duree_maxi";
	private static final String COMPLEXITE = "complexite";
	private static final String NOTE_BGG = "note_bgg";

	/** Patron de conception Singleton
	 * 
	 */
	private static JeuDAO instance=null;

	public static JeuDAO getInstance(){
		if (instance==null){
			instance = new JeuDAO();
		}
		return instance;
	}

	private JeuDAO() {
		super();
	}


	public boolean create(Jeu jeu) {
		boolean succes=true;
		try {

			String requete = "INSERT INTO "+TABLE+" ("+NOM+","+TYPE+" , "+DESCRIPTIF+","+QUANTITE+","+NBR_JOUEURS_MINI +","+NBR_JOUEURS_MAXI+","+AGE_MINI +","+DUREE_MINI+","+DUREE_MAXI+","+COMPLEXITE+","+NOTE_BGG+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			// on pose un String en param�tre 1 -1er '?'- et ce String est le nom de l'avion
			pst.setInt(1, jeu.getId());
			pst.setString(2, jeu.getNom());
			pst.setString(3, jeu.getType());
			pst.setString(1, jeu.getDescriptif());
			pst.setInt(2, jeu.getQuantite());
			pst.setInt(3, jeu.getNbr_joueurs_mini());
			pst.setInt(1, jeu.getNbr_joueurs_maxi());
			pst.setInt(2, jeu.getAgeMini());
			pst.setInt(1, jeu.getDuree_mini());
			pst.setInt(2, jeu.getDuree_maxi());		
			pst.setInt(3, jeu.getComplexite());
			pst.setInt(3, jeu.getNote_bgg());

			// on ex�cute la mise � jour
			pst.executeUpdate();

			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				jeu.setId(rs.getInt(1));
			}
			donnees.put(jeu.getId(), jeu);

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}

	public boolean delete(Jeu jeu) {
		boolean succes = true;
		try {
			int id = jeu.getId();
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

	public boolean update (Jeu obj) {
		boolean succes=true;

		int id =obj.getId();
		String nom =obj.getNom();
		String type =obj.getType();
		String descriptif =obj.getDescriptif();
		int quantite =obj.getQuantite();
		int nbr_joueurs_mini =obj.getNbr_joueurs_mini();
		int nbr_joueurs_maxi =obj.getNbr_joueurs_maxi();
		int ageMini =obj.getAgeMini();
		int dureeMini =obj.getDuree_mini();
		int dureeMaxi =obj.getDuree_maxi();
		int complexite =obj.getComplexite();
		int note_bgg =obj.getNote_bgg();

		try {
			String requete = "UPDATE "+TABLE+" SET "+NOM+" = ?, "+TYPE+" = ?, "+DESCRIPTIF+" = ?,"+QUANTITE+" = ?,"+NBR_JOUEURS_MINI+" = ?,"+NBR_JOUEURS_MAXI+" = ?,"+AGE_MINI+" = ?,"+DUREE_MINI+" = ?,"+DUREE_MAXI+" = ?,"+COMPLEXITE+" = ?,"+NOTE_BGG+" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1, nom);
			pst.setString(2, type);
			pst.setString(3, descriptif);
			pst.setInt(1, quantite);
			pst.setInt(2, nbr_joueurs_mini);
			pst.setInt(3, nbr_joueurs_maxi);
			pst.setInt(1, ageMini);
			pst.setTimestamp(dureeMini, null);
			pst.setTimestamp(dureeMaxi, null);
			pst.setInt(3, complexite);
			pst.setInt(3, note_bgg);
			pst.executeUpdate() ; //si l'objet a été chargé autrement
			donnees.put(id, obj);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	public Jeu read(int id) {
		Jeu jeu = null;
		if (donnees.containsKey(id)) {
			System.out.println("r�cup�r�");
			jeu=donnees.get(id);
		}
		else {
			System.out.println("recherché dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String nom = rs.getString(NOM);
				String type = rs.getString(TYPE);
				String descriptif = rs.getString(DESCRIPTIF);
				int quantite = rs.getInt(QUANTITE);
				int nbrJoueursMini = rs.getInt(NBR_JOUEURS_MINI);
				int nbrJoueursMaxi = rs.getInt(NBR_JOUEURS_MAXI);
				int ageMini = rs.getInt(AGE_MINI);
				int dureeMini = rs.getTimestamp(DUREE_MINI);
				int dureeMaxi = rs.getTimestamp(DUREE_MAXI);
				int complexite = rs.getInt(COMPLEXITE);
				int noteBgg = rs.getInt(NOTE_BGG);
				
				
				
				jeu = new Jeu (id, nom, type, descriptif, quantite, nbrJoueursMini, nbrJoueursMaxi, ageMini, dureeMini, dureeMaxi, complexite, noteBgg);
						
				donnees.put(id, jeu);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jeu;
	}

	public void afficheSelectEtoileJeu() {
//		System.out.println("--- Vol pas en cours ---");
//		String clauseWhere = CLE_PRIMAIRE+" NOT IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
//		Connexion.afficheSelectEtoile("Vol", clauseWhere);
//
//		System.out.println("--- Vol en cours --- ");
//		clauseWhere = CLE_PRIMAIRE+" IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
//		Connexion.afficheSelectEtoile("Vol", clauseWhere);

	}
}
