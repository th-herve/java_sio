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
        launch(args);
    }



}  