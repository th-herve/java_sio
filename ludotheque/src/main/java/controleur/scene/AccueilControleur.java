package controleur.scene;

import controleur.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modele.Personne;
import modele.Personnel;

public class AccueilControleur extends SceneControleur {

	@FXML
	private Label labelPrincipale;

	@FXML
	private Button btnGererJeu;

	@FXML
	private Button btnGererAdherent;
	@FXML
	private Button btnGererReservation;

	@FXML
	private Label userNameLabel;

	@FXML
	public void loadUserData() {

		if (ConnexionPersonnel.loggedInPersonne != null) {
			String nom = ConnexionPersonnel.loggedInPersonne.getNom();
			String prenom = ConnexionPersonnel.loggedInPersonne.getPrenom();
			userNameLabel.setText("Utilisateur : " + nom + " " + prenom);
		} else {
			userNameLabel.setText("Les informations de l'utilisateur ne sont pas disponibles");
		}
	}
	
	// exemple pour enlever le bouton gerer jeu si personne connectée n'est pas admin
	public void setPermission() {
		System.out.println( ((Personnel)ConnexionPersonnel.loggedInPersonne).toString());
		if (!ConnexionPersonnel.loggedInPersonne.estAdmin()) {
			this.btnGererReservation.setVisible(false);
		}
	}

	@FXML
	public void initialize() {
	}

	@FXML
	public void Logout(ActionEvent event) {
		ConnexionPersonnel connexionPersonnel = new ConnexionPersonnel();
		connexionPersonnel.logout(event);
		// app.switchToconnexionPage();
	}

}
