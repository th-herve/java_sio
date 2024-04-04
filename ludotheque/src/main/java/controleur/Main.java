package controleur;

import java.time.LocalDateTime;

import modele.Adherent;
import modele.Jeu;
import modele.JeuPhysique;
import modele.Personne;
import modele.dao.AdherentDAO;
import modele.dao.JeuDAO;
import modele.dao.JeuPhysiqueDAO;

public class Main {	
		

		
    public static void main(String[] args) {
//    	Connection co = Connexion.getInstance();
    	
    	// ==========================   Personne =========================================================

    	/**
    	 * @PERSONNE
    	 * @create_ok
    	 * @read_ok
    	 * @update_ok
    	 * @delete_ok
    	 * @select_pas_ok
    	 */
//    	PersonneDAO pDAO = PersonneDAO.getIntstance();
//    	Personne addPersonne = new Personne("bob", "Marley", "bob@mail.com", "2 rue des chouettes", "0428183928");
    	
    	// create
//    	System.out.println(pDAO.create(addPersonne));

    	// read 
//    	System.out.println(pDAO.read(1));
//    	System.out.println(pDAO.read(1));

    	// update 
//    	Personne pers1 = pDAO.read(1);
//    	pers1.setNom("Dupond");
//    	System.out.println( pDAO.update(pers1));
//    	System.out.println(pDAO.read(1));

    	// delete
//    	Personne pers2 = pDAO.read(72);
//    	System.out.println(pDAO.delete(pers2));

    	// select *
//    	pDAO.afficheSelectEtoilePersonne(); // marche pas bien

    	// ==========================   emprunt =========================================================
    	
    	/**
    	 * @EMPRUNT
    	 * @create
    	 * @read
    	 * @update
    	 * @delete
    	 */
    	
    	// ==========================   Jeu =========================================================

    	/**
    	 * @CREATE
    	 * @READ
    	 * @UPDATE
    	 * @DELETE
    	 */

    	JeuDAO jDAO = JeuDAO.getInstance();
    	
    	// create
    	Jeu addJeu = new Jeu(0, "monopoli", "fun", "jeu fun", 3, 4, 8, 4, 40, 1, "2", 8);
    	System.out.println(jDAO.create(addJeu));

    	// read 
//    	System.out.println(pDAO.read(1));
//    	System.out.println(pDAO.read(1));

    	// update 
//    	Personne pers1 = pDAO.read(1);
//    	pers1.setNom("Dupond");
//    	System.out.println( pDAO.update(pers1));
//    	System.out.println(pDAO.read(1));

    	// delete
//    	Personne pers2 = pDAO.read(72);
//    	System.out.println(pDAO.delete(pers2));

    	// select *
//    	pDAO.afficheSelectEtoilePersonne(); // marche pas bien
    	
    	// ==========================   JeuPhysique =========================================================
    	
    	/**
    	 * @CREATE
    	 * @READ
    	 * @UPDATE
    	 * @DELETE
    	 */

//    	JeuPhysiqueDAO jpDAO = JeuPhysiqueDAO.getInstance();
    	
    	// create
//    	JeuPhysique addJeuP = new JeuPhysique(1, "Bien", "oui", 0)
//    	System.out.println(pDAO.create(addPersonne));

    	// read 
//    	System.out.println(pDAO.read(1));
//    	System.out.println(pDAO.read(1));

    	// update 
//    	Personne pers1 = pDAO.read(1);
//    	pers1.setNom("Dupond");
//    	System.out.println( pDAO.update(pers1));
//    	System.out.println(pDAO.read(1));

    	// delete
//    	Personne pers2 = pDAO.read(72);
//    	System.out.println(pDAO.delete(pers2));

    	// select *
//    	pDAO.afficheSelectEtoilePersonne(); // marche pas bien
    	
    	
    	// ==========================   Personnel =========================================================
    	
    	/**
    	 * @CREATE
    	 * @READ
    	 * @UPDATE
    	 * @DELETE
    	 */
    	// create
    	// read
    	// update
    	// delete
    	
    	// ==========================   Adherent =========================================================

    	/**
    	 * @ADHERENT
    	 * @create
    	 * @read
    	 * @update
    	 * @delete
    	 */

//    	AdherentDAO aDAO = AdherentDAO.getInstance();
    	
    	// create
//    	Adherent addAdherent = new Adherent(true, "est con", "123", LocalDateTime.now());
//    	System.out.println(aDAO.create(addAdherent));

    	// read 
//    	System.out.println(pDAO.read(1));
//    	System.out.println(pDAO.read(1));

    	// update 
//    	Personne pers1 = pDAO.read(1);
//    	pers1.setNom("Dupond");
//    	System.out.println( pDAO.update(pers1));
//    	System.out.println(pDAO.read(1));

    	// delete
//    	Personne pers2 = pDAO.read(72);
//    	System.out.println(pDAO.delete(pers2));

    	// select *
//    	pDAO.afficheSelectEtoilePersonne(); // marche pas bien

    	// ==========================   Proche =========================================================

    	/**
    	 * @CREATE
    	 * @READ
    	 * @UPDATE
    	 * @DELETE
    	 */
    	// create
    	// read
    	// update
    	// delete
    	
    }



}  