package controleur;

import javafx.application.Application;

public class Main {
	
	Connection co = Connexion.getInstance();


	public static Stage stage;
	private GererCompte compte;
	private GererJeuControleur jeu;

	
    public void start(Stage racine) throws Exception {
    	Main.stage = racine;
    	Parent root = FXMLLoader.load(getClass().getResource( "../vue/accueil.fxml"));
    	
    	compte = new GererCompte();
    	jeu = new GererJeuControleur();

//    	Image image = new Image(getClass().getResourceAsStream("/logo_bettonludotheque.png"));
//    	if (image.isError()) {
//    	    System.err.println("Failed to load image: " + image.getException().getMessage());
//    	} else {
//    	    racine.getIcons().add(image);
//    	}

    	//aller sur la page Gérer Compte depuis le bouton Gérer Compte
    	Button compteBouton = (Button) root.lookup("#compte");
    	compteBouton.setOnAction(event -> {
    	compte.initialize();
    	});
    	
    	//aller sur la page Gérer Emprunts depuis le bouton Gérer Compte
    	Button empruntsBouton= (Button) root.lookup("#emprunt");
    	empruntsBouton.setOnAction(event -> {
    	compte.initialize();
    	});
    	
    	//aller sur la page Gérer Jeux depuis le bouton Gérer Compte
    	Button jeuBouton = (Button) root.lookup("#jeu");
    	jeuBouton.setOnAction(event -> {
    	jeu.initialize();
    	});
    	
    	Scene scene = new Scene(root, 1280, 800);     
    	racine.setScene(scene);
    	racine.setTitle("Ludothèque");
    	racine.show();

    }
    
	public static void main(String[] args) {
		Application.launch(App.class, args);
	}
}
