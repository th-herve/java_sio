package modele;

import java.time.LocalDateTime;

public class Personnel extends Personne {


	private int idPersonne;
	private String role;
	private LocalDateTime dateEntree;
	private LocalDateTime dateSortie;


	public Personnel(int idPersonne,String nom, String prenom, String email, 
					String adresse, String tel, String role, 
					LocalDateTime dateEntree, LocalDateTime dateSortie) {
	
		super(nom, prenom, email, adresse, tel);
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

		return super.toString() + "\nPersonnel [idPersonne=" + this.getId() + ", role=" + role 
				+ ", dateEntree=" + dateEntree + ", dateSortie=" + dateSortie + "]" ; 

	}
}
