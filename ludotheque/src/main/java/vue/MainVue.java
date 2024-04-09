package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//TODO
public class MainVue extends Application {

	@Override
    public void start(Stage primaryStage) throws Exception {
        // Create a root node for the scene
        StackPane root = new StackPane();

        // Create the scene
        Scene scene = new Scene(root, 400, 300);

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Set the title of the stage
        primaryStage.setTitle("JavaFX Test Application");

        // Show the stage
        primaryStage.show();
    }


	public static void main(String[] args) {
		launch(args);
	}
}
