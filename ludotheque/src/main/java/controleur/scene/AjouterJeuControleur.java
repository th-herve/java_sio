package controleur.scene;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.ValidationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import modele.Jeu;
import modele.dao.JeuDAO;

public class AjouterJeuControleur extends SceneControleur {

	private JeuDAO jeuDAO = JeuDAO.getInstance();

	@FXML
	private TextField ageMini;

	@FXML
	private ComboBox<Integer> annee;

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
		setUpComboAnnee();

		this.forceIntegerOnTextField(ageMini);
		this.forceIntegerOnTextField(dureeMini);
		this.forceIntegerOnTextField(dureeMaxi);
		this.forceIntegerOnTextField(nbrJoueursMini);
		this.forceIntegerOnTextField(nbrJoueursMaxi);
		this.forceFloatOnTextField(notBGG);
		this.forceFloatOnTextField(complexite);
	}

	public void ajouterJeu(ActionEvent event) {

		Window owner = ajouterBtn.getScene().getWindow();

		try {
			validateForm();

			Jeu jeu = getNewJeu();

			if (jeuDAO.create(jeu)) {
				showAlert(Alert.AlertType.CONFIRMATION, owner, "Création réussie!", "Nouveau jeu inséré avec succès");

				// update l'affichage des jeu
				GererJeuControleur ctl = (GererJeuControleur) this.app.getGererJeuPage().getControleur();
				ctl.addToTableView(jeu);

				this.ResetForm();
			} else {
				showAlert(Alert.AlertType.ERROR, owner, "Database Error!", "Échec de l'insertion du jeu");
			}
		} catch (ValidationException e) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getMessage());
		}

	}

	private boolean validateForm() throws ValidationException {
		String message = "";
		Boolean valid = true;

		// teste s'il y a un nom
		if (nom.getText().isEmpty()) {
			message += "- Veuillez spécifier un nom.";
			valid = false;
		}

		// test si complexité moins de 5
		if (getFloat(complexite) > 5) {
			message += "\n- La note de complexité doit être inférieure ou égale à 5.";
			valid = false;
		}

		// test si noteBGG moins de 10
		if (getFloat(notBGG) > 10) {
			message += "\n- La note BGG doit être inférieure ou égale à 10.";
			valid = false;
		}

		if (!valid) {
			throw new ValidationException(message);
		}
		return valid;
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
		annee.setValue(null);

	}

	private Jeu getNewJeu() {

		int newNbrJoueursMini = getInt(nbrJoueursMini);
		int newNbrJoueursMaxi = getInt(nbrJoueursMaxi);
		int newAgeMini = getInt(ageMini);
		int newDureeMini = getInt(dureeMini);
		int newDureeMaxi = getInt(dureeMaxi);
		float newComplexite = getFloat(complexite);
		float newNotBGG = getFloat(notBGG);
		int newAnnee = annee.getValue() != null ? annee.getValue() : 0;

		return new Jeu(nom.getText(), type.getText(), descriptif.getText(), 0, // quantité
				newNbrJoueursMini, newNbrJoueursMaxi, newAgeMini, newDureeMini, newDureeMaxi, newComplexite, newAnnee,
				newNotBGG);
	}

	private void setUpComboAnnee() {
		List<Integer> anneeList = new ArrayList<Integer>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);

		for (int i = currentYear; i >= 1900; i--) {
			anneeList.add(i);
		}

		annee.getItems().addAll(anneeList);
	}

	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

	private int getInt(TextField textField) {
		int newValue;
		try {
			newValue = Integer.parseInt(textField.getText());
		} catch (NumberFormatException e) {
			newValue = 0;
		}
		return newValue;
	}

	private float getFloat(TextField textField) {
		float newValue;
		try {
			newValue = Float.parseFloat(textField.getText());
		} catch (NumberFormatException e) {
			newValue = 0;
		}
		return newValue;
	}

}
