package controleur.scene;

import controleur.App;
import javafx.application.Application;

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
	
}