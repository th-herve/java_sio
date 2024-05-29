package modele;

import java.time.LocalDateTime;
import java.util.List;

public class Evenement {

	private int id;
	private String nom;
	private LocalDateTime dateEvenement;
	private int idJeu;
	
	private List<Integer> participants;


	public Evenement(String nom, LocalDateTime dateEvenement, int idJeu) {
		super();
		this.dateEvenement = dateEvenement;
		this.nom = nom;
		this.idJeu = idJeu;
	}



	public int getId() {
		return id;
	}



	public int getIdJeu() {
		return idJeu;
	}



	public void setIdJeu(int idJeu) {
		this.idJeu = idJeu;
	}



	public void setId(int id) {
		this.id = id;
	}



	public LocalDateTime getDateEvenement() {
		return dateEvenement;
	}



	public void setDateEvenement(LocalDateTime dateEvenement) {
		this.dateEvenement = dateEvenement;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	@Override
	public String toString() {
		return  "Evenement [id=" + id + ", date = " + this.dateEvenement + ", nom = " + this.nom + "]";
	}

}