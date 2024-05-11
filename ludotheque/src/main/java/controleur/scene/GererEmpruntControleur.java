
package controleur.scene;

import java.time.LocalDateTime;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Adherent;
import modele.Emprunt;
import modele.dao.AdherentDAO;
import modele.dao.EmpruntDAO;

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

    public void initialize() {
        initializeColumn();

        EmpruntDAO empruntDAO = EmpruntDAO.getInstance();
        List<Emprunt> empruntList = empruntDAO.readAll();

        for (Emprunt ad : empruntList) {
            tableEmprunt.getItems().add(ad);
        }
    }

    private void initializeColumn() {
        idJeuPhysique.setCellValueFactory(new PropertyValueFactory<>("idJeuPhysique"));
        idAdherent.setCellValueFactory(new PropertyValueFactory<>("idAdherent"));
        dateEmprunt.setCellValueFactory(new PropertyValueFactory<>("dateEmprunt"));
        dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));
        nomAdherent.setCellValueFactory(new PropertyValueFactory<>("nomAdherent"));
        nomJeuPhysique.setCellValueFactory(new PropertyValueFactory<>("nomJeuPhysique"));

    }
}