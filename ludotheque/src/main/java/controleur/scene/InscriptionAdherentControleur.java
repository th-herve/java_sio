package controleur.scene;

import javax.xml.bind.ValidationException;

import org.bouncycastle.crypto.ec.ECNewPublicKeyTransform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import modele.Adherent;
import modele.dao.AdherentDAO;


public class InscriptionAdherentControleur extends SceneControleur {

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

	@FXML
	private TextField email;

	@FXML
	private TextField numCIN;

	@FXML
	private TextField remarques;

	@FXML
	private TextField adresse;

	@FXML
	private CheckBox estactive;

	@FXML
	private CheckBox desactive;

	@FXML
	private DatePicker dateInscription ;

	@FXML
	private TextField N_tel;

	@FXML
	private Button validerButton;

	@FXML
	private Button reset;

	@FXML
	private Button retour;

	private AdherentDAO adherentDAO;

	// ici la création de inctance de class PersonneDAO
	public InscriptionAdherentControleur() {
		//        
		this.adherentDAO = AdherentDAO.getInstance();
	}
	// création d'un event qui permet  button de faire certain choses comme aleret et create dans la base de donner 
	@FXML
	public void validation(ActionEvent event) {
		Window owner = validerButton.getScene().getWindow();

		try {
			validateForm() ;


			// Creation d'un Adherent object avec values depuis l'interface components
			Adherent adherent = new Adherent(
					nom.getText(),
					prenom.getText(),
					email.getText(),
					adresse.getText(),
					N_tel.getText(),
					estactive.isSelected(),
					remarques.getText(),
					numCIN.getText(),
					dateInscription.getValue() != null ? dateInscription.getValue().atStartOfDay() : null
					);
			// appelle  create method de PersonneDAO pour insert  Personne object dans la base de donner 
			if (adherentDAO.create(adherent)) {
				showAlert(Alert.AlertType.CONFIRMATION, owner, "Inscription réussie!", "Adherent insérée avec succès");
			} else {
				showAlert(Alert.AlertType.ERROR, owner, "Database Error!", "Échec de l'insertion de Adherent");
			}
		} catch (ValidationException e) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getMessage());
		}

	}
	private boolean validateForm() throws ValidationException{
		if(nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() ||
				adresse.getText().isEmpty() || N_tel.getText().isEmpty() || remarques.getText().isEmpty() || numCIN.getText().isEmpty()) {

			throw new ValidationException("Please fill in all fields");
		}
		return true;
	}

	public void ResetForm() {
		nom.clear();
		prenom.clear();
		email.clear();
		adresse.clear();
		N_tel.clear();
		remarques.clear();
		numCIN.clear();
		estactive.setSelected(false);
		dateInscription.setValue(null);



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
