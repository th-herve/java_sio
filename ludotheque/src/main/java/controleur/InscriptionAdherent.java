package controleur;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class InscriptionAdherent extends Main{

	
	public void initialize() {
		try {
			Parent loader = FXMLLoader.load(getClass().getResource("../vue/inscriptionAdherent.fxml"));
				    	
			Scene scene = new Scene(loader, 1280, 800);
			
			Main.stage.setScene(scene);
			Main.stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
