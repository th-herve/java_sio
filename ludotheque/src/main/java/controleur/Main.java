package controleur;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.dao.Connexion;

public class Main extends Application {	
		

	Connection co = Connexion.getInstance();
		
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	
<<<<<<< HEAD
    	InscriptionPersonneControleur controller = new InscriptionPersonneControleur(); 
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/inscriptionAdherent.fxml"));
=======
    	inscription_Personne_Controleur controller = new inscription_Personne_Controleur(); 
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/inscriptionPersonne.fxml"));
>>>>>>> d8125d67f4684c3b5e9d58f3bb8ac5a5e8a78a7c
    	loader.setController(controller);
    	Parent root = loader.load();
    	
    	//Parent root = FXMLLoader.load(getClass().getResource( "../vue/inscriptionPersonnel.fxml"));
    	
//    	Image icon = new Image(getClass().getResourceAsStream("./../../../images/logo_bettonludotheque.png"));
//        primaryStage.getIcons().add(icon);
        
        primaryStage.setScene(new Scene(root, 1280, 600));        
        primaryStage.setTitle("Betton Ludothèque");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}  