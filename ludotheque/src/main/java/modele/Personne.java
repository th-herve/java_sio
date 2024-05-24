package modele;



// éviter d'instancier cette classe, utiliser Adherent ou Personnel à la place
public class Personne {

	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String adresse;
	private String tel;

//	private String mdp; // pour le mot de passe 
	


	public Personne(String nom, String prenom, String email, String adresse, String tel ) {

		super();
	
		this.id = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;


	}


	public Personne(int id, String nom, String prenom, String email, String adresse, String tel) {
		super();
	
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;
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


	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse="
				+ adresse + ", tel=" + tel + "]";
	}

}