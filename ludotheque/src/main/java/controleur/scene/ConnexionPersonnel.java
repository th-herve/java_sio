package controleur.scene;

import java.awt.print.Printable;
import java.net.PasswordAuthentication;
import java.util.Base64;

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
import modele.Personnel;
import modele.dao.Connexion;
import modele.dao.PersonneDAO;
import modele.dao.PersonnelDAO;

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




	private static  Personne loggedInPersonne;
    private PersonnelDAO personnelDAO;
    private PersonneDAO personneDAO;

	private Personnel personnel;

    public ConnexionPersonnel() {
        this.personnelDAO = PersonnelDAO.getInstance();
        this.personneDAO = PersonneDAO.getInstance();
    }

    @FXML
    public void Login(ActionEvent event) {
        Window owner = connecter.getScene().getWindow();

        String email = identification.getText();
        String password = mdp.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur de formulaire!", "Veuillez remplir tous les champs");
            return;
        }

        Personne personne = personnelDAO.readByEmail(email);
        String hashedPassword = personnelDAO.hashPassword(password);

        if (personne != null && hashedPassword != null) {
            // Check if the personne is an instance of Personnel
            if (personne instanceof Personnel) {
                loggedInPersonne =  personne; // Cast personne to Personnel and assign to loggedInPersonne
                showAlert(Alert.AlertType.INFORMATION, owner, "Succès", "Connexion réussie");
                identification.clear();
                mdp.clear();
	            
                app.switchToAccueil();
            } else {
                showAlert(Alert.AlertType.ERROR, owner, "Identifiant ou mot de passe incorrect", "Veuillez vérifier vos informations de connexion");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Identifiant ou mot de passe incorrect", "Veuillez vérifier vos informations de connexion");
        }
    }

//   @FXML
//   public void loggedInPersonne() {
//	   
//	   this.loggedInPersonne();
//   }

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

	


