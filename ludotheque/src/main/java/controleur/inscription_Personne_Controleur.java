package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import modele.Personne;
import modele.dao.PersonneDAO;

public class inscription_Personne_Controleur {

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField adresse;

    @FXML
    private TextField N_tel;

    @FXML
    private Button validerButton;

    @FXML
    private Button retour;

    private PersonneDAO personneDAO;
// ici la création de inctance de class PersonneDAO
    public inscription_Personne_Controleur() {
        this.personneDAO = PersonneDAO.getIntstance();
    }
// création d'un event qui permet  button de faire certain choses comme aleret et create dans la base de donner 
    @FXML
    public void validation(ActionEvent event) {
        Window owner = validerButton.getScene().getWindow();

        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() ||
                adresse.getText().isEmpty() || N_tel.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please fill in all fields");
            return;
        }

        // Creation d'un Personne object avec values depuis l'interface components
      //j'ai utilisé Interger.parsINT(N_tel.getText) , car  les value de Textfild est toujour string 
        Personne personne = new Personne(nom.getText(), prenom.getText(), email.getText(), adresse.getText(),Integer.parseInt(N_tel.getText()));

        // appelle  create method de PersonneDAO pour insert  Personne object dans la base de donner 
        if (personneDAO.create(personne)) {
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Inscription réussie!", "Personne insérée avec succès");
        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Database Error!", "Échec de l'insertion de Personne");
        }
    }

    @FXML
    public void retour(ActionEvent event) {
        // Handle return button action here
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
