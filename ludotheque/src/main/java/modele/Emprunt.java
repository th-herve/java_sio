
package modele;

import java.time.LocalDateTime;

public class Emprunt {

	private int idJeuPhysique;
	private int idAdherent; // TODO c'est en nvarchar dans la base, changer en bool et renomer estActif
	private LocalDateTime dateEmprunt;
	private LocalDateTime dateRetour; // TODO c'est un int dans la bd, changer en str 
	//private LocalDateTime dateInscription;


	public Emprunt(int idJeuPhysique, int idAdherent, LocalDateTime dateEmprunt, LocalDateTime dateRetour) {
		super();
		this.idJeuPhysique = idJeuPhysique;
		this.idAdherent = idAdherent;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
		//this.LocalDateTimeInscription = LocalDateTimeInscription;
	}


	public Emprunt(Boolean idAdherent2, String idJeuPhysique2, Boolean idAdherent3, LocalDateTime dateEmprunt2,
			LocalDateTime dateRetour2) {
		// TODO Auto-generated constructor stub
	}


	public int getIdJeuPhysique() {
		return idJeuPhysique;
	}

	public void setIdJeuPhysique(int idJeuPhysique) {
		this.idJeuPhysique = idJeuPhysique;
	}

	public int isIdAdherent() {
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
		return "Emprunt [numero du jeu=" + "idJeuPhysique" + "numéro de l'adherent ="+"idAdherent" + "date de l'emprunt ="+"dateEmprunt" + "date du retour ="+"dateRetour"+"]";
	}


	public Integer getIdAherent() {
		// TODO Auto-generated method stub
		return null;
	}
}
