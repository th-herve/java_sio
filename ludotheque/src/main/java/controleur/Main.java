package controleur;

import java.sql.Connection;

import modele.dao.Connexion;

public class Main {
	public static void main(String[] args) {
		
		Connection co = Connexion.getInstance();
		
//		LocalDateTime insc = LocalDateTime.now();
//		Adherent ad = new Adherent(true, "ok", "123", insc);
//		
//		AdherentDAO adao = AdherentDAO.getInstance();
//		
//		boolean t = adao.create(ad);
//		System.out.println(t);
//		System.out.println("boo");

	}
}