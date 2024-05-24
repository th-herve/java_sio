package controleur;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import modele.Jeu;

public class VerifierJeu extends Main {
	
	private Jeu jeu;
	
	public VerifierJeu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VerifierJeu(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public String afficherNomJeu() {
		return jeu.getNom();
	}
	
//	public void initialiser() {
//		try {
//			Parent loader = FXMLLoader.load(getClass().getResource("../vue/verifierJeu.fxml"));
//				    	
//			Scene scene = new Scene(loader, 1280, 800);
//			
//			VerifierJeu verification = new VerifierJeu(new Jeu("Wokabi et ses amis", null, null, 0, 0, 0, 0, 0, 0, null, 0));
//	        String nomJeu = verification.afficherNomJeu();
//	        TextField nom = (TextField) loader.lookup("#nomJeu");
//	        nom.setText(nomJeu);
//
//	        Main.stage.setScene(scene);
//	        Main.stage.show();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
