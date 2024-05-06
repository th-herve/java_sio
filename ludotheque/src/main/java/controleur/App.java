package controleur;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	// fenêtre principale de l'application
	private Stage primaryStage;

	private Page accueilPage;
	private Page gererAdherentPage;
	private Page gererJeuPage;
	private Page inscriptionAdherentPage;
	private Page connexionPage;

	@Override
	// méthode appelée automatiquement au lancement de l'app (l'arg primaryStage est
	// créé automatiquement)
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		primaryStage.setTitle("Ludo tech");
		primaryStage.setMaximized(true);

		loadViews(); // Charge les vues, loader et controleur depuis les fichiers FXML

		switchToAccueil(); // Affiche la première vue par défaut
	}

	private void loadViews() throws IOException {
		
		this.accueilPage = new Page(this, "accueil.fxml");
		this.gererAdherentPage = new Page(this, "gererAdherent.fxml");
		this.gererJeuPage = new Page(this, "gererJeu.fxml");
		this.inscriptionAdherentPage = new Page(this, "inscriptionAdherent.fxml");
//		this.connexionPage= new Page(this,"pageConnexion.fxml");
		
		
	}

	// Méthodes pour passer d'une vue à une autre
	public void switchToAccueil() {
		primaryStage.setScene(accueilPage.getScene());
		primaryStage.show();
	}

	public void switchToGererAdherent() {
		primaryStage.setScene(gererAdherentPage.getScene());
		primaryStage.show();
	}

	public void switchToGererJeu() {
		primaryStage.setScene(gererJeuPage.getScene());
		primaryStage.show();
	}
	
	public void switchToinscriptionAdherent() {
		primaryStage.setScene(inscriptionAdherentPage.getScene());
		primaryStage.show();
	}

//	public void switchToconnexionPage() {
//		// TODO Auto-generated method stub
//		primaryStage.setScene(connexionPage.getScene());
//		primaryStage.show();
//		
//	}
	
}