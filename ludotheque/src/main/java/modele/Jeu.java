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
	private String complexite;
	private float note_bgg;

	
	/**
	 * ON ajoute des commentaires
	 * @param numero
	 * @param nom
	 * @param adr
	 * @param salaire
	 */
	public Jeu(int id, String nom, String type, String descriptif, int quantite, int nbr_joueurs_mini, int nbr_joueurs_maxi, int ageMini, int duree_mini, int duree_maxi, String complexite, float note_bgg)
	{
		super();
		this.id = id;
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
		this.note_bgg = note_bgg;
	}

	/**
	 * Encore d'autres
	 * @return la localisation
	 */
	

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

	public String getComplexite() {
		return complexite;
	}

	public void setComplexite(String complexite) {
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
				+ complexite + ", note_bgg=" + note_bgg + "]";
	}

}
