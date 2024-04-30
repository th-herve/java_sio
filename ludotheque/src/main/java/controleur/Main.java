package controleur;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modele.dao.Connexion;

public class Main {
	
	Connection co = Connexion.getInstance();

	public static Stage stage;
	private GererCompte compte;
	private GererJeuControleur jeu;

	
    public void start(Stage racine) throws Exception {
    	Main.stage = racine;
    	Parent root = FXMLLoader.load(getClass().getResource( "../vue/accueil.fxml"));
    	
    	compte = new GererCompte();
    	jeu = new GererJeuControleur();

    	Image image = new Image(getClass().getResourceAsStream("/logo_bettonludotheque.png"));
    	if (image.isError()) {
    	    System.err.println("Failed to load image: " + image.getException().getMessage());
    	} else {
    	    racine.getIcons().add(image);
    	}

    	//aller sur la page Gérer Compte
    	Button compteBouton = (Button) root.lookup("#compte");
    	compteBouton.setOnAction(event -> {
    	compte.initialize();
    	});
    	
    	//aller sur la page Gérer Emprunts
    	Button empruntsBouton= (Button) root.lookup("#emprunt");
    	empruntsBouton.setOnAction(event -> {
    	compte.initialize();
    	});
    	
    	//aller sur la page Gérer Jeux
    	Button jeuBouton = (Button) root.lookup("#gererJeu");
    	jeuBouton.setOnAction(event -> {
    	jeu.initialize();
    	});
    	
    	Scene scene = new Scene(root, 1280, 800);     
    	racine.setScene(scene);
    	racine.setTitle("Ludothèque");
    	racine.show();

    }
	
	
    public static void main(String[] args) {
        Application.launch(App.class, args);

    }
}
