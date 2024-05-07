package controleur.scene;

import java.awt.print.Printable;
import java.net.PasswordAuthentication;

import controleur.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import modele.Personne;
import modele.dao.Connexion;
import modele.dao.PersonneDAO;

public class ConnexionPersonnel extends SceneControleur {

	@FXML
	private TextField identification;

//	@FXML
//	private TextField mdp;
	
	@FXML
	private PasswordField mdp;
	
	@FXML
	private Button connecter;
	
	@FXML
	
	private Button deconnecter;

	
	
	@FXML
	 public void Login(ActionEvent event) {
        Window owner = connecter.getScene().getWindow();

        // Check if the identification and password fields are not empty
        if (identification.getText().isEmpty() || mdp.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur de formulaire!", "Veuillez remplir tous les champs");
        } else {
            String email = identification.getText();
            String password = mdp.getText();

            // Retrieve the user information based on the email
            PersonneDAO personneDAO = PersonneDAO.getIntstance();
            Personne user = personneDAO.readByEmail(email);

            if (user != null && password.equals(user.getMdp())) {
                showAlert(Alert.AlertType.INFORMATION, owner, "Succès", "Connexion réussie");
//                identification.clear();
//                mdp.clear();
                // Switch to Accueil page
                app.switchToAccueil();
            } else {
                showAlert(Alert.AlertType.ERROR, owner, "Identifiant ou mot de passe incorrect", "Veuillez vérifier vos informations de connexion");
            }
        }
    }
//
//	@FXML
//	public void Login(ActionEvent event) {
//		Window owner = connecter.getScene().getWindow();
//
//
//		// Check if the identification and password fields are not empty
//		if (identification.getText().isEmpty() || mdp.getText().isEmpty()) {
//			showAlert(Alert.AlertType.ERROR, owner, "Erreur de formulaire!", "Veuillez remplir tous les champs");        
//		}
//		else {
//			
//			String email = identification.getText();
//			String password = mdp.getText();
//			boolean userExists = Connexion.userExists(email);
//
//			if (userExists) {
//				
//				// ici j'ai mise mot de pass default adminsio24
//				if (password.equals("adminsio24") ) {
//					//si ce n'est pas la meme mot de passe ; lancer un alert 
//					showAlert(Alert.AlertType.INFORMATION, owner, "Succès",  "Connexion réussie");
//					 identification.clear();
//					 mdp.clear();
//					//si la connexion est bon switch To Accueil page 
//					app.switchToAccueil();
//
//					
//				}else {
//					
//					showAlert(Alert.AlertType.INFORMATION, owner, "Mot de passe incorrect", "Le mot de passe est incorrect");
//				}
//				
//				
//				
//			} else {
//				showAlert(Alert.AlertType.ERROR, owner, "Utilisateur introuvable", "L'utilisateur fourni n'existe pas");
//			}
//		}
//
//
//	}
//	
	@FXML
	public void Logout1(ActionEvent event) {
		
	    Window owner = deconnecter.getScene().getWindow();

	    // Effacez les informations d'identification actuellement stockées
	    identification.clear();
	    mdp.clear();

	    // Redirigez l'utilisateur vers l'écran de connexion
	    app.switchToconnexionPage();

	    // Affichez un message de confirmation de déconnexion
	    showAlert(Alert.AlertType.INFORMATION, owner, "Déconnexion réussie", "Vous avez été déconnecté avec succès.");
	     System.out.println("ok");
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
