package controleur.scene;

import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modele.Adherent;
import modele.ProcheAdherent;
import modele.dao.AdherentDAO;

public class GererProcheControleur extends SceneControleur {

	AdherentDAO adherentDAO = AdherentDAO.getInstance();
	Adherent currentAdheret;

	@FXML
	TableView<ProcheAdherent> tableProche;

	@FXML
	private TableColumn<ProcheAdherent, String> nom;

	
	public void initialize() {
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		this.tableProche.setEditable(true);
	}

	public void setUp(Adherent adherent) {
		this.currentAdheret = adherent;
		this.refreshTable();
	}

	public void refreshTable() {
		tableProche.getItems().clear();
		Set<String> procheList = currentAdheret.getProches();

		System.out.println(currentAdheret.getProches());
		for (String nom : procheList) {
			tableProche.getItems().add(new ProcheAdherent(currentAdheret.getId(), nom));
		}
	}

}
