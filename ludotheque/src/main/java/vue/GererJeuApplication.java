package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GererJeuApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/vue/fxml/gererJeu.fxml"));

		Scene scene = new Scene(root);

		stage.setTitle("Gerer les jeux");
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();
	}
}
