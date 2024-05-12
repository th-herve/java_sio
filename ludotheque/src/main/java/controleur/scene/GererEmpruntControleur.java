
package controleur.scene;

import java.time.LocalDateTime;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import modele.Adherent;
import modele.Emprunt;
import modele.JeuPhysique;
import modele.dao.AdherentDAO;
import modele.dao.EmpruntDAO;
import modele.dao.JeuPhysiqueDAO;

public class GererEmpruntControleur extends SceneControleur {

	@FXML
	TableView<Emprunt> tableEmprunt;

	@FXML
	TableColumn<Emprunt, Integer> idAdherent;

	@FXML
	TableColumn<Emprunt, Integer> idJeuPhysique;

	@FXML
	TableColumn<Emprunt, LocalDateTime> dateEmprunt;

	@FXML
	TableColumn<Emprunt, LocalDateTime> dateRetour;

	@FXML
	private TableColumn<Emprunt, String> nomAdherent;

	@FXML
	private TableColumn<Emprunt, String> nomJeuPhysique;

	@FXML
	private Label labelNewIdAdherent;

	@FXML
	private Label labelNewIdJeu;

	@FXML
	private TextField newIdAdherent;

	@FXML
	private TextField newIdJeu;

	public void initialize() {
		initializeColumn();

		this.forceIntegerOnTextField(newIdJeu);
		this.forceIntegerOnTextField(newIdAdherent);
		searchAndDisplayJeu(newIdJeu, labelNewIdJeu);
		searchAndDisplayAdherent(newIdAdherent, labelNewIdAdherent);

		this.refreshTable();
	}

	private void initializeColumn() {
		idJeuPhysique.setCellValueFactory(new PropertyValueFactory<>("idJeuPhysique"));
		idAdherent.setCellValueFactory(new PropertyValueFactory<>("idAdherent"));
		dateEmprunt.setCellValueFactory(new PropertyValueFactory<>("dateEmprunt"));
		dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));
		nomAdherent.setCellValueFactory(new PropertyValueFactory<>("nomAdherent"));
		nomJeuPhysique.setCellValueFactory(new PropertyValueFactory<>("nomJeuPhysique"));

		// change le format de la date affichée pour date inscription
		dateEmprunt.setCellFactory(this.formatDate(new TableColumn<Emprunt, LocalDateTime>()));
		dateRetour.setCellFactory(this.formatDate(new TableColumn<Emprunt, LocalDateTime>()));

	}

	public void refreshTable() {

		EmpruntDAO empruntDAO = EmpruntDAO.getInstance();
		List<Emprunt> empruntList = empruntDAO.readAll();

		for (Emprunt ad : empruntList) {
			tableEmprunt.getItems().add(ad);
		}
	}

	private void searchAndDisplayJeu(TextField textField, Label label) {

		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				String nomJeu = "";
				if (newValue != "") {
					int id = Integer.parseInt(textField.getText());

					JeuPhysique jeuP = JeuPhysiqueDAO.getInstance().read(id);
					if (jeuP != null) {
						nomJeu = jeuP.getJeu().getNom();
					} else {
						nomJeu = "Id jeu introuvable";
					}
				}
				label.setText(nomJeu);
			}
		});
	}

	private void searchAndDisplayAdherent(TextField textField, Label label) {

		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				String nomAdherent = "";
				if (newValue != "") {
					int id = Integer.parseInt(textField.getText());

					Adherent adherent = AdherentDAO.getInstance().read(id);
					if (adherent != null) {
						nomAdherent = adherent.getNom();
					} else {
						nomAdherent = "Id adhérent introuvable";
					}
				}
				label.setText(nomAdherent);
			}
		});
	}

	private boolean isNewEmpruntFormValid() {

		int idJeu = getNewJeuId();
		int idAdherent = getNewAdherentId();

		JeuPhysique jeuP = JeuPhysiqueDAO.getInstance().read(idJeu);
		Adherent adherent = AdherentDAO.getInstance().read(idAdherent);

		return jeuP != null && adherent != null;

	}

	private Emprunt getNewEmprunt() {

		Emprunt emprunt = null;

		if (isNewEmpruntFormValid()) {
			int idJeu = Integer.parseInt(newIdJeu.getText());
			int idAhderent = Integer.parseInt(newIdAdherent.getText());
			emprunt = new Emprunt(idJeu, idAhderent);
		}

		return emprunt;
	}

	/**
	 * Appelé quand on clique sur le bouton ajouter
	 */
	public void ajouterEmprunt() {

		Window owner = tableEmprunt.getScene().getWindow();

		Emprunt emprunt = getNewEmprunt();

		if (emprunt != null) {

			boolean created = EmpruntDAO.getInstance().create(emprunt);

			if (created) {

				this.showAlert(AlertType.CONFIRMATION, owner, "Création réussie!", "Nouvel emprunt enregistré.");
				newIdAdherent.clear();
				newIdJeu.clear();

				this.addToTableView(emprunt);
			} else {
				this.showAlert(AlertType.ERROR, owner, "Erreur",
						"Une erreur est survenu, impossible d'enregistrer l'emprunt.");
			}
		} else {

			this.showAlert(AlertType.ERROR, owner, "Erreur",
					"Impossible d'enregister le nouvel emprunt, vérifier les id fournis.");
		}
	}

	/**
	 * @return jeuPhysique id or -1 if invalid
	 */
	private int getNewJeuId() {
		int idJeu = -1;
		if (newIdJeu.getText() != "") {
			idJeu = Integer.parseInt(newIdJeu.getText());
		}
		return idJeu;
	}

	/**
	 * @return adherent id enter by user or -1 if invalid
	 */
	private int getNewAdherentId() {
		int idAdherent = -1;

		if (newIdAdherent.getText() != "") {
			idAdherent = Integer.parseInt(newIdAdherent.getText());
		}
		return idAdherent;
	}

	public void addToTableView(Emprunt emprunt) {
		tableEmprunt.getItems().add(emprunt);
	}

}