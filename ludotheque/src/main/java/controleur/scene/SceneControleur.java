package controleur.scene;

import controleur.App;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import modele.Adherent;

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
	
	public void switchToinscriptionAdherent() {
		app.switchToinscriptionAdherent();
	}
	
//	public void switchToconnexionPage() {
//		
//		app.switchToconnexionPage();
//	}
	
	protected<T> void changeColumnBooleanValue(TableColumn<T, Boolean> col) {
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


}