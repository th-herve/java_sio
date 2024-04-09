package controleur;

import modele.Adherent;
import modele.dao.AdherentDAO;
import modele.dao.ProcheAdherentDAO;

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
    	 * @JEU
    	 * @create_ok
    	 * @read_ok
    	 * @update_ok
    	 * @delete_ok
    	 */

//    	JeuDAO jDAO = JeuDAO.getInstance();
    	
    	// create
//    	Jeu addJeu = new Jeu(0, "monopoli", "fun", "jeu fun", 3, 4, 8, 4, 40, 1, "2", 8);
//    	System.out.println(jDAO.create(addJeu));

    	// read 
//    	System.out.println(jDAO.read(557));
//    	System.out.println(jDAO.read(557));

    	// update 
//    	Jeu upJeu = jDAO.read(557);
//    	upJeu.setNom("Molki");
//    	System.out.println( jDAO.update(upJeu));
//    	System.out.println(jDAO.read(557));

    	// delete
//    	Jeu upJeu = jDAO.read(557);
//    	System.out.println(jDAO.delete(upJeu));

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
    	 * @Personnel
    	 * @create
    	 * @read
    	 * @update
    	 * @delete
    	 */
    	// create
    	// read
    	// update
    	// delete
    	
    	// ==========================   Adherent =========================================================

    	/**
    	 * @ADHERENT
    	 * @create_OK
    	 * @read_OK
    	 * @update_OK
    	 * @delete_OK
    	 */

    	AdherentDAO aDAO = AdherentDAO.getInstance();
    	
    	// create
//    	Adherent addAdherent = new Adherent("bob", "morane", "bob@mail.com", "rue", "08", true, "non", "2", LocalDateTime.now());
//    	System.out.println(aDAO.create(addAdherent));
//    	System.out.println(aDAO.read(addAdherent.getId()));

    	// read 
//    	System.out.println(aDAO.read(1));
//    	System.out.println(aDAO.read(1));

    	// update 
//    	Adherent adUP = aDAO.read(1072);
//    	adUP.setNom("Vendredi");
//    	adUP.setRemarques("s'apelle pas vraiment vendredi");
//    	System.out.println( aDAO.update(adUP));
//    	System.out.println(aDAO.read(adUP.getId()));

    	// delete
//    	Adherent pers2 = aDAO.read(1072);
//    	System.out.println(aDAO.delete(pers2));

    	// select *
//    	pDAO.afficheSelectEtoilePersonne(); // marche pas bien
    	
    	// Test update des adhérents
//    	Adherent ad = aDAO.read(1);
//    	ad.addProche("Jean");
//    	ad.delProche("Bobe");
//    	aDAO.upateLesProches(ad);
//    	System.out.println(ad.toString());



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