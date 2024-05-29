
package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import modele.Evenement;
import modele.Jeu;

public class EvenementDAO extends DAO<Evenement> {

	private static final String TABLE = "evenement";
	private static final String CLE_PRIMAIRE = "id";
	private static final String NOM = "nom";
	private static final String DATE_EVENEMENT = "dateEvenement";
	private static final String ID_JEU = "idJeu";

	/**
	 * Patron de conception Singleton
	 * 
	 */
	private static EvenementDAO instance = null;

	public static EvenementDAO getInstance() {
		if (instance == null) {
			instance = new EvenementDAO();
		}
		return instance;
	}

	private EvenementDAO() {
		super();
	}
	
	public static void main(String[] args) {
		Evenement ev = new Evenement("fist", LocalDateTime.now(), 2);
		EvenementDAO.getInstance().create(ev);
		
	}

	// TODO adapter
	@Override
	public boolean create(Evenement evenement) {
		boolean succes = true;
		try {

			String requete = "INSERT INTO " + TABLE + " (" + NOM + ", " + DATE_EVENEMENT + ", " + ID_JEU
					+ ") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, evenement.getNom());
			pst.setTimestamp(2, this.convertDate(evenement.getDateEvenement()));
			pst.setInt(3, evenement.getIdJeu());

			// on ex�cute la mise � jour
			pst.executeUpdate();

			// R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				evenement.setId(rs.getInt(1));
			}
			donnees.put(evenement.getId(), evenement);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}

		return succes;
	}

// commentaires de Charles : 
// en dessous c'est la partie que j'ai copié du code de Thibault, 
// important de modifier pour que ça colle à mon code à moi, je boss sur es clés étrangères,
// Thibault a bossé sur une clé primaire, donc bien différent
	@Override
	public boolean delete(Evenement evenement) {
		boolean succes = true;
		try {
			int id = evenement.getId();
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
	public boolean update(Evenement evenement) {
		boolean succes = true;

		try {
			String requete = "UPDATE " + TABLE + " SET " + NOM + " = ?, " + DATE_EVENEMENT + " = ?," + ID_JEU + " = ?"
					+ " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setString(1, evenement.getNom());
			pst.setTimestamp(2, this.convertDate(evenement.getDateEvenement()));
			pst.setInt(3, evenement.getIdJeu());
			pst.setInt(4, evenement.getId());

			pst.executeUpdate();

			donnees.put(evenement.getId(), evenement);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public Evenement read(int id) {
		Evenement evenement = null;
		if (donnees.containsKey(id)) {
			System.out.println("r�cup�r�");
			evenement = donnees.get(id);
		} else {
			System.out.println("recherch� dans la BD");
			try {

				String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = " + id;
				ResultSet rs = Connexion.executeQuery(requete);

				if (rs.next()) {
					int idNew = rs.getInt(CLE_PRIMAIRE);
					String nom = rs.getString(NOM);
					LocalDateTime dateEmprunt = rs.getTimestamp(DATE_EVENEMENT).toLocalDateTime();
					int idJeu = rs.getInt(ID_JEU);

					evenement = new Evenement(nom, dateEmprunt, idJeu);
					evenement.setId(idNew);

					donnees.put(idNew, evenement);
				}

			} catch (SQLException e) {
				// e.printStackTrace();
			}
		}
		return evenement;
	}

	public List<Evenement> readAll() {

		List<Evenement> evenementsList = new ArrayList<Evenement>();

		try {
			String requete = "SELECT " + CLE_PRIMAIRE + " FROM " + TABLE;
			ResultSet res = Connexion.executeQuery(requete); // execution de la requête

			while (res.next()) { // tant qu'on a des résultats

				Evenement ev;
				// on déclare une variable pour l'idJeu et on récupère la donne en récupérant la
				// clé primaire
				int idEv = res.getInt(CLE_PRIMAIRE);
				// on vérifie si l'id existe dans les données
				if (donnees.containsKey(idEv)) {
					ev = donnees.get(idEv);
				} else {
					// sinon on lis
					ev = this.read(idEv);
				}
				evenementsList.add(ev);
				// on ajoute le jeu à la liste

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return evenementsList;
	}

	public void afficheSelectEtoileAdherent() {
		System.out.println("--- " + TABLE + " non utilis� ---");
		String clauseWhere = CLE_PRIMAIRE + " NOT IN (SELECT " + CLE_PRIMAIRE + " From " + TABLE + ")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

		System.out.println("--- " + TABLE + " contraint par adherent --- ");
		clauseWhere = CLE_PRIMAIRE + " IN (SELECT " + CLE_PRIMAIRE + " From " + TABLE + ")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

	}

	private Timestamp convertDate(LocalDateTime date) {
		return Timestamp.valueOf(date.truncatedTo(ChronoUnit.SECONDS));
	}
}
