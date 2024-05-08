package controleur.scene;

import javax.xml.bind.ValidationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import modele.Adherent;
import modele.Jeu;
import modele.dao.JeuDAO;

public class AjouterJeuControleur extends SceneControleur {

	private JeuDAO jeuDAO = JeuDAO.getInstance();

	@FXML
	private TextField ageMini;

	@FXML
	private TextField annee;

	@FXML
	private TextField complexite;

	@FXML
	private TextArea descriptif;

	@FXML
	private TextField dureeMaxi;

	@FXML
	private TextField dureeMini;

	@FXML
	private TextField nbrJoueursMaxi;

	@FXML
	private TextField nbrJoueursMini;

	@FXML
	private TextField nom;

	@FXML
	private TextField notBGG;

	@FXML
	private TextField type;

	@FXML
	private Button ajouterBtn;

	public void initialize() {
	}

	public void validation(ActionEvent event) {

		Window owner = ajouterBtn.getScene().getWindow();

		try {
			validateForm();

			Jeu jeu = getNewJeu();

			// appelle create method de PersonneDAO pour insert Personne object dans la base
			// de donner
			if (jeuDAO.create(jeu)) {
				showAlert(Alert.AlertType.CONFIRMATION, owner, "Création réussie!", "Nouveau jeu inséré avec succès");

				// update l'affichage des jeu
				GererAdherentControleur ad = (GererAdherentControleur) this.app.getGererAdherentPage().getControleur();
				ad.refreshTable();

				this.ResetForm();
			} else {
				showAlert(Alert.AlertType.ERROR, owner, "Database Error!", "Échec de l'insertion du jeu");
			}
		} catch (ValidationException e) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getMessage());
		}

	}

	private boolean validateForm() throws ValidationException {
		if (nom.getText().isEmpty()) {
			throw new ValidationException("Veuillez spécifier un nom.");
		}
		return true;
	}

	public void ResetForm() {
		nom.clear();
		type.clear();
		descriptif.clear();
		nbrJoueursMini.clear();
		nbrJoueursMaxi.clear();
		dureeMini.clear();
		dureeMaxi.clear();
		ageMini.clear();
		complexite.clear();
		notBGG.clear();
		annee.clear();

	}

	private Jeu getNewJeu() {

		int newNbrJoueursMini = Integer.parseInt(nbrJoueursMini.getText());
		int newNbrJoueursMaxi = Integer.parseInt(nbrJoueursMaxi.getText());
		int newAgeMini = Integer.parseInt(ageMini.getText());
		int newDureeMini = Integer.parseInt(dureeMini.getText());
		int newDureeMaxi = Integer.parseInt(dureeMaxi.getText());
		float newComplexite = Float.parseFloat(complexite.getText());
		float newNotBGG = Float.parseFloat(notBGG.getText());
		int newAnnee = Integer.parseInt(annee.getText());

		return new Jeu(nom.getText(), type.getText(), descriptif.getText(), 0, // quantité
				newNbrJoueursMini, newNbrJoueursMaxi, newAgeMini, newDureeMini, newDureeMaxi, newComplexite, newNotBGG, newAnnee);
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
