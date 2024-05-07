package modele.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import modele.Personne;
import modele.Personnel;


public class PersonnelDAO extends DAO<Personnel> {

	private static PersonneDAO personneDao;

	private static final String TABLE = "Personnel";
	private static final String CLE_PRIMAIRE = "idPersonne";

	private static final String ROLE = "role";
	private static final String DATE_ENTREE = "dateEntree";
	private static final String DATE_SORTIE = "dateSortie";

	private static PersonnelDAO instance = null;

	public static PersonnelDAO getInstance() {
		if (instance == null) {
			instance = new PersonnelDAO();
		}
		return instance;
	}

	private PersonnelDAO() {
		super();
		personneDao = PersonneDAO.getIntstance();
	}

	@Override
	public boolean create(Personnel personnel) {
		boolean success = true;
		try {

			int idPers = personnel.getId();
			
			// si l'id est 0, il faut également créer une nouvelle personne (si != 0 ca veut dire que la personne assciée à l'adherent existe déjà)
			if (idPers == 0) {
				personneDao.create((Personne)personnel);
			}

			String requete = "INSERT INTO " + TABLE + " (" + CLE_PRIMAIRE + ", " + ROLE + ", " + DATE_ENTREE + ", " + DATE_SORTIE
					+ ") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, personnel.getId());
			pst.setString(2, personnel.getRole());
			pst.setObject(3, personnel.getDateEntree());
			pst.setObject(4, personnel.getDateSortie());
//            Date dateEntree = Date.valueOf(personnel.getDateEntree()).toLocalDate());
//            pst.setDate(2, dateEntree);
//            Date dateSortie = Date.valueOf(personnel.getDateSortie().toLocalDate());
//            pst.setDate(3, dateSortie);

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				personnel.setId(rs.getInt(1));
			}

			donnees.put(personnel.getId(), personnel);
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delete(Personnel personnel) {
		boolean success = true;
		try {
			Personne personne = (Personne)personnel;
			int id = personnel.getId();

			String query = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(query);
			pst.setInt(1, id);
			pst.executeUpdate();

			donnees.remove(id);

			// on suprimme également la personne associée
			personneDao.delete(personne);

		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean update(Personnel personnel) {
		boolean success = true;
		try {
			String query = "UPDATE " + TABLE + " SET " + ROLE + " = ?, " + DATE_ENTREE + " = ?, " + DATE_SORTIE
					+ " = ? WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(query);

			pst.setString(1, personnel.getRole());
			Date dateEntree = Date.valueOf(personnel.getDateEntree().toLocalDate());
			pst.setDate(2, dateEntree);
			Date dateSortie = Date.valueOf(personnel.getDateSortie().toLocalDate());
			pst.setDate(3, dateSortie);
			pst.setInt(4, personnel.getId());

			pst.executeUpdate();

			donnees.put(personnel.getId(), personnel);

			// update la personne associée
			personneDao.update((Personne) personnel);


		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public Personnel read(int idPersonne) {

		Personnel personnel = null;
		Personne personne = null;

		if (donnees.containsKey(idPersonne)) {
			personnel = donnees.get(idPersonne);
		} else {
			try {
				String query = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = " + idPersonne;
				ResultSet rs = Connexion.executeQuery(query);
				rs.next();

				String role = rs.getString(ROLE);
				LocalDateTime dateEntree = rs.getTimestamp(DATE_ENTREE).toLocalDateTime();

				// pour la date de sortie, comme elle peut etre null dans la bd, il faut controler
				Timestamp timestamp = rs.getTimestamp(DATE_SORTIE);
				LocalDateTime dateSortie = null;
				if (timestamp != null) {
					dateSortie = timestamp.toLocalDateTime();
				}

				personne = personneDao.read(idPersonne);
				personnel = new Personnel(personne.getNom(), personne.getPrenom(), personne.getEmail(), personne.getAdresse(), personne.getTel(),personne.getMdp() ,role, dateEntree, dateSortie);
				personnel.setId(personne.getId());


				donnees.put(idPersonne, personnel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return personnel;
	}
	public void afficheSelectEtoilePersonnel() {
//		System.out.println("--- Personnel non utilisé ---");
//		String clauseWhere = CLE_PRIMAIRE + " NOT IN (SELECT " + CLE_PRIMAIRE + " From Personnel)";
//		Connexion.afficheSelectEtoile("Personnel", clauseWhere);

		System.out.println("--- Personnel contraint par id --- ");
		String clauseWhere = CLE_PRIMAIRE + " IN (SELECT " + CLE_PRIMAIRE + " From Personnel)";
		Connexion.afficheSelectEtoile("Personnel", clauseWhere);

	}
}