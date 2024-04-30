package controleur;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import modele.Jeu;
import modele.dao.JeuDAO;

public class GererJeuControleur extends SceneControleur{

	@FXML
	public TableView<Jeu> tableJeu;
	@FXML
	public TableColumn<Jeu, String> id;
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
	public TableColumn<Jeu, String> complexite;
	@FXML
	public TableColumn<Jeu, String> noteBgg;


	public void initialize() {

		initializeColumn();

		JeuDAO jeuDAO = JeuDAO.getInstance();
		List<Jeu> jeuxList = jeuDAO.readAll();

		for (Jeu jeux : jeuxList) {
			tableJeu.getItems().addAll(jeux);
		}
		tableJeu.setEditable(true);
		
	}

//	private void handleKeyEvent(CellEditEvent<Jeu, String> event) {
//	  if (event.getEventType() == KeyEvent.KEY_TYPED && event.getCode() == KeyCode.ENTER) {
//	        System.out.println("test");
//	    }
//	}

	
	public void initializeColumn() {

	id.setCellValueFactory(new PropertyValueFactory<>("id"));
	id.addEventHandler(null, null);
	
	nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
	nom.setCellFactory(TextFieldTableCell.forTableColumn());
//	nom.setOnEditCommit(event -> handleKeyEvent(event));
	
	
	type.setCellValueFactory(new PropertyValueFactory<>("type"));
	type.setCellFactory(TextFieldTableCell.forTableColumn());
	//key listener qui appelle la fonction updatye
	
	descriptif.setCellValueFactory(new PropertyValueFactory<>("descriptif"));
	descriptif.setCellFactory(TextFieldTableCell.forTableColumn());
	
	quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
	quantite.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	
	nbrJoueursMini.setCellValueFactory(new PropertyValueFactory<>("nbr_joueurs_mini"));
	nbrJoueursMini.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	
	nbrJoueursMaxi.setCellValueFactory(new PropertyValueFactory<>("nbr_joueurs_maxi"));
	nbrJoueursMaxi.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	
	ageMini.setCellValueFactory(new PropertyValueFactory<>("ageMini"));
	ageMini.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	
	dureeMini.setCellValueFactory(new PropertyValueFactory<>("duree_mini"));
	dureeMini.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	
	dureeMaxi.setCellValueFactory(new PropertyValueFactory<>("duree_maxi"));
	dureeMaxi.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	
	annee.setCellValueFactory(new PropertyValueFactory<>("annee"));
	annee.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	
	complexite.setCellValueFactory(new PropertyValueFactory<>("complexite"));
//		complexite.setCellFactory(TextFieldTableCell.forTableColumn());
	
	noteBgg.setCellValueFactory(new PropertyValueFactory<>("note_bgg"));

	}
}
