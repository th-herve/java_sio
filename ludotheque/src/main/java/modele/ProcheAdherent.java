package modele;

public class ProcheAdherent {

	private int idPersonne;
	private String nom;

	public ProcheAdherent(String nom) {
		super();
		this.nom = nom;
	}

	public ProcheAdherent(int idPersonne, String nom) {
		super();
		this.idPersonne = idPersonne;
		this.nom = nom;
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	@Override
	public String toString() {
		return "Proche adherent [numero=" + this.idPersonne + ", nom =" + this.nom + "]";
	} 
}
