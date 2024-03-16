package modele;

import java.time.LocalDateTime;

public class Emprunt {

	private int idJeuPhysique;
	private int idAdherent; // TODO c'est en nvarchar dans la base, changer en bool et renomer estActif
	private date dateEmprunt;
	private date dateRetour; // TODO c'est un int dans la bd, changer en str 
	private LocalDateTime dateInscription;


	public Emprunt(int idJeuPhysique, int idAdherent, date dateEmprunt, date dateRetour) {
		super();
		this.idJeuPhysique = idJeuPhysique;
		this.idAdherent = idAdherent;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
		this.dateInscription = dateInscription;
	}


	public int getIdJeuPhysique() {
		return idJeuPhysique;
	}

	public void setIdJeuPhysique(int idJeuPysique) {
		this.idJeuPhysique = idJeuPhysique;
	}

	public boolean isActif() {
		return idAdherent;
	}

	public void setActif(boolean actif) {
		this.idAdherent = actif;
	}

	public String getRemarques() {
		return dateEmprunt;
	}

	public void setRemarques(String remarques) {
		this.dateEmprunt = remarques;
	}

	public String getNumCIN() {
		return dateRetour;
	}

	public void setNumCIN(String numCIN) {
		this.dateRetour = numCIN;
	}

	public LocalDateTime getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDateTime dateInscription) {
		this.dateInscription = dateInscription;
	}

	
	
	@Override
	public String toString() {
		return "Adherent [numero=" + idJeuPhysique + ", est actif=" + idAdherent + ", remarques=" + dateEmprunt + ", Num cin="+ dateRetour + ", date inscription="+ dateInscription +"]";
	}
}
