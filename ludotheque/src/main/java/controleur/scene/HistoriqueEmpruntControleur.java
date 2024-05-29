package controleur.scene;

import java.time.LocalDateTime;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Emprunt;
import modele.dao.EmpruntDAO;

public class HistoriqueEmpruntControleur extends SceneControleur{

	@FXML
	public TableView<Emprunt> tableHistoriqueEmprunt;
	@FXML
	public TableColumn<Emprunt, Integer> idJeuPhysique;
	@FXML
	public TableColumn<Emprunt, String> nomJeuPhysique;
	@FXML
	public TableColumn<Emprunt, Integer> idAdherent;
	@FXML
	public TableColumn<Emprunt, String> nomAdherent;
	@FXML
	public TableColumn<Emprunt, LocalDateTime> dateEmprunt;
	@FXML
	public TableColumn<Emprunt, LocalDateTime> dateRetour;

	public void initialize() {
		this.initializeColumn(); 
		this.refreshTable();
		
		dateEmprunt.setCellFactory(this.formatDate(new TableColumn<Emprunt, LocalDateTime>()));
		dateRetour.setCellFactory(this.formatDate(new TableColumn<Emprunt, LocalDateTime>()));
	}

	private void initializeColumn() {

		idJeuPhysique.setCellValueFactory(new PropertyValueFactory<>("idJeuPhysique"));
		nomJeuPhysique.setCellValueFactory(new PropertyValueFactory<>("nomJeuPhysique"));
		idAdherent.setCellValueFactory(new PropertyValueFactory<>("idAdherent"));
		nomAdherent.setCellValueFactory(new PropertyValueFactory<>("nomAdherent"));
		dateEmprunt.setCellValueFactory(new PropertyValueFactory<>("dateEmprunt"));
		dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));			
	}

	public void refreshTable() {
	    EmpruntDAO empruntDAO = EmpruntDAO.getInstance();
	    List<Emprunt> empruntList = empruntDAO.readAll();

	    tableHistoriqueEmprunt.getItems().clear();

	    for (Emprunt emprunt : empruntList) {
	        if (emprunt.getDateRetour() != null) {
	        	System.out.println(emprunt);
	            tableHistoriqueEmprunt.getItems().add(emprunt);
	        }
	    }
	}

}
