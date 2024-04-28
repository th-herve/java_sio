package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vue.GererAdherentApplication;
import vue.GererJeuApplication;

public class AccueilControleur extends SceneControleur {

	@FXML
	private Label labelPrincipale;

	@FXML
	private Button btnGererJeu;

	@FXML
	private Button btnGererAdherent;

	public void initialize() {

		labelPrincipale.setText("Hello");

		btnGererJeu.setOnAction(this.switchScene(new GererJeuApplication()));
		btnGererAdherent.setOnAction(this.switchScene(new GererAdherentApplication()));
	}
	

}
