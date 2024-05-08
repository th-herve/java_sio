package modele;

public class Jeu {
	
	private int id;
	private String nom;
	private String type;
	private String descriptif;
	private int quantite;
	private int nbr_joueurs_mini;
	private int nbr_joueurs_maxi;
	private int ageMini;
	private int duree_mini;
	private int duree_maxi;
	private int annee;
	private float complexite;
	private float note_bgg;

	public Jeu( String nom, String type, String descriptif, int quantite, int nbr_joueurs_mini, int nbr_joueurs_maxi, int ageMini, int duree_mini, 
			int duree_maxi, float complexite, int annee, float note_bgg)
{
	super();
	this.nom = nom;
	this.type= type;
	this.descriptif = descriptif;
	this.quantite = quantite;
	this.nbr_joueurs_mini = nbr_joueurs_mini;
	this.nbr_joueurs_maxi = nbr_joueurs_maxi;
	this.ageMini = ageMini;
	this.duree_mini = duree_mini;
	this.duree_maxi = duree_maxi;
	this.complexite = complexite;
	this.annee = annee;
	this.note_bgg = note_bgg;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getNbr_joueurs_mini() {
		return nbr_joueurs_mini;
	}

	public void setNbr_joueurs_mini(int nbr_joueurs_mini) {
		this.nbr_joueurs_mini = nbr_joueurs_mini;
	}

	public int getNbr_joueurs_maxi() {
		return nbr_joueurs_maxi;
	}

	public void setNbr_joueurs_maxi(int nbr_joueurs_maxi) {
		this.nbr_joueurs_maxi = nbr_joueurs_maxi;
	}

	public int getAgeMini() {
		return ageMini;
	}

	public void setAgeMini(int ageMini) {
		this.ageMini = ageMini;
	}

	public int getDuree_mini() {
		return duree_mini;
	}

	public void setDuree_mini(int duree_mini) {
		this.duree_mini = duree_mini;
	}

	public int getDuree_maxi() {
		return duree_maxi;
	}

	public void setDuree_maxi(int duree_maxi) {
		this.duree_maxi = duree_maxi;
	}

	public int getAnnee() {
		return annee;
	}
	
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	public float getComplexite() {
		return complexite;
	}

	public void setComplexite(float complexite) {
		this.complexite = complexite;
	}
	

	public float getNote_bgg() {
		return note_bgg;
	}

	public void setNote_bgg(float note_bgg) {
		this.note_bgg = note_bgg;
	}
		
	@Override
	public String toString() {
		return "Jeu [id=" + id + ", nom=" + nom + ", type=" + type + ", descriptif=" + descriptif + ", quantite="
				+ quantite + ", nbr_joueurs_mini=" + nbr_joueurs_mini + ", nbr_joueurs_maxi=" + nbr_joueurs_maxi
				+ ", ageMini=" + ageMini + ", duree_mini=" + duree_mini + ", duree_maxi=" + duree_maxi + ", complexite="
				+ complexite + ", annee=" + annee + ", note_bgg=" + note_bgg + "]";
	}

}
