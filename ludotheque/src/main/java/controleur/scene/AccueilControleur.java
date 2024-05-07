package controleur.scene;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AccueilControleur extends SceneControleur {

	@FXML
	private Label labelPrincipale;

	@FXML
	private Button btnGererJeu;

	@FXML
	private Button btnGererAdherent;
	
//	@FXML
//    public void Logout(ActionEvent event) {
//        ConnexionPersonnel connexionPersonnel = new ConnexionPersonnel();
//        connexionPersonnel.Logout1(event);
//    }
	
	@FXML
    public void Logout(ActionEvent event) {
        ConnexionPersonnel connexionPersonnel = new ConnexionPersonnel();
        connexionPersonnel.Logout1(event);
//		app.switchToconnexionPage();
	}
    


	public void initialize() {

	}
	
	
	
}
