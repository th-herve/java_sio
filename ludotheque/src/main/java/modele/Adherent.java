package modele;

import java.time.LocalDateTime;

public class Adherent {

	private int idPersonne;
	private boolean actif; // TODO c'est en nvarchar dans la base, changer en bool et renomer estActif
	private String remarques;
	private String numCIN; // TODO c'est un int dans la bd, changer en str 
	private LocalDateTime dateInscription;


	public Adherent(int idPersonne, boolean actif, String remarques, String numCIN, LocalDateTime dateInscription) {
		super();
		this.idPersonne = idPersonne;
		this.actif = actif;
		this.remarques = remarques;
		this.numCIN = numCIN;
		this.dateInscription = dateInscription;
	}


	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getRemarques() {
		return remarques;
	}

	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}

	public String getNumCIN() {
		return numCIN;
	}

	public void setNumCIN(String numCIN) {
		this.numCIN = numCIN;
	}

	public LocalDateTime getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDateTime dateInscription) {
		this.dateInscription = dateInscription;
	}

	
	
	@Override
	public String toString() {
		return "Adherent [numero=" + idPersonne + ", est actif=" + actif + ", remarques=" + remarques + ", Num cin="+ numCIN + ", date inscription="+ dateInscription +"]";
	}
}