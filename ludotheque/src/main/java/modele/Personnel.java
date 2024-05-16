package modele;

import java.time.LocalDateTime;

public class Personnel extends Personne {

	private String role;
	private LocalDateTime dateEntree;
	private LocalDateTime dateSortie;
	private String password;
	
	 public Personnel(String nom, String prenom, String email, String adresse, String tel, String password, String role, LocalDateTime dateEntree, LocalDateTime dateSortie) {
	        super(nom, prenom, email, adresse, tel);
	        this.password = password; // Set password attribute
	        this.role = role;
	        this.dateEntree = dateEntree;
	        this.dateSortie = dateSortie;
	    }
	
	public String getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
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
		return super.toString() + "\nPersonnel [id_Personne=" + this.getId() + ", role=" + role 
				+ ", dateEntree=" + dateEntree
				+ ", dateSortie=" + dateSortie + "]";
	}
}