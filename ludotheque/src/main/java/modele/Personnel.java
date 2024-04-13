package modele;

import java.time.LocalDateTime;

public class Personnel {

	private int idPersonne;
	private String role;
	private LocalDateTime dateEntree;
	private LocalDateTime dateSortie;

	public Personnel(int idPersonne, String role, LocalDateTime dateEntree, LocalDateTime dateSortie) {
		super();
		this.idPersonne = idPersonne;
		this.role = role;
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
	}

	public int getidPersonne() {
		return idPersonne;
	}

	public void setidPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
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
		return "Personnel [idPersonne=" + idPersonne + ", role=" + role + ", dateEntree=" + dateEntree
				+ ", dateSortie=" + dateSortie + "]";
	}
}
