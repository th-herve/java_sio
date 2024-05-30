
package modele;

import java.time.LocalDateTime;

import exception.AdherentNotActive;
import exception.JeuNotDisponible;
import modele.dao.AdherentDAO;
import modele.dao.EmpruntDAO;
import modele.dao.JeuPhysiqueDAO;
import modele.dao.ReservationDAO;

public class Reservation {

	private int idJeuPhysique;
	private int idAdherent;
	private LocalDateTime dateReservation;
	private boolean estEmprunte;
	
	private JeuPhysique jeuPhysique;
	private Adherent adherent;

	public Reservation(int idJeuPhysique, int idAdherent) throws JeuNotDisponible, AdherentNotActive {
		super();
		this.idJeuPhysique = idJeuPhysique;
		this.idAdherent = idAdherent;
		this.dateReservation = LocalDateTime.now();
		this.estEmprunte = false;

		this.jeuPhysique = JeuPhysiqueDAO.getInstance().read(idJeuPhysique);
		this.adherent = AdherentDAO.getInstance().read(idAdherent);

		if (!this.adherent.getEstActif()) {
			throw new AdherentNotActive("L'ahdérent n'est pas actif");
		}

		if (!this.jeuPhysique.getEstDisponible()) {
			throw new JeuNotDisponible("Jeu non disponible");
		}
		
		this.jeuPhysique.setEstDisponible(false);
		JeuPhysiqueDAO.getInstance().update(jeuPhysique);
	}


	
	public Reservation(int idJeuPhysique, int idAdherent, LocalDateTime dateReservation, boolean estEmprunte) {
		super();
		this.idJeuPhysique = idJeuPhysique;
		this.idAdherent = idAdherent;
		this.dateReservation = dateReservation;
		this.estEmprunte = estEmprunte;

		this.jeuPhysique = JeuPhysiqueDAO.getInstance().read(idJeuPhysique);
		this.adherent = AdherentDAO.getInstance().read(idAdherent);
	}



	public int getIdJeuPhysique() {
		return idJeuPhysique;
	}

	public void setIdJeuPhysique(int idJeuPhysique) {
		this.idJeuPhysique = idJeuPhysique;
	}

	public int getIdAdherent() {
		return idAdherent;
	}

	public void setIdAdherent(int idAdherent) {
		this.idAdherent = idAdherent;
	}

	public LocalDateTime getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(LocalDateTime dateReservation) {
		this.dateReservation = dateReservation;
	}

	public boolean getEstEmprunte() {
		return estEmprunte;
	}

	public void setEstEmprunte(boolean estEmprunte) {
		this.estEmprunte = estEmprunte;
	}

	public boolean enregistrerReservation() {

		boolean succes = true;

		this.estEmprunte = true;
		this.jeuPhysique.setEstDisponible(true);
		if (ReservationDAO.getInstance().update(this)) {
			JeuPhysiqueDAO.getInstance().update(jeuPhysique);
		} else {
			succes = false;
		}
		return succes;
	}


	@Override
	public String toString() {
		return "reservation [idJeuPhysique=" + idJeuPhysique + ", idAdherent=" + idAdherent + ", date=" + dateReservation
				+ ", est emprunte=" + estEmprunte + "]";
	}

}
