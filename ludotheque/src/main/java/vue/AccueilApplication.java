package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccueilApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/vue/fxml/accueil.fxml"));

		Scene scene = new Scene(root, 1000, 1000);

		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
	}

}
