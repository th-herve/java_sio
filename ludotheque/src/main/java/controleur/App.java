package controleur;

import java.io.IOException;

import controleur.scene.GererJeuPhysiqueControleur;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	// fenêtre principale de l'application
	private Stage primaryStage;

	private Page accueilPage;
	private Page gererAdherentPage;
	private Page gererJeuPage;
	private Page gererJeuPhysiquePage;
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
		this.gererJeuPhysiquePage 	= new Page(this, "gererJeuPhysique.fxml");
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

	// il faut spécifier l'id du jeu (non physique) pour lequel on veut afficher les jeux physiques
	public void switchToGererJeuPhysique(int idJeu) {
		GererJeuPhysiqueControleur ctl = (GererJeuPhysiqueControleur) gererJeuPhysiquePage.getControleur(); // récupère le controleur
		ctl.setUp(1);
		Stage stage = new Stage();
		stage.setScene(gererJeuPhysiquePage.getScene());
		stage.setTitle("Gérer Jeux physiques");
		stage.show();
	}
	
	public void switchToinscriptionAdherent() {
		// Crée un new stage pour ouvrir la vue dans une nouvelle fenêtre
		Stage stage = new Stage();
		stage.setScene(inscriptionAdherentPage.getScene());
		stage.show();
	}

//	public void switchToconnexionPage() {
//		// TODO Auto-generated method stub
//		primaryStage.setScene(connexionPage.getScene());
//		primaryStage.show();
//		
//	}
	public Page getGererAdherentPage() {
		return gererAdherentPage;
	}

	
}
