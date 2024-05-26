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
	private Label userNameLabel;


	//	@FXML
	//    public void Logout(ActionEvent event) {
	//        ConnexionPersonnel connexionPersonnel = new ConnexionPersonnel();
	//        connexionPersonnel.Logout1(event);
	//    }

	@FXML
	public void loadUserData() {
		// Access the logged-in user's information
		System.out.println("dns accueil loaduserdata");
		if (ConnexionPersonnel.loggedInPersonne != null) {
			String nom = ConnexionPersonnel.loggedInPersonne.getNom();
			String prenom = ConnexionPersonnel.loggedInPersonne.getPrenom();
			userNameLabel.setText(nom + " " + prenom);
		} else {
			userNameLabel.setText("Les informations utilisateur ne sont pas disponibles");
		}
	}
	@FXML
	public void initialize() {
		loadUserData();    }

	@FXML
	public void Logout(ActionEvent event) {
		ConnexionPersonnel connexionPersonnel = new ConnexionPersonnel();
		connexionPersonnel.Logout1(event);
		//		app.switchToconnexionPage();
	}






}
