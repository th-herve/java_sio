package controleur;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;
    private FXMLLoader accueilLoader, gererAdherentLoader, gererJeuLoader; // Vous pouvez avoir un chargeur pour chaque vue

    private Parent accueilVue, gererAdherentVue, gererJeuVue; // Vue correspondant à la racine de chaque vue

    private AccueilControleur accueilControleur;
    private GererAdherentControleur gererAdherentControleur; // Contrôleur pour chaque vue
    private GererJeuControleur gererJeuControleur; // Contrôleur pour chaque vue

	private String fxmlLocaltion = "/vue/";

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

//		primaryStage.setTitle("Ludo tech");
//		primaryStage.setMaximized(true);

        loadViews(); // Chargez les vues depuis les fichiers FXML
        showAccueilVue(); // Affichez la première vue par défaut
    }

    private void loadViews() throws IOException {
        accueilLoader = new FXMLLoader(getClass().getResource(this.fxmlLocaltion + "accueil.fxml"));
        accueilVue = accueilLoader.load();
        accueilControleur  = accueilLoader.getController();
        accueilControleur.setApp(this);

        gererAdherentLoader = new FXMLLoader(getClass().getResource(this.fxmlLocaltion + "gererAdherent.fxml"));
        gererAdherentVue = gererAdherentLoader.load();
        gererAdherentControleur  = gererAdherentLoader.getController();
        gererAdherentControleur.setApp(this);

        gererJeuLoader = new FXMLLoader(getClass().getResource(this.fxmlLocaltion + "gererJeu.fxml"));
        gererJeuVue = gererJeuLoader.load();
        gererJeuControleur  = gererJeuLoader.getController();
        //gererJeuControleur.setApp(this);
    }
    
    private void showAccueilVue() {
        Scene scene = new Scene(accueilVue);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showGererAdherentVue() {
        Scene scene = new Scene(gererAdherentVue);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showGererJeuVue() {
        Scene scene = new Scene(gererJeuVue);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthodes pour passer d'une vue à une autre
    // Ces méthodes peuvent être appelées à partir des contrôleurs ou des boutons dans les vues
    public void switchToAccueil() {
        showAccueilVue();
    }

    public void switchToGererAdherent() {
        showGererAdherentVue();
    }

    public void switchToGererJeu() {
        showGererJeuVue();
    }
}
