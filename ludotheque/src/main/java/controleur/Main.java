package controleur;


import modele.Emprunt;
import modele.Jeu;
import modele.JeuPhysique;
import modele.Personnel;
import modele.dao.AdherentDAO;
import modele.dao.EmpruntDAO;
import modele.dao.JeuDAO;
import modele.dao.JeuPhysiqueDAO;
import modele.dao.PersonnelDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.dao.Connexion;

public class Main {	
		

		
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//    	
//    	
//    	inscription_Personnel_Controleur controller = new inscription_Personnel_Controleur(); 
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/inscriptionPersonnel.fxml"));
//    	loader.setController(controller);
//    	Parent root = loader.load();
//    	
//    	//Parent root = FXMLLoader.load(getClass().getResource( "../vue/inscriptionPersonnel.fxml"));
//    	
////    	Image icon = new Image(getClass().getResourceAsStream("./../../../images/logo_bettonludotheque.png"));
////        primaryStage.getIcons().add(icon);
//        
//        primaryStage.setScene(new Scene(root, 1280, 600));        
//        primaryStage.setTitle("Betton Ludothèque");
//        primaryStage.show();
//    }

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
    	 * @create_Ok
    	 * @read_Ok
    	 * @update_NON
    	 * @delete_OK
    	 */
    	
    	EmpruntDAO eDAO = EmpruntDAO.getInstance();
    	eDAO.deleteByAdherent(1);
    	Emprunt emp = new Emprunt(1, 2, LocalDateTime.now(), null);
    	
    	// create
    	System.out.println(eDAO.create(emp));

    	// read 
    	System.out.println(eDAO.readByAdherent(1).get(1));
    	System.out.println(eDAO.readByAdherent(1).get(1));

    	// update 
    	emp.setDateRetour(LocalDateTime.now());
    	System.out.println(eDAO.update(emp));
    	System.out.println(eDAO.readByAdherent(1));

    	// delete
//    	System.out.println(eDAO.delete(emp));

    	// select *
//    	pDAO.afficheSelectEtoilePersonne(); // marche pas bien
    	// ==========================   Jeu =========================================================

    	/**
    	 * @JEU
    	 * @create_ok
    	 * @read_ok
    	 * @update_ok
    	 * @delete_ok
    	 */

    	JeuDAO jDAO = JeuDAO.getInstance();
    	
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
    	 * @JEU_PHYSIQUE
    	 * @create_OK
    	 * @read_OK
    	 * @update_OK
    	 * @delete_OK
    	 */

    	JeuPhysiqueDAO jpDAO = JeuPhysiqueDAO.getInstance();
    	
//    	// create
//    	Jeu jeu = jDAO.read(1);
//    	JeuPhysique jeuP = new JeuPhysique(jeu, "good", "Oui");
//    	System.out.println(jpDAO.create(jeuP));
//
//    	// read 
//    	System.out.println(jDAO.read(jeuP.getId()));
//    	System.out.println(jDAO.read(jeuP.getId()));
//
//    	// update 
//    	jeuP.setEtat("Passable");
//    	System.out.println(jpDAO.update(jeuP));
//    	System.out.println(jpDAO.read(jeuP.getId()).getEtat() == "Passable");
//
//    	// delete
//    	System.out.println(jpDAO.delete(jeuP));

    	// select *
//    	pDAO.afficheSelectEtoilePersonne(); // marche pas bien
    	
    	
    	// ==========================   Personnel =========================================================
    	
    	/**
    	 * @Personnel
    	 * @create_OK
    	 * @read_OK
    	 * @update_OK
    	 * @delete_OK
    	 */

    	PersonnelDAO pDAO = PersonnelDAO.getInstance();
    	
    	// create
//    	Personnel personnel= new Personnel("sam", "lesbriz", "sam@mail.com", "rue", "08", "dirlo", LocalDateTime.now(), null);
//    	System.out.println(pDAO.create(personnel));
//    	System.out.println(pDAO.read(personnel.getId()));

    	// read 
//    	System.out.println(pDAO.read(1076));
//    	System.out.println(pDAO.read(1076));

    	// update 
//    	Personnel pUP = pDAO.read(1076);
//    	pUP.setNom("sam");
//    	pUP.setDateSortie(LocalDateTime.now());
//    	System.out.println(pDAO.update(pUP));
//    	System.out.println(pDAO.read(pUP.getId()));

    	// delete
//    	Personnel pUP = pDAO.read(1076);
//    	System.out.println(pDAO.delete(pUP));

    	// select *
//    	pDAO.afficheSelectEtoilePersonne(); // marche pas bien
    	
    	// Test update des adhérents
//    	Adherent ad = aDAO.read(1);
//    	ad.addProche("Jean");
//    	ad.delProche("Bobe");
//    	aDAO.upateLesProches(ad);
//    	System.out.println(ad.toString());

    	
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
    	 * @PROCHE
    	 * Tester depuis la classe adherent
    	 * @create_OK
    	 * @read_OK
    	 * @update_OK
    	 * @delete_OK
    	 */

    	// create
    	// read
    	// update
    	// delete
    	
    	
    }


}  