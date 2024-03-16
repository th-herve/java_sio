package modele;

import java.time.LocalDateTime;

public class Adherent {

	private int idPersonne;
	private boolean estActif;
	private String remarques;
	private String numCIN;
	private LocalDateTime dateInscription;

	public Adherent(boolean estActif, String remarques, String numCIN, LocalDateTime dateInscription) {
		super();
		this.estActif = estActif;
		this.remarques = remarques;
		this.numCIN = numCIN;
		this.dateInscription = dateInscription;
	}

	public Adherent(int idPersonne, boolean estActif, String remarques, String numCIN, LocalDateTime dateInscription) {
		super();
		this.idPersonne = idPersonne;
		this.estActif = estActif;
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
		return estActif;
	}

	public void setActif(boolean actif) {
		this.estActif = actif;
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

	// ?? peut être que la date d'inscription ne devrait pas être modifiable ?
	public void setDateInscription(LocalDateTime dateInscription) {
		this.dateInscription = dateInscription;
	}

	
	
	@Override
	public String toString() {
		return "Adherent [numero=" + idPersonne + ", est actif=" + estActif + ", remarques=" + remarques + ", Num cin="+ numCIN + ", date inscription="+ dateInscription +"]";
	}
}