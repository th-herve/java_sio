
package modele;

import java.time.LocalDateTime;

public class Emprunt {

	private int idJeuPhysique;
	private int idAdherent; // TODO c'est en nvarchar dans la base, changer en bool et renomer estActif
	private LocalDateTime dateEmprunt;
	private LocalDateTime dateRetour; // TODO c'est un int dans la bd, changer en str 
	//private LocalDateTime dateInscription;


	public Emprunt(int idAdherent, int idJeuPhysique, LocalDateTime dateEmprunt, LocalDateTime dateRetour) {
		super();
		this.idJeuPhysique = idJeuPhysique;
		this.idAdherent = idAdherent;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
		//this.LocalDateTimeInscription = LocalDateTimeInscription;
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


	@Override
	public String toString() {
		return "Emprunt [idJeuPhysique=" + idJeuPhysique + ", idAdherent=" + idAdherent + ", dateEmprunt=" + dateEmprunt
				+ ", dateRetour=" + dateRetour + "]";
	}



}
