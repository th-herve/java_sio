package controleur.scene;

import java.time.LocalDateTime;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Emprunt;
import modele.Evenement;
import modele.dao.EvenementDAO;

public class GererEvenementControleur extends SceneControleur {


    @FXML
    private TableView<Evenement> tableEvenement;

    @FXML
    private TableColumn<Evenement, LocalDateTime> dateEvenement;

    @FXML
    private TableColumn<Evenement, Integer> id;

    @FXML
    private TableColumn<Evenement, Integer> idJeu;

    @FXML
    private Label labelNewIdAdherent;

    @FXML
    private Label labelNewIdJeu;

    @FXML
    private Label mainLabel;

    @FXML
    private DatePicker newDateEvenement;

    @FXML
    private TextField newIdJeu;

    @FXML
    private TextField newNom;

    @FXML
    private TableColumn<?, ?> nom;

    @FXML
    void ajouterEvenement(ActionEvent event) {
    		LocalDateTime dateNew = this.newDateEvenement.getValue().atStartOfDay();
    		String nomNew = this.newNom.getText();
    		int idJeuNew = Integer.parseInt(this.newIdJeu.getText());
    		
    		Evenement ev = new Evenement(nomNew, dateNew, idJeuNew);
    		if (EvenementDAO.getInstance().create(ev)) {
    			this.tableEvenement.getItems().add(ev);
    		}
    }

	public void initialize() {
		initializeColumn();

		this.refreshTable();
	}
	
	private void initializeColumn() {
		this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		this.dateEvenement.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
		this.idJeu.setCellValueFactory(new PropertyValueFactory<>("idJeu"));

		// change le format de la date affichée pour date inscription
		dateEvenement.setCellFactory(this.formatDate(new TableColumn<Evenement, LocalDateTime>()));

	}

	public void refreshTable() {

		EvenementDAO empruntDAO = EvenementDAO.getInstance();
		List<Evenement> evenementList = empruntDAO.readAll();

		tableEvenement.getItems().clear();;
		for (Evenement ev : evenementList) {
			tableEvenement.getItems().add(ev);
		}
	}

}
