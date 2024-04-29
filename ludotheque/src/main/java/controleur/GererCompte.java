package controleur;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class GererCompte extends Main {
	
	private InscriptionAdherent adherent;
	private InscriptionPersonnel personnel;
//	private Main accueil;

	
	public void initialize() {
		try {
			Parent loader = FXMLLoader.load(getClass().getResource("../vue/gererCompte.fxml"));
			
			adherent = new InscriptionAdherent();
			personnel = new InscriptionPersonnel();
//			accueil = new Main();


			Button addAdherent = (Button) loader.lookup("#inscrAdherent");
			addAdherent.setOnAction(event -> {
			adherent.initialize();
			});
			
			Button addPersonnel = (Button) loader.lookup("#inscrPersonnel");
			addPersonnel.setOnAction(event -> {
			personnel.initialize();
			});
//			
//			Button retourAccueil = (Button) loader.lookup("#rAccueil");
//			retourAccueil.setOnAction(event -> {
//			accueil.initialiser();
//			});
//			
//			
			
			Scene scene = new Scene(loader, 1280, 800);

			Main.stage.setScene(scene);
			Main.stage.show();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
