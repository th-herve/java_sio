package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modele.Emprunt;
import modele.ProcheAdherent;

/***
 * @clef_composée idPersonne + nom
 */
public class ProcheAdherentDAO extends DAO<ProcheAdherent> {

	// clef primaire = idPersone + Nom
	private static final String TABLE 		= "ProcheAdherent";
	private static final String ID_PERSONNE = "idPersonne";
	private static final String NOM 		= "nom"; 
	
	private static final String WHERE_CLEF_PRIMAIRE = " WHERE " + ID_PERSONNE + " = ?, " + NOM + " = ?";
	
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

			String requete = "INSERT INTO "+TABLE+" ("+ID_PERSONNE + NOM+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setInt(1, proche.getIdPersonne());
			pst.setString(2, proche.getNom());
			// on ex�cute la mise � jour
			pst.executeUpdate();

			donnees.put(this.getClefDonnee(proche), proche);

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

			String requete = "DELETE FROM "+TABLE+ WHERE_CLEF_PRIMAIRE;
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idPersonne);
			pst.setString(2, nom);

			pst.executeUpdate();
			donnees.remove(this.getClefDonnee(proche));

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	/**
	 * @NE_PAS_UTILISER_CETTE_FONCTION si besoin d'update le nom d'un proche, d'abord le supprimer puis en creer un autre
	 */
	@Override
	public boolean update(ProcheAdherent proche) {
		throw new UnsupportedOperationException("Cette méthode n'est pas utilisable dans la classe fille");	
	}

	/**
	 * @NE_PAS_UTILISER_CETTE_FONCTION utiliser readByAdherent à la place
	 */
	@Override
	public ProcheAdherent read(int idAdherent) {

		throw new UnsupportedOperationException("Cette méthode n'est pas utilisable dans la classe fille, utiliser readProcheAdherent à la place");
	}
	
	
	// retourne la liste des emprunts associés à un adhérent
	public List<ProcheAdherent> readByAdherent(int idAdherent) {
		
		List<ProcheAdherent> listeProches = new ArrayList<ProcheAdherent>();
		System.out.println("recherch� dans la BD");
		try {

			String requete = "SELECT * FROM "+TABLE+" WHERE " + ID_PERSONNE + " = ?";

			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idAdherent);

			ResultSet rs = pst.executeQuery(); 

			while(rs.next()) {
				String nom = rs.getString(NOM);
					
				ProcheAdherent proche = new ProcheAdherent(idAdherent, nom);
				
				listeProches.add(proche);
				
				int clefHash = this.getClefDonnee(proche);
				if (!donnees.containsKey(clefHash)) {
					donnees.put(clefHash, proche);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeProches;
	}
	
	
	// la clef de la table proche étant idPersonne+nom, on ne peut pas utiliser juste idPersonne pour la hashmap.
	// il faut donc générer un nombre à partir de ces deux valeurs pour obetnir une clef unique
	// comme nom est un String, il faut générer un int avec la fonction hashCode()
	public Integer getClefDonnee (ProcheAdherent proche) {

		return (proche.getIdPersonne() + proche.getNom()).hashCode();
	}


	public void afficheSelectEtoileAdherent() {
		System.out.println("--- "+ TABLE +" non utilis� ---");
		String clauseWhere = ID_PERSONNE+" NOT IN (SELECT "+ID_PERSONNE+" From "+ TABLE +")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

		System.out.println("--- "+ TABLE +" contraint par adherent --- ");
		clauseWhere = ID_PERSONNE+" IN (SELECT "+ID_PERSONNE+" From "+ TABLE +")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

	}


}
