package controleur.scene;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.converter.IntegerStringConverter;
import modele.Jeu;
import modele.dao.JeuDAO;

public class GererJeuControleur extends SceneControleur{

	@FXML
	public TableView<Jeu> tableJeu;
	@FXML
	public TableColumn<Jeu, Integer> id;
	@FXML
	public TableColumn<Jeu, String> nom;
	@FXML
	public TableColumn<Jeu, String> type;
	@FXML
	public TableColumn<Jeu, String> descriptif;
	@FXML
	public TableColumn<Jeu, Integer> quantite;
	@FXML
	public TableColumn<Jeu, Integer> nbrJoueursMini;
	@FXML
	public TableColumn<Jeu, Integer> nbrJoueursMaxi;
	@FXML
	public TableColumn<Jeu, Integer> ageMini;
	@FXML
	public TableColumn<Jeu, Integer> dureeMini;
	@FXML
	public TableColumn<Jeu, Integer> dureeMaxi;
	@FXML
	public TableColumn<Jeu, Integer> annee;
	@FXML
	public TableColumn<Jeu, Float> complexite;
	@FXML
	public TableColumn<Jeu, Float> noteBgg;

	@FXML
	public TextField searchJeu;
	@FXML
	private ObservableList<Jeu> originalData;

	public ActionEvent event;

	public void initialize() {

		initializeColumn();

		refreshTable();

	}

	public void initializeColumn() {

		id.setCellValueFactory(new PropertyValueFactory<>("id"));

		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		nom.setCellFactory(TextFieldTableCell.forTableColumn());
		nom.setOnEditCommit(event -> handleActionString(event));


		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		type.setCellFactory(TextFieldTableCell.forTableColumn());
		type.setOnEditCommit(event -> handleActionString(event));

		descriptif.setCellValueFactory(new PropertyValueFactory<>("descriptif"));
		descriptif.setCellFactory(TextFieldTableCell.forTableColumn());
		descriptif.setOnEditCommit(event -> handleActionString(event));

		quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		quantite.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		quantite.setOnEditCommit(event -> handleActionInt(event));

		nbrJoueursMini.setCellValueFactory(new PropertyValueFactory<>("nbr_joueurs_mini"));
		nbrJoueursMini.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		nbrJoueursMini.setOnEditCommit(event -> handleActionInt(event));

		nbrJoueursMaxi.setCellValueFactory(new PropertyValueFactory<>("nbr_joueurs_maxi"));
		nbrJoueursMaxi.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		nbrJoueursMaxi.setOnEditCommit(event -> handleActionInt(event));

		ageMini.setCellValueFactory(new PropertyValueFactory<>("ageMini"));
		ageMini.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		ageMini.setOnEditCommit(event -> handleActionInt(event));

		dureeMini.setCellValueFactory(new PropertyValueFactory<>("duree_mini"));
		dureeMini.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		dureeMini.setOnEditCommit(event -> handleActionInt(event));

		dureeMaxi.setCellValueFactory(new PropertyValueFactory<>("duree_maxi"));
		dureeMaxi.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		dureeMaxi.setOnEditCommit(event -> handleActionInt(event));

		annee.setCellValueFactory(new PropertyValueFactory<>("annee"));
		annee.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		annee.setOnEditCommit(event -> handleActionInt(event));

		complexite.setCellValueFactory(new PropertyValueFactory<>("complexite"));
		//complexite.setCellFactory(TextFieldTableCell.forTableColumn());

		noteBgg.setCellValueFactory(new PropertyValueFactory<>("note_bgg"));
	}

	private void handleActionString(CellEditEvent<Jeu, String> event) {
		TableColumn<Jeu, String> column = event.getTableColumn();
		Jeu jeu = event.getRowValue();

		if (column == nom) { 
			jeu.setNom(event.getNewValue()); 
		}
		if (column == descriptif) {
			jeu.setDescriptif(event.getNewValue());
		}
		if (column == type) {
			jeu.setType(event.getNewValue());
		}
		JeuDAO jeuDAO = JeuDAO.getInstance();
		jeuDAO.update(jeu);	
	}

	private void handleActionInt(CellEditEvent<Jeu, Integer> event) {
		TableColumn<Jeu, Integer> column = event.getTableColumn();
		Jeu jeu = event.getRowValue();

		if (column == quantite) { 
			jeu.setQuantite(event.getNewValue()); 
		}
		if (column == nbrJoueursMini) {
			jeu.setNbr_joueurs_mini(event.getNewValue());
		}
		if (column == nbrJoueursMaxi) {
			jeu.setNbr_joueurs_maxi(event.getNewValue());
		}
		if (column == ageMini) {
			jeu.setAgeMini(event.getNewValue());
		}
		if (column == dureeMini) {
			jeu.setDuree_mini(event.getNewValue());
		}
		if (column == dureeMaxi) {
			jeu.setDuree_maxi(event.getNewValue());
		}
		if (column == annee) {
			jeu.setAnnee(event.getNewValue());
		}
		JeuDAO jeuDAO = JeuDAO.getInstance();
		jeuDAO.update(jeu);	
	}

	public void refreshTable() {
		JeuDAO jeuDAO = JeuDAO.getInstance();
		List<Jeu> jeuxList = jeuDAO.readAll();

		tableJeu.getItems().clear();

		for (Jeu jeux : jeuxList) {
			tableJeu.getItems().addAll(jeux);
		}
		tableJeu.setEditable(true);
		tableJeu.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	}

	public void addToTableView(Jeu jeu) {
		tableJeu.getItems().add(jeu);
	}

	public void deleteJeu() {
		JeuDAO jeuDAO = JeuDAO.getInstance();
		int selectedJeu = tableJeu.getSelectionModel().getFocusedIndex();
		if (selectedJeu >= 0) {        	
			Jeu	jeu = tableJeu.getItems().get(selectedJeu);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Confirmation de suppression");
			alert.setHeaderText("Êtes-vous sûr de vouloir définitivement supprimer ce jeu ?");

			ButtonType buttonTypeOui = new ButtonType("Oui");
			ButtonType buttonTypeNon = new ButtonType("Non");
			alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);
			alert.showAndWait().ifPresent(buttonType -> {
				if (buttonType == buttonTypeOui) {
					ObservableList<Jeu> items = tableJeu.getItems();
					items.remove(selectedJeu);
					jeuDAO.delete(jeu);

				}
			});
		}
	}

	public void searchJeu(ActionEvent eventFilter) {
		String requete = searchJeu.getText().toLowerCase(); //on convertir pour pouvoir ignorer la casse;
		ObservableList<Jeu> data = tableJeu.getItems(); 
		ObservableList<Jeu> filtre = FXCollections.observableArrayList();

		for (Jeu item : data) {
			if (item.getNom().toLowerCase().contains(requete) || item.getType().equalsIgnoreCase(requete)) {
				filtre.add(item);
			}
		}

		if (filtre.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Pas de résultat");
			alert.setHeaderText("Aucun résultat trouvé !");
			alert.show();
		} else {
			tableJeu.setItems(filtre);
		}
	}
		
	public void openGererJeuPhysique() {
        Jeu jeu = tableJeu.getSelectionModel().getSelectedItem();
        this.switchToGererJeuPhysique(jeu.getId());
    }

	public void openGererJeuPhysique() {
		Jeu jeu = tableJeu.getSelectionModel().getSelectedItem();
		this.switchToGererJeuPhysique(jeu.getId());
	}

	//	private void handleActionFloat(CellEditEvent<Jeu, String> event) {
	//		TableColumn<Jeu, String> column = event.getTableColumn();
	//		Jeu jeu = event.getRowValue();
	//
	//		if (column == nom) { 
	//			jeu.setNom(event.getNewValue()); 
	//		}
	//		if (column == descriptif) {
	//			jeu.setDescriptif(event.getNewValue());
	//		}
	//		if (column == type) {
	//			jeu.setType(event.getNewValue());
	//		}
	//		JeuDAO jeuDAO = JeuDAO.getInstance();
	//		jeuDAO.update(jeu);	
	//	}

}
