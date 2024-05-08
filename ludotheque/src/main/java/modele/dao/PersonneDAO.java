package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;

import modele.Personne;

public class PersonneDAO extends DAO<Personne> {
	private static final String TABLE = "Personne";
	private static final String CLE_PRIMAIRE = "id";
	private static final String NOM = "nom";
	private static final String PRENOM = "prenom";
	private static final String EMAIL = "email";
	private static final String ADRESSE = "Adresse";
	private static final String TEL = "tel";
	private static final String MDP = "mdp";
	
	

	private static PersonneDAO intstance = null;

	public static PersonneDAO getInstance() {
		if (intstance == null) {
			intstance = new PersonneDAO();
		}
		return intstance;

	}

	private PersonneDAO() {
		super();
	}

	@Override
	public boolean create(Personne personne) {
		boolean succes = true;
		try {

			String requete = "INSERT INTO " + TABLE + " (" + NOM + "," + PRENOM + "," + EMAIL + "," + ADRESSE + ","
					+ TEL + "," + MDP + ")VALUES(?,?,?,?,?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, personne.getNom());
			pst.setString(2, personne.getPrenom());
			pst.setString(3, personne.getEmail());
			pst.setString(4, personne.getAdresse());
			pst.setString(5, personne.getTel());
			pst.setString(6, personne.getMdp());

			// on exécute la mise à jour
			pst.executeUpdate();

			// Récupérer la clé qui a été générée et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				personne.setId(rs.getInt(1));
			}
			donnees.put(personne.getId(), personne);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}

		return succes;
	}

	@Override
	public boolean delete(Personne personne) {
		boolean succes = true;
		try {
			int id = personne.getId();
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();
			donnees.remove(id);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean update(Personne personne) {
		boolean succes = true;

		String nom = personne.getNom();
		String prenom = personne.getPrenom();
		String email = personne.getEmail();
		String adresse = personne.getAdresse();
		String tel = personne.getTel();
		int id = personne.getId();

		try {
			String requete = "UPDATE " + TABLE + " SET " + NOM + " =?, " + PRENOM + " =?," + EMAIL + " =?, " + ADRESSE
					+ " =?, " + TEL + " =? WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, nom);
			pst.setString(2, prenom);
			pst.setString(3, email);
			pst.setString(4, adresse);
			pst.setString(5, tel);
			pst.setInt(6, id);
			pst.executeUpdate();
			donnees.put(id, personne);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public Personne read(int id) {
		Personne personne = null;
		if (donnees.containsKey(id)) {
			System.out.println("r�cup�r�");
			personne = donnees.get(id);
		}
		try {

			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = " + id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString(NOM);
			String prenom = rs.getString(PRENOM);
			String email = rs.getString(EMAIL);
			String adresse = rs.getString(ADRESSE);
			String tel = rs.getString(TEL);
			String mdp = rs.getString(MDP);
			personne = new Personne(nom, prenom, email, adresse, tel, mdp);
			personne.setId(id);
			donnees.put(id, personne);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personne;
	}

	/// moatasm
	@Override
	public Personne readByEmail(String email) {
		Personne personne = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + EMAIL + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();

			// Check if ResultSet is not null and has results
			if (rs != null && rs.next()) {
				int id = rs.getInt(CLE_PRIMAIRE);
				String nom = rs.getString(NOM);
				String prenom = rs.getString(PRENOM);
				String adresse = rs.getString(ADRESSE);
				String tel = rs.getString(TEL);
				String mdp = rs.getString(MDP); // Retrieve password from database
				personne = new Personne(id, nom, prenom, email, adresse, tel, mdp);
				donnees.put(id, personne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personne;
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

//	public byte[] generateSalt() {
//		byte[] salt = new byte[16];
//		SecureRandom random = new SecureRandom();
//		random.nextBytes(salt);
//		return salt;
//	}

	public void afficheSelectEtoilePersonne() {
		System.out.println("--- Personne non utilisé ---");
		String clauseWhere = CLE_PRIMAIRE + " NOT IN (SELECT " + CLE_PRIMAIRE + " From " + TABLE + ")";
		Connexion.afficheSelectEtoile("Personne", clauseWhere);

		System.out.println("--- Personne contraint par id --- ");
		clauseWhere = CLE_PRIMAIRE + " IN (SELECT " + CLE_PRIMAIRE + " From " + TABLE + ")";
		Connexion.afficheSelectEtoile("Personne", clauseWhere);

	}

}
