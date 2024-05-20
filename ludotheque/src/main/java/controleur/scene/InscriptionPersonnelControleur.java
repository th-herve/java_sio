package controleur.scene;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.xml.bind.ValidationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import modele.Personne;
import modele.Personnel;
import modele.dao.PersonnelDAO;
import modele.dao.PersonneDAO;

public class InscriptionPersonnelControleur extends SceneControleur {

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField adresse;

    @FXML
    private TextField tel;

    @FXML
    private TextField password;

//    @FXML
//    private CheckBox estActive;
	@FXML
	private TextField estactif;

    @FXML
    private DatePicker dateEntree;

    @FXML
    private DatePicker dateSortie;

    @FXML
    private Button validerButton;

    @FXML
    private Button reset;

    private PersonnelDAO personnelDAO;
    private PersonneDAO personneDAO;

    public InscriptionPersonnelControleur() {
        this.personnelDAO = PersonnelDAO.getInstance();
        this.personneDAO = PersonneDAO.getInstance();
    }

    @FXML
    public void validation(ActionEvent event) throws SQLException {
        Window owner = validerButton.getScene().getWindow();

        try {
            validateForm();

            // Hash  password
            String hashedPassword = personnelDAO.hashPassword(password.getText());

            LocalDateTime entree = (dateEntree.getValue() != null) ? dateEntree.getValue().atStartOfDay() : null;
            LocalDateTime sortie = (dateSortie.getValue() != null) ? dateSortie.getValue().atStartOfDay() : null;

            // Créer un nouvel objet Personne
            Personne personne = personneDAO.readByEmail(email.getText());

            if (personne == null) {
                personne = new Personne(
                        nom.getText(),
                        prenom.getText(),
                        email.getText(),
                        adresse.getText(),
                        tel.getText()
                );

                // Ajouter des données à la table Personne
                personneDAO.create(personne);
            }

            // Créer un nouvel objet Personnel
            Personnel personnel = new Personnel(
                    nom.getText(),
                    prenom.getText(),
                    email.getText(),
                    adresse.getText(),
                    tel.getText(),
                    hashedPassword,
                    estactif.getText(),
                    entree,
                    sortie
            );

            // Ajouter des données à la table Personnel
            personnelDAO.create(personnel);

            showAlert(Alert.AlertType.CONFIRMATION, owner, "Inscription réussie!", "Personnel inséré avec succès");
        } catch (ValidationException e) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", e.getMessage());
        }
    }


    private boolean validateForm() throws ValidationException {
    	if(nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() ||
				adresse.getText().isEmpty() || tel.getText().isEmpty() || password.getText().isEmpty()) {

			throw new ValidationException("Merci de remplir tous les champs");
		}
		return true;
    }

    public void resetForm() {
        // Clear all form fields
        nom.clear();
        prenom.clear();
        email.clear();
        adresse.clear();
        tel.clear();
        password.clear();
        estactif.clear();
        dateEntree.setValue(null);
        dateSortie.setValue(null);
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
