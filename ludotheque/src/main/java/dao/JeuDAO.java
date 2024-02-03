package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import ludotheque.Jeu;

public class JeuDAO {
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
			pst.setLocalDateTime(3, jeu.getDuree_mini());
			pst.setLocalDateTime(3, jeu.getDuree_maxi());
			pst.setString(3, jeu.getComplexite());
			pst.setInt(3, jeu.getNote_bgg());

			// on ex�cute la mise � jour
			pst.executeUpdate();

			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				jeu.setNumero(rs.getInt(1));
			}
			donnees.put(vol.getNumero(), pil);

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}

	public boolean delete(Jeu jeu) {
		boolean succes = true;
		try {
			int id = jeu.getNumero();
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
	public boolean update(Jeu obj) {
		boolean succes=true;

		String nom =obj.getNom();
		String adr =obj.getAdr();
		int salaire = obj.getSalaire();
		int id = obj.getNumero();
		
		int(1, jeu.getId());
		String(2, jeu.getNom());
		String(3, jeu.getType());
		String(1, jeu.getDescriptif());
		Int(2, jeu.getQuantite());
		Int(3, jeu.getNbr_joueurs_mini());
		Int(1, jeu.getNbr_joueurs_maxi());
		Int(2, jeu.getAgeMini());
		LocalDateTime(3, jeu.getDuree_mini());
		LocalDateTime(3, jeu.getDuree_maxi());
		String(3, jeu.getComplexite());
		Int(3, jeu.getNote_bgg());

		try {
			String requete = "UPDATE "+TABLE+" SET "+NOM_PIL+" = ?, "+ADRESSE+" = ?, "
					+SALAIRE+" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setString(2,adr) ; 
			pst.setInt(3, salaire) ;
			pst.setInt(4, id) ;
			pst.executeUpdate() ; //si l'objet a été chargé autrement
			donnees.put(id, obj);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public Vol read(int id) {
		Vol vol = null;
		if (donnees.containsKey(id)) {
			System.out.println("r�cup�r�");
			vol=donnees.get(id);
		}
		else {
			System.out.println("recherché dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String num_av = rs.getString(NUM_AV);
				String nom_pil = rs.getString(NOM_PIL);
				String ville_depart = rs.getString(VILLE_DEP);
				String ville_arrivee = rs.getString(VILLE_ARR);
				LocalDateTime salaire = rs.getLocalDateTime(H_DEP);
				LocalDateTime salaire = rs.getLocalDateTime(H_DEP);
				vol = new Vol (id, num_av, nom_pil, ville_depart, ville_arrivee, );
				donnees.put(id, pilote);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vol;
	}

	public void afficheSelectEtoileVol() {
		System.out.println("--- Vol pas en cours ---");
		String clauseWhere = CLE_PRIMAIRE+" NOT IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
		Connexion.afficheSelectEtoile("Vol", clauseWhere);

		System.out.println("--- Vol en cours --- ");
		clauseWhere = CLE_PRIMAIRE+" IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
		Connexion.afficheSelectEtoile("Vol", clauseWhere);

	}
}
