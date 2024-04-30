package controleur;

import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Jeu;
import modele.dao.JeuDAO;

public class GererJeuControleur extends SceneControleur{

	//	private AjouterJeu ajouterJeu;
	//	private VerifierJeu verifierJeu;

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
	public TableColumn<Jeu, String> quantite;
	@FXML
	public TableColumn<Jeu, String> nbrJoueursMini;
	@FXML
	public TableColumn<Jeu, String> nbrJoueursMaxi;
	@FXML
	public TableColumn<Jeu, String> ageMini;
	@FXML
	public TableColumn<Jeu, String> dureeMini;
	@FXML
	public TableColumn<Jeu, String> dureeMaxi;
	@FXML
	public TableColumn<Jeu, String> annee;
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

	public void initializeColumn() {

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		descriptif.setCellValueFactory(new PropertyValueFactory<>("descriptif"));
		quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		nbrJoueursMini.setCellValueFactory(new PropertyValueFactory<>("nbr_joueurs_mini"));
		nbrJoueursMaxi.setCellValueFactory(new PropertyValueFactory<>("nbr_joueurs_maxi"));
		ageMini.setCellValueFactory(new PropertyValueFactory<>("ageMini"));
		dureeMini.setCellValueFactory(new PropertyValueFactory<>("duree_mini"));
		dureeMaxi.setCellValueFactory(new PropertyValueFactory<>("duree_maxi"));
		annee.setCellValueFactory(new PropertyValueFactory<>("annee"));
		complexite.setCellValueFactory(new PropertyValueFactory<>("complexite"));
		noteBgg.setCellValueFactory(new PropertyValueFactory<>("note_bgg"));

	}
}
