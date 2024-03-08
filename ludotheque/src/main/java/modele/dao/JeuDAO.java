package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *  TODO addapter le code par rapport à la table Jeu de la bd quand elle sera créée
 *  C'est un copier coller de la table PiloteDAO du projet aero, donc il faut remplacer tout ce qui parle d'avion
 *  TODO faire ça pour toutes les tables de la bd
 */

import modele.Jeu; // TODO créer la classe associée dans le modele

public class JeuDAO extends DAO<Jeu> {

	private static final String TABLE = "Jeu";
	private static final String CLE_PRIMAIRE = "";

	// TODO ajouter tous les fields de la table
	private static final String NOM = "nom_dans_la_bd";

	
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


	// TODO adapter 
	@Override
	public boolean create(Jeu pi) {
		boolean succes=true;
		try {

			String requete = "INSERT INTO "+TABLE+" ("+NOM_PIL+","+ADR+" , "+SAL+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			// on pose un String en param�tre 1 -1er '?'- et ce String est le nom de l'avion
			pst.setString(1, pi.getNom());
			pst.setString(2, pi.getAdr());
			pst.setInt(3, pi.getSal());
			// on ex�cute la mise � jour
			pst.executeUpdate();

			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				pi.setNumero(rs.getInt(1));
			}
			donnees.put(pi.getNumero(), pi);

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}


	// TODO adapter 
	@Override
	public boolean delete(Jeu pi) {
		boolean succes = true;
		try {
			int id = pi.getNumero();
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

	// TODO adapter
	@Override
	public boolean update(Jeu obj) {
		boolean succes=true;

		String nom =obj.getNom();
		String loc =obj.getAdr();
		int capacite = obj.getSal();
		int id = obj.getNumero();

		try {
			String requete = "UPDATE "+TABLE+" SET "+NOM_PIL+" = ?, "+ADR+" = ?, "
						+SAL+" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setString(2,loc) ; 
			pst.setInt(3, capacite) ;
			pst.setInt(4, id) ;
			pst.executeUpdate() ;
			donnees.put(id, obj);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	// TODO adapter
	@Override
	public Jeu read(int id) {
		Jeu jeu = null;
		if (donnees.containsKey(id)) {
			System.out.println("r�cup�r�");
			pilote=donnees.get(id);
		}
		else {
			System.out.println("recherch� dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String nom = rs.getString(NOM_PIL);
				String adr = rs.getString(ADR);
				int sal = rs.getInt(SAL);
				pilote = new Pilote (id, nom, adr, sal);
				donnees.put(id, pilote);
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return pilote;
	}

	public void afficheSelectEtoilePilote() {
		System.out.println("--- Pilote non utilis� ---");
		String clauseWhere = CLE_PRIMAIRE+" NOT IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
		Connexion.afficheSelectEtoile("Pilote", clauseWhere);

		System.out.println("--- Pilote contraint par Vol --- ");
		clauseWhere = CLE_PRIMAIRE+" IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
		Connexion.afficheSelectEtoile("Pilote", clauseWhere);

	}




}
