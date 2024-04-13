package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import modele.Personnel;


public class PersonnelDAO extends DAO<Personnel> {

	private static final String TABLE = "Personnel";
    private static final String CLE_PRIMAIRE = "idPersonne";
    
    //est ce que je dois changer le nom de CLE_PRIMAIRE pour CLE_FORIGNE ???
    
//	private static final String CLE_FORIGNE = "idPersonne";
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
	}

	@Override
	public boolean create(Personnel personnel) {
		boolean success = true;
		try {
			String requete = "INSERT INTO " + TABLE + " (" + ROLE + ", " + DATE_ENTREE + ", " + DATE_SORTIE
					+ ") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, personnel.getRole());
			pst.setObject(2, personnel.getDateEntree());
			pst.setObject(3, personnel.getDateSortie());
//            Date dateEntree = Date.valueOf(personnel.getDateEntree()).toLocalDate());
//            pst.setDate(2, dateEntree);
//            Date dateSortie = Date.valueOf(personnel.getDateSortie().toLocalDate());
//            pst.setDate(3, dateSortie);

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				personnel.setidPersonne(rs.getInt(1));
			}

			donnees.put(personnel.getidPersonne(), personnel);
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
			int id = personnel.getidPersonne();
			String query = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(query);
			pst.setInt(1, id);
			pst.executeUpdate();
			donnees.remove(id);
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
			pst.setInt(4, personnel.getidPersonne());

			pst.executeUpdate();

			donnees.put(personnel.getidPersonne(), personnel);
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public Personnel read(int idPersonne) {
		Personnel personnel = null;
		if (donnees.containsKey(idPersonne)) {
			personnel = donnees.get(idPersonne);
		} else {
			try {
				String query = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = " + idPersonne;
				ResultSet rs = Connexion.executeQuery(query);
				rs.next();

				String role = rs.getString(ROLE);
				LocalDateTime dateEntree = rs.getTimestamp(DATE_ENTREE).toLocalDateTime();
				LocalDateTime dateSortie = rs.getTimestamp(DATE_SORTIE).toLocalDateTime();

				personnel = new Personnel(idPersonne, role, dateEntree, dateSortie);
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
