package controleur.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import modele.Personne;
import modele.Personnel;
import modele.dao.PersonneDAO;
import modele.dao.PersonnelDAO;

public class ConnexionPersonnel extends SceneControleur {

	@FXML
	private TextField identification;

	@FXML
	private PasswordField mdp;

	@FXML
	private Button connecter;

	@FXML
	private Button deconnecter;

	public static Personne loggedInPersonne;
	private PersonnelDAO personnelDAO;
	private Personnel personnel;


	@FXML
	public void initialize() {
		this.personnelDAO = PersonnelDAO.getInstance();
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

		this.personnel = this.personnelDAO.readByEmail(email);

		if (personnel != null) {

			// hachage le mot de passe depuis le table personnel
			String hashedPasswordFromDB = personnel.getMdp();

			// hachage le mot de passe entrée
			String hashedEnteredPassword = personnelDAO.hashPassword(password);

			if (hashedEnteredPassword.equals(hashedPasswordFromDB)) {

				// Passwords match, login avec succès
				ConnexionPersonnel.loggedInPersonne = personnel;

				showAlert(Alert.AlertType.INFORMATION, owner, "Succès", "Connexion réussie");

				identification.clear();
				mdp.clear();
				this.switchToAccueil();

			} else {
				// Passwords don't match, login failed
				showAlert(Alert.AlertType.ERROR, owner, "Identifiant ou mot de passe incorrect",
						"Veuillez vérifier vos informations de connexion");
			}
		} else {
			// Person not found in the database
			showAlert(Alert.AlertType.ERROR, owner, "Identifiant ou mot de passe incorrect",
					"Veuillez vérifier vos informations de connexion");
		}
	}

	@FXML
	public void logout(ActionEvent event) {
		Window owner = deconnecter.getScene().getWindow();

		// Effacez les informations d'identification actuellement stockées
		identification.clear();
		mdp.clear();

		// Redirigez l'utilisateur vers l'écran de connexion
		app.switchToconnexionPage();

		// Affichez un message de confirmation de déconnexion
		showAlert(Alert.AlertType.INFORMATION, owner, "Déconnexion réussie", "Vous avez été déconnecté avec succès.");

	}

	public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

}