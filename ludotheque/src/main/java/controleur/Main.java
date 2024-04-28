package controleur;

import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import modele.Adherent;
import modele.dao.AdherentDAO;
import vue.AccueilApplication;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Set up the primary stage
		primaryStage.setTitle("Ludo tech");
		primaryStage.setMaximized(true);

		new AccueilApplication().start(primaryStage);

	}

	public static void main(String[] args) {
//		AdherentDAO adao = AdherentDAO.getInstance();
//		List<Adherent> adl = adao.readAll();
//		for (Adherent ad : adl) {
//			System.out.println(ad.toString());
//		}
		launch(args);
	}
}
