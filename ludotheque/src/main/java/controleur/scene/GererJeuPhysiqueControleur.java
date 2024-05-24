package controleur.scene;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import modele.Jeu;
import modele.JeuPhysique;
import modele.dao.JeuDAO;
import modele.dao.JeuPhysiqueDAO;

/**
 * Appeler la fonction setUp(id) avec l'id d'un jeu avant d'afficher cette page
 */
public class GererJeuPhysiqueControleur extends SceneControleur {

	// jeu pour lequel on affiche les jeux physiques
	private int idJeu;
	private Jeu leJeu;

	private JeuPhysiqueDAO jeuPhysiqueDAO = JeuPhysiqueDAO.getInstance();

	@FXML
	private TableView<JeuPhysique> tableJeuPhysique;

	@FXML
	private TableColumn<JeuPhysique, Boolean> estDisponible;

	@FXML
	private TableColumn<JeuPhysique, String> etat;

	@FXML
	private TableColumn<JeuPhysique, Integer> id;

	@FXML
	private Label mainLabel;

	@FXML
	private CheckBox estDisponibleAjout;

	@FXML
	private TextArea etatAjout;

	public void initialize() {
		initializeColumn();
		tableJeuPhysique.setEditable(true);
	}

	/**
	 * Appeler cette fonction avant l'affichage de la page
	 * 
	 * @param idJeu l'id d'un jeu (!! pas d'un jeu physique)
	 */
	public void setUp(int idJeu) {
		this.idJeu = idJeu;
		this.leJeu = JeuDAO.getInstance().read(idJeu);
		mainLabel.setText(leJeu.getNom());
		refreshTable();
	}

	/**
	 * Ajoute dans la table, les jeux physique de la bd pour l'id jeu donné
	 */
	public void refreshTable() {
		List<JeuPhysique> jeuList = jeuPhysiqueDAO.readByIdJeu(this.idJeu);

		for (JeuPhysique jeu : jeuList) {
			tableJeuPhysique.getItems().add(jeu);
		}
	}

	/**
	 * Appelé quand on clique sur le bouton ajouter
	 */
	public void ajouterJeuPhysique() {
		JeuPhysique jeu = new JeuPhysique(idJeu, etatAjout.getText(), estDisponibleAjout.isSelected());
		if (jeuPhysiqueDAO.create(jeu)) {
			tableJeuPhysique.getItems().add(jeu);
		}
	}

	/**
	 * Appelé quand on clique sur le bouton supprimer
	 */
	public void deleteJeuPhysique() {
		int row = tableJeuPhysique.getSelectionModel().getFocusedIndex();
		if (row >= 0) {
			JeuPhysique jeu = tableJeuPhysique.getItems().get(row);
			if (jeuPhysiqueDAO.delete(jeu)) {
				tableJeuPhysique.getItems().remove(row);
				tableJeuPhysique.getSelectionModel().clearSelection();
			}
		}
	}

	private void initializeColumn() {

		// === id ===
		id.setCellValueFactory(new PropertyValueFactory<>("id"));

		// === etat ===
		etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
		etat.setCellFactory(TextFieldTableCell.forTableColumn());
		etat.setOnEditCommit(event -> {
			final String value = event.getNewValue();
			JeuPhysique jeu = event.getRowValue();
			jeu.setEtat(value);
			jeuPhysiqueDAO.update(jeu);
		});

		// === estDisponible ===
		estDisponible.setCellValueFactory(new PropertyValueFactory<>("estDisponible"));
		// change l'affiche de estDisponible de True/False à Oui/Non
		this.changeColumnBooleanValue(estDisponible);
	}

}