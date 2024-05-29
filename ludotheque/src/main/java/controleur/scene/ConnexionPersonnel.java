package controleur.scene;



import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javafx.util.Duration;
import modele.Personne;
import modele.Personnel;
import modele.dao.PersonneDAO;
import modele.dao.PersonnelDAO;

public class ConnexionPersonnel extends SceneControleur {

	@FXML
	private TextField identification;

	@FXML
	private PasswordField mdp;

	@FXML
	private Button connecter;

	@FXML

	private Button deconnecter;

	public static Personne loggedInPersonne;
	private PersonnelDAO personnelDAO;
	private PersonneDAO personneDAO;
	private Personnel personnel;
	private PauseTransition activityTimer;
	
	// variable pour contrôler duree de connexion s'il n'y a pas d'activité
	private final int TimeOutSeconde = 30 ;  

	public ConnexionPersonnel() {
		this.personnelDAO = PersonnelDAO.getInstance();
		this.personneDAO = PersonneDAO.getInstance();
		 
		// Initialize timer
		activityTimer = new PauseTransition(Duration.seconds(TimeOutSeconde));
		activityTimer.setOnFinished(event -> {
            Platform.runLater(() -> {
                showAlert(Alert.AlertType.INFORMATION, null, "Déconnexion automatique", "Vous avez été déconnecté en raison de l'inactivité.");
               
                switchToconnexionPage();
            });
        });
    }
	 @FXML
	    public void initialize() {
		// ici on vérifier si il n'y a pas d'activite applique on non
		 
		 	if (identification != null) addEventListeners(identification);
	        if (mdp != null) addEventListeners(mdp);
	        if (connecter != null) addEventListeners(connecter);
	        if (deconnecter != null) addEventListeners(deconnecter);

	        resetactivityTimer();
	    }
	//on déterminé les type de action "MOUSE PRESSED" ,"KEY PRESSED"
	@FXML
    private void addEventListeners(Node node) {
      node.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> resetactivityTimer());
      node.addEventFilter(KeyEvent.KEY_PRESSED, event -> resetactivityTimer());
    }
	
    @FXML
	private void resetactivityTimer() {
		activityTimer.playFromStart();
	}

	@FXML
	public void Login(ActionEvent event) {
		Window owner = connecter.getScene().getWindow();

		String email = identification.getText();
		String password = mdp.getText();

		if (email.isEmpty() || password.isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Erreur de formulaire!", "Veuillez remplir tous les champs");
			return;
		}

		Personnel personnel = (Personnel) personnelDAO.readByEmail(email); // Cast  Personnel
		//        cast permet de changer type data pour d'autre type

		if (personnel != null  ) {

			// hachage le mot de passe depuis le table personnel 
			String hashedPasswordFromDB = personnel.getMdp(); 
			System.out.println(personnel.toString());
			            System.out.println("from BD " + hashedPasswordFromDB);

			//hachage le mot de passe entrée
			String hashedEnteredPassword = personnelDAO.hashPassword(password); 
			            System.out.println("from form " +hashedEnteredPassword );

			if (hashedEnteredPassword.equals(hashedPasswordFromDB)) {
				// Passwords match, login  avec succès
				ConnexionPersonnel.loggedInPersonne = personnel;
				showAlert(Alert.AlertType.INFORMATION, owner, "Succès", "Connexion réussie");
				identification.clear();
				mdp.clear();
				switchToAccueil();
			} else {
				// Passwords don't match, login failed
				showAlert(Alert.AlertType.ERROR, owner, "Identifiant ou mot de passe incorrect", "Veuillez vérifier vos informations de connexion");
			}
		} else {
			// Person not found in the database
			showAlert(Alert.AlertType.ERROR, owner, "Identifiant ou mot de passe incorrect", "Veuillez vérifier vos informations de connexion");
		}
	}



	@FXML
	public void Logout1(ActionEvent event) {
		Window owner = deconnecter.getScene().getWindow();

		// Effacez les informations d'identification actuellement stockées
		identification.clear();
		mdp.clear();

		// Redirigez l'utilisateur vers l'écran de connexion
		app.switchToconnexionPage();

		// Affichez un message de confirmation de déconnexion
		showAlert(Alert.AlertType.INFORMATION, owner, "Déconnexion réussie", "Vous avez été déconnecté avec succès.");

	}

	public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

}




