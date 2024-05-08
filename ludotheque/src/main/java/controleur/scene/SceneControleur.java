package controleur.scene;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controleur.App;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public abstract class SceneControleur {

	protected App app;

	public void setApp(App app) {
		this.app = app;
	}

	public void switchToAccueil() {
		app.switchToAccueil();
	}

	public void switchToGererAdherent() {
		app.switchToGererAdherent();
	}

	public void switchToGererJeu() {
		app.switchToGererJeu();
	}

	public void switchToAjouterJeu() {
		app.switchToAjouterJeu();
	}

	public void switchToinscriptionAdherent() {
		app.switchToinscriptionAdherent();
	}

//	public void switchToconnexionPage() {
//		
//		app.switchToconnexionPage();
//	}

	protected <T> void changeColumnBooleanValue(TableColumn<T, Boolean> col) {
		changeColumnBooleanValue(col, "Oui", "Non");
	}

	protected <T> void changeColumnBooleanValue(TableColumn<T, Boolean> col, String valTrue, String valFalse) {
		col.setCellFactory(column -> {
			return new TableCell<T, Boolean>() {
				@Override
				protected void updateItem(Boolean item, boolean empty) {
					super.updateItem(item, empty);
					// ne change pas les valeurs null
					if (empty || item == null) {
						setText(null);
						// update les true/false
					} else {
						// Set text based on the boolean value
						setText(item ? valTrue : valFalse);
					}
				}
			};
		});

	}


	/**
	 * Permet de formatter l'affichage d'une date dans une cell de tableView, avec le fromat "dd/MM/yyyy" par défaut
	 * @param <T> classe du modèle utilisée dans le controleur (ex : Adherent)
	 * @param col
	 * @return cell qui sera affichée
	 */
	protected <T> Callback<TableColumn<T, LocalDateTime>, TableCell<T, LocalDateTime>> formatDate(
			TableColumn<T, LocalDateTime> col) {
		return formatDate(col, "dd/MM/yyyy");
	}
	/**
	 * Permet de formatter l'affichage d'une date dans une cell de tableView
	 * @param <T> classe du modèle utilisée dans le controleur (ex : Adherent)
	 * @param col
	 * @param format ex : "dd.MM.yyyy"
	 * @return cell qui sera affichée
	 */
	protected <T> Callback<TableColumn<T, LocalDateTime>, TableCell<T, LocalDateTime>> formatDate(
			TableColumn<T, LocalDateTime> col, String format) {

		return new Callback<TableColumn<T, LocalDateTime>, TableCell<T, LocalDateTime>>() {
			@Override
			public TableCell<T, LocalDateTime> call(TableColumn<T, LocalDateTime> param) {
				return new TableCell<T, LocalDateTime>() {
					private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

					@Override
					protected void updateItem(LocalDateTime item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setText(null);
						} else {
							setText(formatter.format(item));
						}
					}
				};
			}
		};
	}

}