package controleur.scene;

import java.time.LocalDateTime;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Adherent;
import modele.dao.AdherentDAO;

public class GererAdherentControleur extends SceneControleur {

	@FXML
	TableView<Adherent> tableAdherent;

	@FXML
	TableColumn<Adherent, Integer> id;

	@FXML
	TableColumn<Adherent, String> nom;

	@FXML
	TableColumn<Adherent, String> prenom;

	@FXML
	TableColumn<Adherent, String> email;

	@FXML
	TableColumn<Adherent, String> tel;

	@FXML
	TableColumn<Adherent, Boolean> estActif;

	@FXML
	TableColumn<Adherent, String> remarques;

	@FXML
	TableColumn<Adherent, String> numCIN;

	@FXML
	TableColumn<Adherent, LocalDateTime> dateInscription;

	public void initialize() {
		initializeColumn();

		AdherentDAO adherentDAO = AdherentDAO.getInstance();
		List<Adherent> adherentList = adherentDAO.readAll();

		for (Adherent ad : adherentList) {
			tableAdherent.getItems().add(ad);
		}
	}

	private void initializeColumn() {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		dateInscription.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
		remarques.setCellValueFactory(new PropertyValueFactory<>("remarques"));
		numCIN.setCellValueFactory(new PropertyValueFactory<>("numCIN"));
		estActif.setCellValueFactory(new PropertyValueFactory<>("estActif"));

		// change l'affiche de estActif de True/False à Oui/Non
		estActif.setCellFactory(column -> {
			return new TableCell<Adherent, Boolean>() {
				@Override
				protected void updateItem(Boolean item, boolean empty) {
					super.updateItem(item, empty);
					// ne change pas les valeurs null
					if (empty || item == null) {
						setText(null);
					// update les true/false
					} else {
						// Set text based on the boolean value
						setText(item ? "Oui" : "Non");
					}
				}
			};
		});
	}

}