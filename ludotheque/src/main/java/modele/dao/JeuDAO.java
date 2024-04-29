package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Jeu;

public class JeuDAO extends DAO<Jeu> {

	private static final String TABLE = "Jeu";
	private static final String CLE_PRIMAIRE = "id";

	private static final String NOM = "nom";
	private static final String TYPE = "type";
	private static final String DESCRIPTIF = "descriptif";
	private static final String QUANTITE = "quantite";
	private static final String NBR_JOUEURS_MINI = "nbrJoueursMini";
	private static final String NBR_JOUEURS_MAXI = "nbrJoueursMaxi";
	private static final String AGE_MINI = "ageMini";
	private static final String DUREE_MINI = "dureeMini";
	private static final String DUREE_MAXI = "dureeMaxi";
	private static final String COMPLEXITE = "complexite";
	private static final String ANNEE = "annee";
	private static final String NOTE_BGG = "noteBGG";

	private static JeuDAO instance=null;

	public static JeuDAO getInstance(){
		if (instance==null){
			instance = new JeuDAO();
		}
		return instance;
	}

	public JeuDAO() {
		super();
	}


	public boolean create(Jeu jeu) {
		boolean succes=true;
		try {

			String requete = "INSERT INTO "+TABLE+" ("+NOM+","+TYPE+" , "+DESCRIPTIF+","+QUANTITE+","+NBR_JOUEURS_MINI +","+NBR_JOUEURS_MAXI+","+AGE_MINI +","+DUREE_MINI+","+DUREE_MAXI+","
							 +COMPLEXITE+","+ANNEE+","+NOTE_BGG+") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, jeu.getNom());
			pst.setString(2, jeu.getType());
			pst.setString(3, jeu.getDescriptif());
			pst.setInt(4, jeu.getQuantite());
			pst.setInt(5, jeu.getNbr_joueurs_mini());
			pst.setInt(6, jeu.getNbr_joueurs_maxi());
			pst.setInt(7, jeu.getAgeMini());
			pst.setInt(8, jeu.getDuree_mini());
			pst.setInt(9, jeu.getDuree_maxi());		
			pst.setFloat(10, jeu.getComplexite());
			pst.setInt(11, jeu.getAnnee());
			pst.setFloat(12, jeu.getNote_bgg());

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

		try {
			String requete = "UPDATE "+TABLE+" SET "+NOM+" = ?, "+TYPE+" = ?, "+DESCRIPTIF+" = ?,"
									+QUANTITE+" = ?,"+NBR_JOUEURS_MINI+" = ?,"+NBR_JOUEURS_MAXI+" = ?,"
									+AGE_MINI+" = ?,"+DUREE_MINI+" = ?,"+DUREE_MAXI+" = ?,"
									+COMPLEXITE+" = ?,"+ANNEE+" = ?,"+NOTE_BGG+" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;

			pst.setString(1, 	obj.getNom());
			pst.setString(2, 	obj.getType());
			pst.setString(3, 	obj.getDescriptif());
			pst.setInt(4, 		obj.getQuantite());
			pst.setInt(5, 		obj.getNbr_joueurs_mini());
			pst.setInt(6, 		obj.getNbr_joueurs_maxi());
			pst.setInt(7, 		obj.getAgeMini());
			pst.setInt(8, 		obj.getDuree_mini());
			pst.setInt(9, 		obj.getDuree_maxi());
			pst.setFloat(10, 	obj.getComplexite());
			pst.setFloat(11, 	obj.getComplexite());
			pst.setInt(12, 	obj.getAnnee());
			pst.setInt(13, 		obj.getId());
			pst.executeUpdate();
			donnees.put(obj.getId(), obj);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	public Jeu read(int id) {
		Jeu jeu = null;
		if (donnees.containsKey(id)) {
			System.out.println("récupéré");
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
				int dureeMini = rs.getInt(DUREE_MINI);
				int dureeMaxi = rs.getInt(DUREE_MAXI);
				float complexite = rs.getFloat(COMPLEXITE);
				int annee = rs.getInt(ANNEE);
				float noteBgg = rs.getFloat(NOTE_BGG);

				jeu = new Jeu (nom, type, descriptif, quantite, nbrJoueursMini, nbrJoueursMaxi, ageMini, dureeMini, dureeMaxi, complexite, annee, noteBgg);
				jeu.setId(id);

				donnees.put(id, jeu);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jeu;
	}
	
	public List<Jeu> readAll(){
		
		List<Jeu> jeuList = new ArrayList<Jeu>();
		
		try {
			String requete = "SELECT " + CLE_PRIMAIRE + " FROM " + TABLE;
			ResultSet res = Connexion.executeQuery(requete); //execution de la requête
			
			while(res.next()) { //tant qu'on a des résultats
				
				Jeu jeu;
				//on déclare une variable pour l'idJeu et on récupère la donne en récupérant la clé primaire
				int idJeu = res.getInt(CLE_PRIMAIRE);
				//on vérifie si l'id existe dans les données
				if(donnees.containsKey(idJeu)) {
					System.out.println("récupéré");
					jeu = donnees.get(idJeu);
				} else {
					//sinon on lis
					jeu = this.read(idJeu);
				}
				jeuList.add(jeu);
				//on ajoute le jeu à la liste
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jeuList;	
	}

	public void afficheSelectEtoileJeu() {
		System.out.println("--- Jeu non-disponible ---");
		String clauseWhere = CLE_PRIMAIRE+" NOT IN (SELECT "+CLE_PRIMAIRE+" From Jeu)";
		Connexion.afficheSelectEtoile("Jeu", clauseWhere);

		System.out.println("--- Jeu disponible --- ");
		clauseWhere = CLE_PRIMAIRE+" IN (SELECT "+CLE_PRIMAIRE+" From Jeu)";
		Connexion.afficheSelectEtoile("Jeu", clauseWhere);
	}
}