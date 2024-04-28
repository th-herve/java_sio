package controleur;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class SceneControleur {

	// function qui permet de changer la scene.
	protected EventHandler<ActionEvent> switchScene(Application newScene) {

		EventHandler<ActionEvent> eventHandler;

		eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Stage currentStage = getCurrentStageFromEvent(event);

				try {
					newScene.start(currentStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;
			}
		};

		return eventHandler;

	}

	protected Stage getCurrentStageFromEvent(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage currentStage = (Stage) node.getScene().getWindow();
		
		return currentStage;
	}

}