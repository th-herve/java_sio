
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

import exception.AdherentNotActive;
import exception.JeuNotDisponible;
import microsoft.sql.Types;
import modele.Emprunt;
import modele.Reservation;

// !! utiliser la fonction convertDate() avant d'insérer ou utiliser une date dans la bd

/**
 * @clef_composé idAdherent + idJeuPhysique + dateEmprunt
 */
public class ReservationDAO extends DAO<Reservation> {

	private static final String TABLE = "Reservation";
	private static final String ID_ADHERENT = "id_Adherent";
	private static final String ID_JEUPHYSIQUE = "id_JeuPhysique";

	private static final String DATE_RESERVATION = "dateReservation";
	private static final String EST_EMRPRUNTE = "estEmprunte";

	private static final String WHERE_CLEF_PRIMAIRE = " WHERE " + ID_ADHERENT + " = ? AND " + ID_JEUPHYSIQUE
			+ " = ? AND " + DATE_RESERVATION + " = ?";

	/**
	 * Patron de conception Singleton
	 * 
	 */
	private static ReservationDAO instance = null;

	public static ReservationDAO getInstance() {
		if (instance == null) {
			instance = new ReservationDAO();
		}
		return instance;
	}

	private ReservationDAO() {
		super();
	}

	@Override
	public boolean create(Reservation reservation) {
		boolean succes = true;
		try {

			String requete = "INSERT INTO " + TABLE + " (" + ID_ADHERENT + ", " + ID_JEUPHYSIQUE + ", "
					+ DATE_RESERVATION + ", " + EST_EMRPRUNTE + ") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			// on ex�cute la mise � jour
			pst.setInt(1, reservation.getIdAdherent());
			pst.setInt(2, reservation.getIdJeuPhysique());
			pst.setTimestamp(3, this.convertDate(reservation.getDateReservation()));
			pst.setBoolean(4, reservation.getEstEmprunte());

			pst.executeUpdate();

			donnees.put(this.getClefDonne(reservation), reservation);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}

		return succes;
	}

	@Override
	public boolean delete(Reservation reservation) {
		boolean succes = true;
		try {
			int idAdherent = reservation.getIdAdherent();
			int idJeu = reservation.getIdJeuPhysique();
			String requete = "DELETE FROM " + TABLE + " " + WHERE_CLEF_PRIMAIRE;
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idAdherent);
			pst.setInt(2, idJeu);
			pst.setTimestamp(3, this.convertDate(reservation.getDateReservation()));

			pst.executeUpdate();
			donnees.remove(this.getClefDonne(reservation));
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

//	public boolean deleteByAdherent(int idAdherent) {
//		boolean succes = true;
//		try {
//			
//			List<Emprunt> lesEmprunts = this.readByAdherent(idAdherent);
//
//			String requete = "DELETE FROM "+TABLE+ " WHERE " + ID_ADHERENT + " = ?";
//			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
//			pst.setInt(1, idAdherent);
//
//			pst.executeUpdate();
//			for (Emprunt emprunt : lesEmprunts) {
//				donnees.remove(this.getClefDonne(emprunt));
//			}
//		} catch (SQLException e) {
//			succes=false;
//			e.printStackTrace();
//		}
//		return succes;
//	}

	@Override
	public boolean update(Reservation reservation) {
		boolean succes = true;

		try {
			String requete = "UPDATE " + TABLE + " SET " + EST_EMRPRUNTE + " = ? " + WHERE_CLEF_PRIMAIRE;
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setBoolean(1, reservation.getEstEmprunte());

			pst.setInt(2, reservation.getIdAdherent());
			pst.setInt(3, reservation.getIdJeuPhysique());
			pst.setTimestamp(4, this.convertDate(reservation.getDateReservation()));

			pst.executeUpdate();

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	/**
	 * @NE_PAS_UTILISER_CETTE_FONCTION utiliser readByAdherent à la place
	 */
	@Override
	public Reservation read(int idEmprunt) {

		throw new UnsupportedOperationException(
				"Cette méthode n'est pas utilisable dans la classe fille, utiliser readByAdherent à la place");
	}

	// retourne la liste des emprunts associés à un adhérent
//	public List<Emprunt> readByAdherent(int idAdherent) {
//		
//		List<Emprunt> listeEmprunts = new ArrayList<Emprunt>();
//
//		try {
//
//			String requete = "SELECT * FROM "+TABLE+" WHERE " + ID_ADHERENT + " = ?";
//
//			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
//			pst.setInt(1, idAdherent);
//
//			ResultSet rs = pst.executeQuery();
//
//			while(rs.next()) {
//				int idJeu = rs.getInt(ID_JEUPHYSIQUE);
//				LocalDateTime dateEmprunt = rs.getTimestamp(DATE_EMPRUNT).toLocalDateTime();
//
//				// pour la date de retour, comme elle peut etre null dans la bd, il faut controler
//				Timestamp timestamp = rs.getTimestamp(DATE_RETOUR);
//				LocalDateTime dateRetour = null;
//				if (timestamp != null) {
//					dateRetour = timestamp.toLocalDateTime();
//				}
//					
//				Emprunt emprunt = new Emprunt(idAdherent, idJeu, dateEmprunt, dateRetour);
//				
//				listeEmprunts.add(emprunt);
//				
//				int clefHash = this.getClefDonne(emprunt);
//				if (!donnees.containsKey(clefHash)) {
//					donnees.put(clefHash, emprunt);
//				}
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return listeEmprunts;
//	}

	public List<Reservation> readAll() {

		List<Reservation> reservationList = new ArrayList<Reservation>();

		try {

			String requete = "SELECT * FROM " + TABLE;
			ResultSet rs = Connexion.executeQuery(requete);

			while (rs.next()) {

				LocalDateTime dateReservation = rs.getTimestamp(DATE_RESERVATION).toLocalDateTime();

				// pour la date de retour, comme elle peut etre null dans la bd, il faut
				// controler
				Reservation reservation = new Reservation(rs.getInt(ID_JEUPHYSIQUE), rs.getInt(ID_ADHERENT),
						dateReservation, rs.getBoolean(EST_EMRPRUNTE));

				// recupere la clef utilisee dans la hashmap donnee
				int idReservation = getClefDonne(reservation);

				// si emprunt deja dans donne, on ajoute cette instance
				if (donnees.containsKey(idReservation)) {
					reservation = donnees.get(idReservation);
				}
				reservationList.add(reservation);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservationList;
	}

	public Integer getClefDonne(Reservation reservation) {

		return (reservation.getIdAdherent() + reservation.getIdJeuPhysique() + "" + reservation.getDateReservation())
				.hashCode();
	}

	/**
	 * Converti une localdatetime dans le bon format pour la bd
	 * 
	 * @param date
	 * @return timestamp
	 */
	private Timestamp convertDate(LocalDateTime date) {
		return Timestamp.valueOf(date.truncatedTo(ChronoUnit.SECONDS));
	}

	public void afficheSelectEtoileAdherent() {
		System.out.println("--- " + TABLE + " non utilis� ---");
		String clauseWhere = " WHERE " + ID_ADHERENT + " = ? AND " + ID_JEUPHYSIQUE + " = ? AND " + DATE_RESERVATION
				+ "= ?" + " NOT IN (SELECT " + " WHERE " + ID_ADHERENT + " = ? AND " + ID_JEUPHYSIQUE + " = ? AND "
				+ DATE_RESERVATION + "= ?";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere);

		System.out.println("--- " + TABLE + " contraint par adherent --- ");
		String clauseWhere1 = "WHERE " + ID_ADHERENT + " = ? AND " + ID_JEUPHYSIQUE + " = ? AND " + DATE_RESERVATION
				+ "= ?" + " IN (SELECT " + "WHERE " + ID_ADHERENT + " = ? AND " + ID_JEUPHYSIQUE + " = ? AND "
				+ DATE_RESERVATION + "= ?" + " From " + TABLE + ")";
		Connexion.afficheSelectEtoile(TABLE, clauseWhere1);

	}

}
