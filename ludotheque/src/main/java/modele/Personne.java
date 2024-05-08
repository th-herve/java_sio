package modele;

import org.bouncycastle.pqc.crypto.DigestingMessageSigner;

// éviter d'instancier cette classe, utiliser Adherent ou Personnel à la place
public class Personne {

	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String adresse;
	private String tel;
	private String mdp; // pour le mot de passe 

	private String salt;
	public Personne(String nom, String prenom, String email, String adresse, String tel , String mdp) {
		super();
	
		this.id = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;
		this.mdp = mdp;
	}

	public Personne(int id, String nom, String prenom, String email, String adresse, String tel , String mdp) {
		super();
	
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;
		this.mdp = mdp ;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse="
				+ adresse + ", tel=" + tel + ", mdp=" + mdp + "]";
	}

	

}
