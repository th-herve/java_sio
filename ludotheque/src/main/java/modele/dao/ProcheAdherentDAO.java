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

			String requete = "INSERT INTO "+TABLE+" ("+IDPERSONNE + NOM+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setInt(1, proche.getIdPersonne());
			pst.setString(2, proche.getNom());
			// on ex�cute la mise � jour
			pst.executeUpdate();

			donnees.put(this.getClef(proche), proche);

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

			String requete = "DELETE FROM "+TABLE+" WHERE " + IDPERSONNE+" = ? AND " +NOM+ " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idPersonne);
			pst.setString(2, nom);

			pst.executeUpdate();
			donnees.remove(getClef(proche));

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	public boolean update(ProcheAdherent proche, ProcheAdherent ancientProche) { // TODO trouver un moyen d'avoir deux update dans DAO (sans avoir a implementer les deux)
		boolean succes=true;

		int idPersonne = ancientProche.getIdPersonne();
		String nom = ancientProche.getNom();

		try {
			String requete = "UPDATE "+TABLE+" SET "+NOM+" = ? WHERE "+IDPERSONNE+" = ? AND " + NOM + " = ?";

			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setString(1, idPersonne); 
			pst.setString(2, nom); 

			pst.executeUpdate();

			donnees.put(getClef(proche), proche);

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

				String requete = "SELECT * FROM "+TABLE+" WHERE " + IDPERSONNE+" = ? AND " +NOM+ " = ?";
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();

				String nom = rs.getString(NOM);

				proche = new ProcheAdherent (idAdherent, nom);

				donnees.put(getClef(proche), proche);

			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return proche;
	}
	
	
	// la clef de la table proche étant idPersonne+nom, on ne peut pas utiliser juste idPersonne pour la hashmap.
	// il faut donc générer un nombre à partir de ces deux valeurs pour obetnir une clef unique
	// comme nom est un String, il faut générer un int avec la fonction hashCode()
	public Integer getClef(ProcheAdherent proche) {

		return proche.getIdPersonne() + proche.getNom().hashCode();
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
