package controleur.scene;

import java.time.LocalDateTime;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Adherent;
import modele.Emprunt;
import modele.dao.AdherentDAO;
import modele.dao.EmpruntDAO;

public class HistoriqueEmpruntControleur extends SceneControleur{

	private int idAdh;
	private Adherent leAdherent;

	@FXML
	public TableView<Emprunt> tableHistoriqueEmprunt;
	@FXML
	public TableColumn<Emprunt, Integer> idJeuPhysique;
	@FXML
	public TableColumn<Emprunt, Integer> idAdherent;
	@FXML
	public TableColumn<Emprunt, LocalDateTime> dateEmprunt;
	@FXML
	public TableColumn<Emprunt, LocalDateTime> dateRetour;
	//	@FXML
	//	private Label mainLabel;

	public void initialize() {
		this.initializeColumn(); 
		this.refreshTable();

		dateEmprunt.setCellFactory(this.formatDate(new TableColumn<Emprunt, LocalDateTime>()));
		dateRetour.setCellFactory(this.formatDate(new TableColumn<Emprunt, LocalDateTime>()));
	}

	public void setUp(int idAdh) {
		this.idAdh = idAdh; 
		this.leAdherent = AdherentDAO.getInstance().read(idAdh);
		//	    mainLabel.setText("test");
		refreshTable();
	}

	public void refreshTable() {
		EmpruntDAO empruntDAO = EmpruntDAO.getInstance();

		tableHistoriqueEmprunt.getItems().clear();

		List<Emprunt> empruntList = empruntDAO.readByAdherent(idAdh);
		for (Emprunt emprunt : empruntList) {
			if (emprunt.getDateRetour() != null) {				
				tableHistoriqueEmprunt.getItems().add(emprunt);
			}
		}
	}

	private void initializeColumn() {
		idJeuPhysique.setCellValueFactory(new PropertyValueFactory<>("idJeuPhysique"));
		idAdherent.setCellValueFactory(new PropertyValueFactory<>("idAdherent"));
		dateEmprunt.setCellValueFactory(new PropertyValueFactory<>("dateEmprunt"));
		dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));			
	}
}
