package controleur;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import vue.AccueilApplication;
import vue.GererJeuApplication;

public class AccueilControleur {
	
	@FXML
	private Label labelPrincipale;
	
	@FXML
	private Button btnGererJeu;
	
	public void initialize() {
		labelPrincipale.setText("Hello");
		
	btnGererJeu.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			try {
				new GererJeuApplication().start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
	});
	}
	
	
	private void loadGererJeuScene() {
        try {
            // Load the GererJeu.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GererJeu.fxml"));
            Parent root = loader.load();

            // Get the controller associated with the GererJeu.fxml file
            GererJeuControleur gererJeuController = loader.getController();

            // Access any methods or variables in GererJeuControleur if needed
            // gererJeuController.someMethod();

            // Create a new scene
            Scene scene = new Scene(root);
            
            // Get the current stage and set the new scene
            Stage stage = (Stage) btnGererJeu.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

	public void open(String[] args) {
		Application.launch(AccueilApplication.class, args);
	}

}
