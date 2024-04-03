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
    	 * @CREATE
    	 * @READ
    	 * @UPDATE
    	 * @DELETE
    	 */
    	
    	// create
    	pDAO.create(addPersonne); // OK
    	// update 
    	// delete

    	// ==========================   emprunt =========================================================
    	
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