package modele;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Personnel {

	private int id_Personne;
	private String role;
	private LocalDateTime dateEntree;
	private LocalDateTime dateSortie;

	public Personnel(int id_Personne, String role, LocalDateTime dateEntree, LocalDateTime dateSortie) {
		super();
		this.id_Personne = id_Personne;
		this.role = role;
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
	}

	public int getId_Personne() {
		return id_Personne;
	}

	public void setId_Personne(int id_Personne) {
		this.id_Personne = id_Personne;
	}

	public String getRole() {
		return role;
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
		return "Personnel [id_Personne=" + id_Personne + ", role=" + role + ", dateEntree=" + dateEntree
				+ ", dateSortie=" + dateSortie + "]";
	}
}
