
package modele;

import java.time.LocalDateTime;

import modele.dao.AdherentDAO;
import modele.dao.JeuPhysiqueDAO;

public class Emprunt {

	private int idJeuPhysique;
	private int idAdherent;
	private LocalDateTime dateEmprunt;
	private LocalDateTime dateRetour;
	
	private JeuPhysique jeuPhysique;
	private Adherent adherent;

	/**
	 * @param idJeuPhysique
	 * @param idAdherent
	 */
	public Emprunt(int idJeuPhysique, int idAdherent) {
		super();
		this.idJeuPhysique = idJeuPhysique;
		this.idAdherent = idAdherent;
		this.dateEmprunt = LocalDateTime.now();
		this.dateRetour = null;
		
		this.jeuPhysique = JeuPhysiqueDAO.getInstance().read(idJeuPhysique);
		this.adherent = AdherentDAO.getInstance().read(idAdherent);
	}

	public Emprunt(int idJeuPhysique, int idAdherent, LocalDateTime dateEmprunt, LocalDateTime dateRetour) {
		super();
		this.idJeuPhysique = idJeuPhysique;
		this.idAdherent = idAdherent;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
		
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

	public LocalDateTime getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDateTime dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public LocalDateTime getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(LocalDateTime dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	public String getNomAdherent() {
		return this.adherent.getNom();
	}

	public String getNomJeuPhysique() {
		return this.jeuPhysique.getJeu().getNom();
	}


	@Override
	public String toString() {
		return "Emprunt [idJeuPhysique=" + idJeuPhysique + ", idAdherent=" + idAdherent + ", dateEmprunt=" + dateEmprunt
				+ ", dateRetour=" + dateRetour + "]";
	}



}
