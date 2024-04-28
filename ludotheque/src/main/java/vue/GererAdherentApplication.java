package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GererAdherentApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/vue/fxml/gererAdherent.fxml"));

		Scene scene = new Scene(root);

		stage.setTitle("Gerer les adherents");
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();
	}

}
