package modele;


public class JeuPhysique { // monopoly 12

	private Jeu jeu;

	private int id;
	private String etat;
	private String disponible;
//	private int idJeu;


	public JeuPhysique(Jeu jeu, String etat, String disponible) {
		super();
		this.jeu = jeu;
		this.disponible = disponible;
		this.etat = etat;
	}

	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}
	

	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}


	public String getDisponible() {
		return disponible;
	}


	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	public int getIdJeu() {
		return this.jeu.getId();
	}


	public Jeu getJeu() {
		return this.jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}


	@Override
	public String toString() {
		return super.toString() + "\njeuPhysique [numero=" + id + ", état = " + etat + ", disponible = " + disponible + ", id jeu ="+ this.getIdJeu() + "]";
	}

}