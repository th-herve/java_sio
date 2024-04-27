package controleur;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modele.Jeu;
import modele.dao.JeuDAO;

public class GererJeu extends Main{

	private AjouterJeu ajouterJeu;
	//		private VerifierJeu verifierJeu;

	@FXML
	private TableView<Jeu> tableJeu;

	private TableColumn<Jeu, String> id;
	private TableColumn<Jeu, String> nom;
	private TableColumn<Jeu, String> type;
	private TableColumn<Jeu, String> descriptif;
	private TableColumn<Jeu, String> quantite;
	private TableColumn<Jeu, String> jMini;
	private TableColumn<Jeu, String> jMaxi;
	private TableColumn<Jeu, String> ageMini;
	private TableColumn<Jeu, String> dMini;
	private TableColumn<Jeu, String> dMaxi;
	private TableColumn<Jeu, String> annee;
	private TableColumn<Jeu, String> complexite;
	private TableColumn<Jeu, String> noteBgg;


	public GererJeu() {
		super();
		this.id = new TableColumn<Jeu, String>("ID");
		this.nom = new TableColumn<Jeu, String>("NOM");
		this.type = new TableColumn<Jeu, String>("TYPE");
		this.descriptif = new TableColumn<Jeu, String>("DESCRIPTIF");
		this.quantite = new TableColumn<Jeu, String>("QUANTITE");
		this.jMini = new TableColumn<Jeu, String>("JMINI");
		this.jMaxi = new TableColumn<Jeu, String>("JMAXI");
		this.ageMini = new TableColumn<Jeu, String>("AGEMINI");
		this.dMini = new TableColumn<Jeu, String>("DMINI");
		this.dMaxi = new TableColumn<Jeu, String>("DMAXI");
		this.annee = new TableColumn<Jeu, String>("ANNEE");
		this.complexite = new TableColumn<Jeu, String>("COMPLEXITE");
		this.noteBgg = new TableColumn<Jeu, String>("NOTEBGG");
	}

	public void initialiser() {

		try {
			Parent loader = FXMLLoader.load(getClass().getResource("../vue/gererJeu.fxml"));

			initialiserTable();

			//						verifierJeu = new VerifierJeu();
			ajouterJeu = new AjouterJeu();


			//						Button verifJeu = (Button) loader.lookup("#verifier");
			//						verifJeu.setOnAction(event -> {
			//						verifierJeu.initialiser();
			//						});

			Button ajJeu = (Button) loader.lookup("#ajouter");
			ajJeu.setOnAction(event -> {
				ajouterJeu.initialiser();
			});

			Scene scene = new Scene(loader, 1280, 800);
			Main.stage.setScene(scene);
			Main.stage.show();



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialiserTable() {

		id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()+""));
		nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
		type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
		descriptif.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescriptif()));
		quantite.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantite()+""));
		jMini.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNbr_joueurs_mini()+""));
		jMaxi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNbr_joueurs_maxi()+""));
		ageMini.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAgeMini()+""));
		dMini.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuree_mini()+""));
		dMaxi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuree_maxi()+""));
		annee.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAnnee()+""));
		complexite.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComplexite()+""));
		noteBgg.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNote_bgg()+""));

		JeuDAO jeuDAO = new JeuDAO();
		Jeu jeux = jeuDAO.read(2);
		tableJeu.getItems().addAll(jeux);

	}

}
