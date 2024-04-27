package controleur;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AjouterJeu extends Main{

	
	public void initialiser() {
		try {
			Parent loader = FXMLLoader.load(getClass().getResource("../vue/ajouterJeu.fxml"));
				    	
			Scene scene = new Scene(loader, 1280, 800);
			
			Main.stage.setScene(scene);
			Main.stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
