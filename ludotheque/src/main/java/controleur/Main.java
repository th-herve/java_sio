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

public class Main extends Application {	
		
	Connection co = Connexion.getInstance();


	public static Stage stage;
	private GererCompte compte;
	private GererJeu jeu;

    @Override

    public void start(Stage racine) throws Exception {
    	Main.stage = racine;
    	Parent root = FXMLLoader.load(getClass().getResource( "../vue/accueil.fxml"));
    	
    	compte = new GererCompte();
    	jeu = new GererJeu();

    	Image image = new Image(getClass().getResourceAsStream("/logo_bettonludotheque.png"));
    	racine.getIcons().add(image);
    	
    	//aller sur la page Gérer Compte depuis le bouton Gérer Compte
    	Button compteBouton = (Button) root.lookup("#compte");
    	compteBouton.setOnAction(event -> {
    	compte.initialiser();
    	});
    	
    	//aller sur la page Gérer Emprunts depuis le bouton Gérer Compte
    	Button empruntsBouton= (Button) root.lookup("#emprunt");
    	empruntsBouton.setOnAction(event -> {
    	compte.initialiser();
    	});
    	
    	//aller sur la page Gérer Jeux depuis le bouton Gérer Compte
    	Button jeuBouton = (Button) root.lookup("#jeu");
    	jeuBouton.setOnAction(event -> {
    	jeu.initialiser();
    	});
    	
    	Scene scene = new Scene(root, 1280, 800);     
    	racine.setScene(scene);
    	racine.setTitle("Ludothèque");
    	racine.show();

    }

    public static void main(String[] args) {
        launch(args);
        
    }
}

