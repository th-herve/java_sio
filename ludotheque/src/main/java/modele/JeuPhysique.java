package modele;

import java.time.LocalDateTime;

public class JeuPhysique { // monopoly 12

	private int id;
	private String etat;
	private String disponible;
	private int idJeu;


	public JeuPhysique(int id, String etat, String disponible, int idJeu) {
		super();
		this.id = id;
		this.disponible = disponible;
		this.etat = etat;
		this.idJeu = idJeu;
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
		return idJeu;
	}


	public void setIdJeu(int idJeu) {
		this.idJeu = idJeu;
	}


	@Override
	public String toString() {
		return "jeuPhysique [numero=" + id + ", état = " + etat + ", disponible = " + disponible + ", id jeu ="+ idJeu + "]";
	}

}