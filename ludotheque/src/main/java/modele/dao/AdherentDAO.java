package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
<<<<<<< HEAD
import java.sql.Date;
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
>>>>>>> test-javafx

import modele.Adherent;

public class AdherentDAO extends DAO<Adherent> {

<<<<<<< HEAD
	private static final String TABLE 			= "Adherent";
	private static final String CLE_PRIMAIRE 	= "idPersonne";
=======
	private static PersonneDAO personneDao;
	private static ProcheAdherentDAO procheDAO;

	private static final String TABLE = "Adherent";
	private static final String CLE_PRIMAIRE = "idPersonne";
>>>>>>> test-javafx

	private static final String EST_ACTIF = "estActif";
	private static final String REMARQUES = "remarques";
	private static final String NUM_CIN = "numCIN";
	private static final String DATE_INSCRIPTION = "dateInscription";

	/**
	 * Patron de conception Singleton
	 * 
	 */
	private static AdherentDAO instance = null;

	public static AdherentDAO getInstance() {
		if (instance == null) {
			instance = new AdherentDAO();
		}
		return instance;
	}

	private AdherentDAO() {
		super();
	}

	@Override
	public boolean create(Adherent adherent) {
		boolean succes = true;
		try {

<<<<<<< HEAD
			String requete = "INSERT INTO "+TABLE+" ("+EST_ACTIF+","+REMARQUES+" , "+NUM_CIN+", "+ DATE_INSCRIPTION+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			// on pose un String en param�tre 1 -1er '?'- et ce String est le nom de l'avion
			pst.setBoolean(1, adherent.isActif());
			pst.setString(2, adherent.getRemarques());
			pst.setString(3, adherent.getNumCIN());
=======
			int idPers = adherent.getId();

			// si l'id est 0, il faut également créer une nouvelle personne (si != 0 ca veut
			// dire que la personne assciée à l'adherent existe déjà)
			if (idPers == 0) {
				personneDao.create((Personne) adherent);
			}

			String requete = "INSERT INTO " + TABLE + " (" + CLE_PRIMAIRE + ", " + EST_ACTIF + ", " + REMARQUES + ", "
					+ NUM_CIN + ", " + DATE_INSCRIPTION + ") VALUES (?, ?, ?, ?, ?)";

			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, adherent.getId());
			pst.setBoolean(2, adherent.getEstActif());
			pst.setString(3, adherent.getRemarques());
			pst.setString(4, adherent.getNumCIN());
>>>>>>> test-javafx
			Date dateInscription = Date.valueOf(adherent.getDateInscription().toLocalDate());
			pst.setDate(4, dateInscription);
			// on ex�cute la mise � jour
			pst.executeUpdate();

			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				adherent.setIdPersonne(rs.getInt(1));
			}
			donnees.put(adherent.getIdPersonne(), adherent);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}

		return succes;
	}

	@Override
	public boolean delete(Adherent adherent) {
		boolean succes = true;
		try {
<<<<<<< HEAD
			int id = adherent.getIdPersonne();
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
=======
			Personne personne = (Personne) adherent;
			int id = adherent.getId();

			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
>>>>>>> test-javafx
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();
			donnees.remove(id);
<<<<<<< HEAD
=======

			// on suprimme également la personne associée
			personneDao.delete(personne);

>>>>>>> test-javafx
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean update(Adherent adherent) {
		boolean succes = true;

<<<<<<< HEAD
		byte actif = (byte) (adherent.isActif() ? 1 : 0); // pas de boolean en sql serveur, donc il faut convertire en bit
		String remarque =adherent.getRemarques();
=======
		// TODO enlever binary operator
		byte actif = (byte) (adherent.getEstActif() ? 1 : 0); // pas de boolean en sql serveur, donc il faut convertire
																// en
																// bit
		String remarque = adherent.getRemarques();
>>>>>>> test-javafx
		String numCIN = adherent.getNumCIN();
		Date dateInscription = Date.valueOf(adherent.getDateInscription().toLocalDate());

		try {
<<<<<<< HEAD
			String requete = "UPDATE "+TABLE+" SET "+EST_ACTIF+" = ?, "+REMARQUES+" = ?, "
						+NUM_CIN+" = ? "+ DATE_INSCRIPTION +" = ? WHERE "+CLE_PRIMAIRE+" = ?";
=======
			String requete = "UPDATE " + TABLE + " SET " + EST_ACTIF + " = ?, " + REMARQUES + " = ?, " + NUM_CIN
					+ " = ?, " + DATE_INSCRIPTION + " = ? WHERE " + CLE_PRIMAIRE + " = ?";
>>>>>>> test-javafx
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setByte(1, actif);
			pst.setString(2, remarque);
			pst.setString(3, numCIN);
			pst.setDate(4, dateInscription);
			pst.setInt(5, adherent.getIdPersonne());

			pst.executeUpdate();

<<<<<<< HEAD
			donnees.put(adherent.getIdPersonne(), adherent);
=======
			donnees.put(adherent.getId(), adherent);

			// update la personne associée
			personneDao.update((Personne) adherent);
>>>>>>> test-javafx

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
		} else {
			System.out.println("recherch� dans la BD");
			try {

				String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = " + idAdherent;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();

				Boolean estActif = rs.getBoolean(EST_ACTIF);
				String remarque = rs.getString(REMARQUES);
				String numCIN = rs.getString(NUM_CIN);
				LocalDateTime dateInscription = rs.getTimestamp(DATE_INSCRIPTION).toLocalDateTime();

<<<<<<< HEAD
				adherent = new Adherent (idAdherent, estActif, remarque, numCIN, dateInscription);
=======
				personne = personneDao.read(idAdherent);
				adherent = new Adherent(personne.getNom(), personne.getPrenom(), personne.getEmail(),
						personne.getAdresse(), personne.getTel(), estActif, remarque, numCIN, dateInscription);
				adherent.setId(personne.getId());
>>>>>>> test-javafx

				donnees.put(idAdherent, adherent);

			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return adherent;
	}
<<<<<<< HEAD
=======

	public List<Adherent> readAll() {

		List<Adherent> adList = new ArrayList<Adherent>();

		try {

			String requete = "SELECT " + CLE_PRIMAIRE + " FROM " + TABLE;
			ResultSet rs = Connexion.executeQuery(requete);

			while (rs.next()) {

				Adherent adherent;
				int idAdherent = rs.getInt(CLE_PRIMAIRE);
				

				if (donnees.containsKey(idAdherent)) {
					System.out.println("r�cup�r�");
					adherent = donnees.get(idAdherent);
				} else {
					adherent = this.read(idAdherent);
				}

				adList.add(adherent);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adList;
	}

	// met à jour les proches adhérents dans la base en supprimant tout et
	// réajoutant les proches de la liste de l'objet adhérent
	public boolean upateLesProches(Adherent adherent) {

		boolean reussi = true;
		procheDAO.deleteByIdAdherent(adherent.getId());

		Set<String> listeProches = adherent.getProches();
		for (String proche : listeProches) {
			procheDAO.create(new ProcheAdherent(adherent.getId(), proche));
		}

		return reussi;
	}
>>>>>>> test-javafx

	public void afficheSelectEtoileAdherent() {
		System.out.println("--- " + TABLE + " non utilis� ---");
		String clauseWhere = CLE_PRIMAIRE + " NOT IN (SELECT " + CLE_PRIMAIRE + " From " + TABLE + ")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

		System.out.println("--- " + TABLE + " contraint par adherent --- ");
		clauseWhere = CLE_PRIMAIRE + " IN (SELECT " + CLE_PRIMAIRE + " From " + TABLE + ")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

	}

}
