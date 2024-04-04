package controleur;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modele.Personne;
import modele.dao.Connexion;
import modele.dao.PersonneDAO;

public class Main {	
		

		
    public static void main(String[] args) {
//    	Connection co = Connexion.getInstance();
    	
    	// ==========================   Personne =========================================================
    	PersonneDAO pDAO = PersonneDAO.getIntstance();
    	Personne addPersonne = new Personne("bob", "Marley", "bob@mail.com", "2 rue des chouettes", "0428183928");
    	

    	/**
    	 * @PERSONNE
    	 * @create_ok
    	 * @read_ok
    	 * @update_ok
    	 * @delete_ok
    	 * @select_pas_ok
    	 */
    	
    	// create
//    	System.out.println(pDAO.create(addPersonne));

    	// read 
    	System.out.println(pDAO.read(1));
    	System.out.println(pDAO.read(1));

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
    	 * @CREATE
    	 * @READ
    	 * @UPDATE
    	 * @DELETE
    	 */
    	// create
    	// read
    	// update
    	// delete
    	
    	// ==========================   Jeu =========================================================

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
    	
    	// ==========================   JeuPhysique =========================================================
    	
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