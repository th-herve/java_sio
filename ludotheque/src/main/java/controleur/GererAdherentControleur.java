package controleur;

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
	TableView<Adherent> adherent_tv;

	@FXML
	TableColumn<Adherent, Integer> idCol;

	@FXML
	TableColumn<Adherent, String> nomCol;

	@FXML
	TableColumn<Adherent, String> prenomCol;

	@FXML
	TableColumn<Adherent, String> emailCol;

	@FXML
	TableColumn<Adherent, String> telCol;

	@FXML
	TableColumn<Adherent, Boolean> estActifCol;

	@FXML
	TableColumn<Adherent, String> remarquesCol;

	@FXML
	TableColumn<Adherent, String> numCINCol;

	@FXML
	TableColumn<Adherent, LocalDateTime> dateInscriptionCol;

	public void initialize() {
		initializeColumn();

		AdherentDAO adherentDAO = AdherentDAO.getInstance();
		List<Adherent> adherentList = adherentDAO.readAll();

		for (Adherent ad : adherentList) {
			adherent_tv.getItems().add(ad);
		}
	}

	private void initializeColumn() {
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
		dateInscriptionCol.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
		remarquesCol.setCellValueFactory(new PropertyValueFactory<>("remarques"));
		numCINCol.setCellValueFactory(new PropertyValueFactory<>("numCIN"));

		estActifCol.setCellValueFactory(new PropertyValueFactory<>("estActif"));
		// change l'affiche de estActif de True/False à Oui/Non
		estActifCol.setCellFactory(column -> {
			return new TableCell<Adherent, Boolean>() {
				@Override
				protected void updateItem(Boolean item, boolean empty) {
					super.updateItem(item, empty);
					// ne change pas les valeurs null
					if (empty || item == null) {
						setText(null);
					// update les true/false
					} else {
						// Set the text based on the boolean value
						setText(item ? "Oui" : "Non");
					}
				}
			};
		});
	}

}