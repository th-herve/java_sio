package controleur.scene;

import java.time.LocalDateTime;
import java.util.List;

import exception.AdherentNotActive;
import exception.JeuNotDisponible;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Emprunt;
import modele.Reservation;
import modele.dao.EmpruntDAO;
import modele.dao.ReservationDAO;

public class GererReservationControleur extends SceneControleur {

	@FXML
	private TableView<Reservation> tableReservation;

	@FXML
	private TableColumn<Reservation, LocalDateTime> dateReservation;

	@FXML
	private TableColumn<Reservation, Boolean> estEmprunte;

	@FXML
	private TableColumn<Reservation, Integer> idAdherent;

	@FXML
	private TableColumn<Reservation, Integer> idJeuPhysique;

	@FXML
	private Label labelNewIdAdherent;

	@FXML
	private Label labelNewIdJeu;

	@FXML
	private Label mainLabel;

	@FXML
	private DatePicker newDateEmrpunte;

	@FXML
	private TextField newIdAdherent;

	@FXML
	private TextField newIdJeu;

	@FXML
	void ajouter(ActionEvent event) {

		int idJeuNew = Integer.parseInt(this.newIdJeu.getText());
		int idAdherentNew = Integer.parseInt(this.newIdAdherent.getText());

		Reservation ob;
		try {
			ob = new Reservation(idJeuNew, idAdherentNew);
			if (ReservationDAO.getInstance().create(ob)) {
				this.tableReservation.getItems().add(ob);
			}
		} catch (JeuNotDisponible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AdherentNotActive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void enregistrerReservation(ActionEvent event) {

		Reservation reservation = tableReservation.getFocusModel().getFocusedItem();
		if (reservation.enregistrerReservation()) {
			tableReservation.getItems().set(tableReservation.getFocusModel().getFocusedIndex(), reservation);
		}
	}

	@FXML
	void supprimer(ActionEvent event) {
		Reservation res = tableReservation.getFocusModel().getFocusedItem();

		if (ReservationDAO.getInstance().delete(res)) {
			int itemId = this.tableReservation.getFocusModel().getFocusedIndex();
			this.tableReservation.getItems().remove(itemId);
		}
	}

	public void initialize() {
		initializeColumn();

		this.forceIntegerOnTextField(newIdJeu);
		this.forceIntegerOnTextField(newIdAdherent);

		this.refreshTable();
	}

	private void initializeColumn() {
		idJeuPhysique.setCellValueFactory(new PropertyValueFactory<>("idJeuPhysique"));
		idAdherent.setCellValueFactory(new PropertyValueFactory<>("idAdherent"));
		dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
		this.estEmprunte.setCellValueFactory(new PropertyValueFactory<>("estEmprunte"));

		// change le format de la date affichée pour date inscription
		dateReservation.setCellFactory(this.formatDate(new TableColumn<Reservation, LocalDateTime>()));

	}

	public void refreshTable() {

		ReservationDAO reservationDao = ReservationDAO.getInstance();
		List<Reservation> reservationList = reservationDao.readAll();

		tableReservation.getItems().clear();
		;
		for (Reservation res : reservationList) {
			tableReservation.getItems().add(res);
		}
	}

}
