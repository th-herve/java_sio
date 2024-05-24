package controleur;

import java.io.IOException;

import controleur.scene.SceneControleur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Page {

	private FXMLLoader loader;

	private String fxmlFile;

	// variables avec chaque vue
	private Parent vue;

	// variables avec chaque scene
	private Scene scene;

	// variables avec chaque contrôleur
	private SceneControleur controleur;

	public Page(App app, String fxmlFile) throws IOException {
		super();
		this.loader = new FXMLLoader(getClass().getResource("/vue/" + fxmlFile));
		this.vue = this.loader.load();
		this.scene = new Scene(this.vue);
		this.controleur = this.loader.getController();
		this.controleur.setApp(app);
	}

	public Parent getVue() {
		return vue;
	}

	public Scene getScene() {
		return scene;
	}

	public SceneControleur getControleur() {
		return this.controleur;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}


}
