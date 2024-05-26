package modele;

import java.time.LocalDateTime;

import javafx.scene.control.TextField;

public class Personnel extends Personne {

	private String role;
	private LocalDateTime dateEntree;
	private LocalDateTime dateSortie;
	private String mdp;
	
	public Personnel(String nom, String prenom, String email, String adresse, String tel, String role, LocalDateTime dateEntree, LocalDateTime dateSortie, String hashedPassword) {
	    super(nom, prenom, email, adresse, tel);
	    this.mdp = hashedPassword; // Set hashed password 
	    this.role = role;
	    this.dateEntree = dateEntree;
	    this.dateSortie = dateSortie;
	}

	
	public String getRole() {
		return role;
	}

	
	public void setRole(String role) {
		this.role = role;
	}
	
	
	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public LocalDateTime getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(LocalDateTime dateEntree) {
		this.dateEntree = dateEntree;
	}

	public LocalDateTime getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(LocalDateTime dateSortie) {
		this.dateSortie = dateSortie;
	}

	@Override
	public String toString() {
		return "Personnel [role=" + role + ", dateEntree=" + dateEntree + ", dateSortie=" + dateSortie + ", password="
				+ mdp + "]";
	}

	
}