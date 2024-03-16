package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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

	private static PersonneDAO intstance = null;

	public static PersonneDAO getIntstance() {
		if (intstance == null) {
			intstance = new PersonneDAO();
		}
		return intstance;

	}

	private PersonneDAO() {
		super();
	}

	@Override
	public boolean create(Personne Pe) {
		boolean succes = true;
		try {

			String requete = "INSERT INTO PERSONNE" + TABLE + "(" + NOM + "," + PRENOM + "," + EMAIL + "," + ADRESSE
					+ "," + TEL + ")VALUES(?,?,?,?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, Pe.getNom());
			pst.setString(2, Pe.getPrenom());
			pst.setString(3, Pe.getEmail());
			pst.setString(4, Pe.getAdresse());
			pst.setInt(5, Pe.getTel());

			// on exécute la mise à jour
			pst.executeUpdate();

			// Récupérer la clé qui a été générée et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				Pe.setId(rs.getInt(1));
			}
			donnees.put(Pe.getId(), Pe);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}

		return succes;
	}

	@Override
	public boolean delete(Personne Pe) {
		boolean succes = true;
		try {
			int id = Pe.getId();
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
	public boolean update(Personne obj) {
		boolean succes = true;

		String nom = obj.getNom();
		String prenom = obj.getPrenom();
		String email = obj.getEmail();
		String adresse = obj.getAdresse();
		int tel = obj.getTel();
		int id = obj.getId();

		try {
			String requete = "UPDATE " + TABLE + " SET nomPe = ?, loc = ?, capacite = ? WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, nom);
			pst.setString(2, prenom);
			pst.setString(3, email);
			pst.setString(4, adresse);
			pst.setInt(5, tel);
			pst.setInt(6, id);
			pst.executeUpdate();
			donnees.put(id, obj);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	public Personne read0(int id) {
		Personne Personne = null;
		if (donnees.containsKey(id)) {
			// System.out.println("récupéré");
			Personne = donnees.get(id);
		} else {
			// System.out.println("recherché dans la BD");
			try {

				String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = " + id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String nom = rs.getString(NOM);
				String prenom = rs.getString(PRENOM);
				String email = rs.getString(EMAIL);
				String adresse = rs.getString(ADRESSE);
				int tel = rs.getInt(TEL);
				Personne = new Personne(nom, prenom, email, adresse, tel);
				Personne.setId(id);
				donnees.put(id, Personne);
			} catch (SQLException e) {
				// e.printStackTrace();
			}
		}
		return Personne;
	}

	@Override
	public Personne read(int id) {
		Personne Personne = null;
		try {

			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = " + id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString(NOM);
			String prenom = rs.getString(PRENOM);
			String email = rs.getString(EMAIL);
			String adresse = rs.getString(ADRESSE);
			int tel = rs.getInt(TEL);
			Personne = new Personne(nom, prenom, email, adresse, tel);
			Personne.setId(id);
			donnees.put(id, Personne);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Personne;
	}

	public void afficheSelectEtoilePersonne() {
		System.out.println("--- Personne non utilisé ---");
		String clauseWhere = CLE_PRIMAIRE + " NOT IN (SELECT " + CLE_PRIMAIRE + " From Personne)";
		Connexion.afficheSelectEtoile("Personne", clauseWhere);

		System.out.println("--- Personne contraint par id --- ");
		clauseWhere = CLE_PRIMAIRE + " IN (SELECT " + CLE_PRIMAIRE + " From Personne)";
		Connexion.afficheSelectEtoile("Personne", clauseWhere);

	}

}
