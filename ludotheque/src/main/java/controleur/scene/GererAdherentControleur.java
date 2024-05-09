package controleur.scene;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import modele.Adherent;
import modele.dao.AdherentDAO;

public class GererAdherentControleur extends SceneControleur {

	AdherentDAO adherentDAO = AdherentDAO.getInstance();

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
	TableColumn<Adherent, String> adresse;

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
		this.initializeColumn();
		this.tableAdherent.setEditable(true);
		this.refreshTable();

	}

	private void initializeColumn() {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		estActif.setCellValueFactory(new PropertyValueFactory<>("estActif"));
		remarques.setCellValueFactory(new PropertyValueFactory<>("remarques"));
		numCIN.setCellValueFactory(new PropertyValueFactory<>("numCIN"));
		dateInscription.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));

		// change l'affiche de estActif de True/False à Oui/Non
		this.changeColumnBooleanValue(estActif);

		// change le format de la date affichée pour date inscription
		dateInscription.setCellFactory(this.formatDate(new TableColumn<Adherent, LocalDateTime>())); 

		// ajout des cell editable
		nom.setCellFactory(TextFieldTableCell.forTableColumn());
		nom.setOnEditCommit(event -> {
			final String value = event.getNewValue();
			Adherent adherent = event.getRowValue();
			adherent.setNom(value);
			adherentDAO.update(adherent);
		});
		prenom.setCellFactory(TextFieldTableCell.forTableColumn());
		prenom.setOnEditCommit(event -> {
			final String value = event.getNewValue();
			Adherent adherent = event.getRowValue();
			adherent.setPrenom(value);
			adherentDAO.update(adherent);
		});
		email.setCellFactory(TextFieldTableCell.forTableColumn());
		email.setOnEditCommit(event -> {
			final String value = event.getNewValue();
			Adherent adherent = event.getRowValue();
			adherent.setEmail(value);
			adherentDAO.update(adherent);
		});
		adresse.setCellFactory(TextFieldTableCell.forTableColumn());
		adresse.setOnEditCommit(event -> {
			final String value = event.getNewValue();
			Adherent adherent = event.getRowValue();
			adherent.setAdresse(value);
			adherentDAO.update(adherent);
		});
		tel.setCellFactory(TextFieldTableCell.forTableColumn());
		tel.setOnEditCommit(event -> {
			final String value = event.getNewValue();
			Adherent adherent = event.getRowValue();
			adherent.setTel(value);
			adherentDAO.update(adherent);
		});
		remarques.setCellFactory(TextFieldTableCell.forTableColumn());
		remarques.setOnEditCommit(event -> {
			final String value = event.getNewValue();
			Adherent adherent = event.getRowValue();
			adherent.setRemarques(value);
			adherentDAO.update(adherent);
		});
		numCIN.setCellFactory(TextFieldTableCell.forTableColumn());
		numCIN.setOnEditCommit(event -> {
			final String value = event.getNewValue();
			Adherent adherent = event.getRowValue();
			adherent.setNumCIN(value);
			adherentDAO.update(adherent);
		});

	}

	/**
	 * Ajoute les adhérents de la bd dans la tableView
	 */
	public void refreshTable() {
		AdherentDAO adherentDAO = AdherentDAO.getInstance();
		List<Adherent> adherentList = adherentDAO.readAll();
		
		tableAdherent.getItems().clear();

		for (Adherent ad : adherentList) {
			tableAdherent.getItems().add(ad);
		}
	}

	/**
	 * Appelé quand on clique sur le bouton supprimer
	 */
	public void deleteAdherent() {
		int row = tableAdherent.getSelectionModel().getFocusedIndex();
		if (row >= 0) {
			Adherent adherent = tableAdherent.getItems().get(row);
			if (adherentDAO.delete(adherent)) {
				tableAdherent.getItems().remove(row);
				tableAdherent.getSelectionModel().clearSelection();
			}
		}
	}
	
	public void addToTableView(Adherent adherent) {
		tableAdherent.getItems().add(adherent);
	}
	

}