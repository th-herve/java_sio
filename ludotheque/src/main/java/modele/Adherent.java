package modele;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import modele.dao.ProcheAdherentDAO;

public class Adherent extends Personne {

	// liste des noms des proches pouvant utiliser la carte
	private Set<String> proches;

	private boolean estActif;
	private String remarques;
	private String numCIN;
	private LocalDateTime dateInscription;
	
	// constructeur sans spécifié d'idPersonne (set à 0), un id sera attribué lors de l'ajout dans la bd
	public Adherent(String nom, String prenom, String email, 
					String adresse, String tel,String mdp, boolean estActif, 
					String remarques, String numCIN, LocalDateTime dateInscription) {
		super(nom, prenom, email, adresse, tel,mdp);
		this.estActif = estActif;
		this.remarques = remarques;
		this.numCIN = numCIN;
		this.dateInscription = dateInscription;

		// rempli la liste des proches
		this.fetchProches();
	}
	
	public boolean getEstActif() {
		return estActif;
	}

	public void setEstActif(boolean actif) {
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


	public boolean addProche(String nom) {
		return proches.add(nom);
	}

	public boolean delProche(String nom) {
		return proches.remove(nom);
	}
	
	public Set<String> getProches() {
		return this.proches;
	}
	
	// récupère les proches lié à l'adhérent depuis la bd
	public void fetchProches() {
		ProcheAdherentDAO procheDao = ProcheAdherentDAO.getInstance();
		this.proches = procheDao.readByAdherent(this.getId());
	}
	
	
	@Override
	public String toString() {
		return super.toString() + "\nAdherent [numero=" + this.getId() + ", est actif=" + estActif + ", remarques=" + remarques + ", Num cin="+ numCIN + ", date inscription="+ dateInscription +"]"
				+ "\nProches" + this.proches.toString();
	}
}