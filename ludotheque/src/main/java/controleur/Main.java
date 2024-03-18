package controleur;

import java.time.LocalDateTime;

import modele.Adherent;
import modele.dao.AdherentDAO;

public class Main {
	public static void main(String[] args) {
		
		LocalDateTime insc = LocalDateTime.now();
		Adherent ad = new Adherent(true, "ok", "123", insc);
		
		AdherentDAO adao = AdherentDAO.getInstance();
		
		adao.create(ad);
		System.out.println("boo");

	}
}