package controleur;

import java.io.IOException;

import atlantafx.base.theme.PrimerLight;
import controleur.scene.AccueilControleur;
import controleur.scene.GererJeuPhysiqueControleur;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	// fenêtre principale de l'application

	private Stage primaryStage;
	private Page accueilPage;
	private Page gererAdherentPage;
	private Page gererJeuPage;
	private Page gererEmpruntPage;
	private Page ajouterJeuPage;
	private Page gererJeuPhysiquePage;
	private Page inscriptionAdherentPage;
	private Page connexionPage;
	private Page gererEvenementPage;

	private Page inscriptionPersonnelPage;
	// private Page logoutPage;

	@Override
	// méthode appelée automatiquement au lancement de l'app (l'arg primaryStage est
	// créé automatiquement)
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		primaryStage.setMinWidth(700);
		primaryStage.setMinHeight(600);
		primaryStage.setTitle("Ludo tech");
		// primaryStage.setMaximized(true);

		loadViews(); // Charge les vues, loader et controleur depuis les fichiers FXML

//		switchToconnexionPage(); // Affiche la première vue par défaut
//		switchToinscriptionPersonnel();
		switchToAccueil(); // Affiche la première vue par défaut
		
		// Set le theme
		Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

	}

	private void loadViews() throws IOException {

		this.accueilPage = new Page(this, "accueil.fxml");
		this.gererAdherentPage = new Page(this, "gererAdherent.fxml");
		this.gererJeuPage = new Page(this, "gererJeu.fxml");
		this.ajouterJeuPage = new Page(this, "ajouterJeu.fxml");
		this.gererJeuPhysiquePage = new Page(this, "gererJeuPhysique.fxml");
		this.inscriptionAdherentPage = new Page(this, "inscriptionAdherent.fxml");
		this.connexionPage = new Page(this, "pageConnexion.fxml");
		this.inscriptionPersonnelPage = new Page(this, "inscriptionPersonnel.fxml");
		this.gererEmpruntPage = new Page(this, "gererEmprunt.fxml");
		this.gererEvenementPage = new Page(this, "gererEvenement.fxml");

	}

	public void switchToGererEvenement() {

		primaryStage.setScene(gererEvenementPage.getScene());
		primaryStage.show();
	}

	// Méthodes pour passer d'une vue à une autre
	public void switchToAccueil() {

		((AccueilControleur) accueilPage.getControleur()).loadUserData();
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

	public void switchToGererEmprunt() {
		primaryStage.setScene(gererEmpruntPage.getScene());
		primaryStage.show();
	}

	// il faut spécifier l'id du jeu (non physique) pour lequel on veut afficher les
	// jeux physiques
	public void switchToGererJeuPhysique(int idJeu) {
		GererJeuPhysiqueControleur ctl = (GererJeuPhysiqueControleur) gererJeuPhysiquePage.getControleur(); // récupère
																											// le
																											// controleur
		ctl.setUp(idJeu);
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

	public void switchToAjouterJeu() {
		// Crée un new stage pour ouvrir la vue dans une nouvelle fenêtre
		Stage stage = new Stage();
		stage.setScene(ajouterJeuPage.getScene());
		stage.show();
	}

	public void switchToconnexionPage() {

		primaryStage.setScene(connexionPage.getScene());
		primaryStage.show();

	}

	public Page getGererAdherentPage() {
		return gererAdherentPage;
	}

	public Page getGererJeuPage() {
		return gererJeuPage;
	}

	public void switchToinscriptionPersonnel() {

		primaryStage.setScene(inscriptionPersonnelPage.getScene());
		primaryStage.show();

	}

}
