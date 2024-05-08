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
	
	
    public static void main(String[] args) {
        Application.launch(App.class, args);

    }
}
