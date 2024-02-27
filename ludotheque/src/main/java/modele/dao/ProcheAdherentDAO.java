package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modele.ProcheAdherent;

public class ProcheAdherentDAO extends DAO<ProcheAdherent> {

	// clef primaire = idPersone + Nom
	private static final String TABLE 		= "ProcheAdherent";
	private static final String IDPERSONNE 	= "idPersonne";
	private static final String NOM 		= "nom"; 
	
	/** Patron de conception Singleton
	 * 
	 */
	private static ProcheAdherentDAO instance=null;

	public static ProcheAdherentDAO getInstance(){
		if (instance==null){
			instance = new ProcheAdherentDAO();
		}
		return instance;
	}

	private ProcheAdherentDAO() {
		super();
	}


	@Override
	public boolean create(ProcheAdherent proche) {
		boolean succes=true;
		try {

			String requete = "INSERT INTO "+TABLE+" ("+NOM+") VALUES (?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, proche.getNom());
			// on ex�cute la mise � jour
			pst.executeUpdate();

			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				proche.setIdPersonne(rs.getInt(1));
			}
			donnees.put(proche.getIdPersonne(), proche);

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}


	@Override
	public boolean delete(ProcheAdherent proche) {
		boolean succes = true;
		try {
			int idPersonne 	= proche.getIdPersonne();
			String nom 		= proche.getNom();

			String requete = "DELETE FROM "+TABLE+" WHERE "+IDPERSONNE+" = ? AND " +NOM+ " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idPersonne);
			pst.setString(2, nom);

			pst.executeUpdate();
			donnees.remove(idPersonne);
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean update(ProcheAdherent proche, String ancientNom) {
		boolean succes=true;

		String nom =proche.getNom();

		try {
			String requete = "UPDATE "+TABLE+" SET "+NOM+" = ? WHERE "+IDPERSONNE+" = ?";

			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setString(1, nom); 

			pst.executeUpdate();

			donnees.put(proche.getIdPersonne(), proche);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public ProcheAdherent read(int idAdherent) {
		ProcheAdherent proche = null;
		if (donnees.containsKey(idAdherent)) {
			System.out.println("r�cup�r�");
			proche = donnees.get(idAdherent);
		}
		else {
			System.out.println("recherch� dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+IDPERSONNE+" = "+idAdherent;
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
		String clauseWhere = IDPERSONNE+" NOT IN (SELECT "+IDPERSONNE+" From "+ TABLE +")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

		System.out.println("--- "+ TABLE +" contraint par adherent --- ");
		clauseWhere = IDPERSONNE+" IN (SELECT "+IDPERSONNE+" From "+ TABLE +")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

	}

}
