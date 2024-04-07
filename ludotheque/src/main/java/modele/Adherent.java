package modele;

import java.time.LocalDateTime;

public class Adherent {

	// composition pattern
	private Personne personne;

	private boolean estActif;
	private String remarques;
	private String numCIN;
	private LocalDateTime dateInscription;

	public Adherent(int idPersonne, String nom, String prenom, String email, 
					String adresse, String tel, boolean estActif, 
					String remarques, String numCIN, LocalDateTime dateInscription) {
		personne = new Personne(nom, prenom, email, adresse, tel);
		personne.setId(idPersonne);
		this.estActif = estActif;
		this.remarques = remarques;
		this.numCIN = numCIN;
		this.dateInscription = dateInscription;
	}

	// constructeur sans spécifié d'idPersonne (set à 0), un id sera attribué lors de l'ajout dans la bd
	public Adherent(String nom, String prenom, String email, 
					String adresse, String tel, boolean estActif, 
					String remarques, String numCIN, LocalDateTime dateInscription) {
		personne = new Personne(nom, prenom, email, adresse, tel);
//		personne.setId(0);
		this.estActif = estActif;
		this.remarques = remarques;
		this.numCIN = numCIN;
		this.dateInscription = dateInscription;
	}

	public Adherent(Personne personne, boolean estActif, String remarques, String numCIN, LocalDateTime dateInscription) {
		super();
		this.personne = personne;
		this.estActif = estActif;
		this.remarques = remarques;
		this.numCIN = numCIN;
		this.dateInscription = dateInscription;
	}

	public Personne getPersonne() {
		return this.personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public int getIdPersonne() {
		return this.personne.getId();
	}

	public void setIdPersonne(int idPersonne) {
		this.personne.setId(idPersonne);
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

	public String getNom() {
		return personne.getNom();
	}

	public void setNom(String nom) {
		personne.setNom(nom);
	}

	public String getPrenom() {
		return personne.getPrenom();
	}

	public void setPrenom(String prenom) {
		personne.setPrenom(prenom);
	}

	public String getEmail() {
		return personne.getEmail();
	}

	public void setEmail(String email) {
		personne.setEmail(email);
	}

	public String getAdresse() {
		return personne.getAdresse();
	}

	public void setAdresse(String adresse) {
		personne.setAdresse(adresse);
	}

	public String getTel() {
		return personne.getTel();
	}

	public void setTel(String tel) {
		personne.setTel(tel);
	}

	
	
	@Override
	public String toString() {
		return personne.toString() + "\nAdherent [numero=" + this.personne.getId() + ", est actif=" + estActif + ", remarques=" + remarques + ", Num cin="+ numCIN + ", date inscription="+ dateInscription +"]";
	}
}