package modele.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;

import modele.Personne;
import modele.Personnel;


public class PersonnelDAO extends DAO<Personnel> {

	private static PersonneDAO personneDao;

	private static final String TABLE = "Personnel";
	private static final String CLE_PRIMAIRE = "idPersonne";

	private static final String ROLE = "role";
	private static final String DATE_ENTREE = "dateEntree";
	private static final String DATE_SORTIE = "dateSortie";
	private static final String MDP = "mdp";
	private static PersonnelDAO instance = null;

	public static PersonnelDAO getInstance() {
		if (instance == null) {
			instance = new PersonnelDAO();
		}
		return instance;
	}

	private PersonnelDAO() {
		super();
		personneDao = PersonneDAO.getInstance();
	}

	@Override
    public boolean create(Personnel personnel) {
        boolean success = true;
        try {
            String requete = "INSERT INTO " + TABLE + " (" + CLE_PRIMAIRE + ", " + ROLE + ", " + DATE_ENTREE + ", " + DATE_SORTIE
                            + ", " + MDP + ") VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, personnel.getId());
            pst.setString(2, personnel.getRole());
            pst.setObject(3, personnel.getDateEntree());
            pst.setObject(4, personnel.getDateSortie());
            pst.setString(5, personnel.getPassword()); // Include password here
            pst.executeUpdate();
            // Remaining code...
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
	                    + " = ?, " + MDP + " = ? WHERE " + CLE_PRIMAIRE + " = ?";
	            PreparedStatement pst = Connexion.getInstance().prepareStatement(query);
	            pst.setString(1, personnel.getRole());
	            pst.setObject(2, personnel.getDateEntree());
	            pst.setObject(3, personnel.getDateSortie());
	            pst.setString(4, personnel.getPassword()); // Include password here
	            pst.setInt(5, personnel.getId());
	            pst.executeUpdate();
	            // Remaining code...
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
	         int id = personnel.getId();

	         String query = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
	         PreparedStatement pst = Connexion.getInstance().prepareStatement(query);
	         pst.setInt(1, id);
	         pst.executeUpdate();

	         donnees.remove(id);

	         // No need to delete associated personne record as there is no association
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
	             String query = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
	             PreparedStatement pst = Connexion.getInstance().prepareStatement(query);
	             pst.setInt(1, idPersonne);
	             ResultSet rs = pst.executeQuery();
	             if (rs.next()) {
	                 String role = rs.getString(ROLE);
	                 LocalDateTime dateEntree = rs.getTimestamp(DATE_ENTREE).toLocalDateTime();
	                 // Read password from ResultSet
	                 String password = rs.getString(MDP);

	                 // Handle null dateSortie
	                 LocalDateTime dateSortie = null;
	                 Timestamp timestamp = rs.getTimestamp(DATE_SORTIE);
	                 if (timestamp != null) {
	                     dateSortie = timestamp.toLocalDateTime();
	                 }

	                 personnel = new Personnel(
	                		 rs.getString("nom"),
	                         rs.getString("prenom"),
	                         rs.getString("email"),
	                         rs.getString("adresse"),
	                         rs.getString("tel"),
	                         password, // Use password retrieved from ResultSet
	                         role,
	                         dateEntree,
	                         dateSortie
	                 );
	                 personnel.setId(idPersonne);
	                 donnees.put(idPersonne, personnel);
	             }
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }
	     }
	     return personnel;
	 }
	 @Override
	 public Personne readByEmail(String email) {
	     Personnel personnel = null;
	     
	     String TABLE_PERSONNE = "Personne";
	     PersonneDAO personneDAO = PersonneDAO.getInstance(); // Get instance of PersonneDAO
	     try {
	         String query = "SELECT * FROM " + TABLE_PERSONNE + " WHERE " + PersonneDAO.EMAIL + " = ?";
	         PreparedStatement pst = Connexion.getInstance().prepareStatement(query);
	         pst.setString(1, email);
	         ResultSet rs = pst.executeQuery();
	         
	         if (rs != null && rs.next()) {
	             int id = rs.getInt(PersonneDAO.CLE_PRIMAIRE);
	             String nom = rs.getString("nom"); // Retrieve nom from ResultSet
	             String prenom = rs.getString("prenom"); // Retrieve prenom from ResultSet
	             String adresse = rs.getString("adresse"); // Retrieve adresse from ResultSet
	             String tel = rs.getString("tel"); // Retrieve tel from ResultSet
	             
	             personnel = new Personnel(nom, prenom, email, adresse, tel, tel, tel, null, null);
	             personnel.setId(id);
	         }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
	     return personnel;
	 }


	 public String hashPassword(String password) {
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hashedBytes = digest.digest(password.getBytes());
				return Base64.getEncoder().encodeToString(hashedBytes);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return null;
			}
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