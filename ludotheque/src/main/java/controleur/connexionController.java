package controleur;



import java.awt.TextField;

import javax.mail.search.AndTerm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Window;
import modele.dao.Connexion;

public class connexionController {

	
	@FXML
	private TextField identifiant;
	
	
	@FXML
	private TextField mdp;
	
	@FXML
	private Button connecter;
	
    public connexionController() {
		
    	Connexion connexion = new Connexion();
	}
	public void  connexionGo(ActionEvent event) {
		
		 Window owner = connecter.getScene().getWindow();
		 
		 if (identifiant.getText().isEmpty() || mdp.getText().isEmpty() ) {
			 
			 
			 showAlert(Alert.AlertType.ERROR , owner ,"Form Error!", "Veuillez remplir tous les champs" );
			return;
		}
		
		 
	}
	
	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		  Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.initOwner(owner);
	        alert.show();
	    }
		
	}
	

