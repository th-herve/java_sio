# Ajouter une vue

1. Créer le fichier fxml dans vue (par exemple accueil.fxml)
2. Créer le controleur :
	. Ajouter la classe dans le package 'controleur.app'
	. Le nom doit être NomFichierFxml + Controleur (ex: AccueilControleur)
	. La classe doit 'extends SceneControleur'
	. Déclarer les '@FXML' et la fonction initialize
3. Lier le controleur et le fichier fxml :
	. Soit depuis scenebuilder (bien mettre: controleur.scene.NomControleur)
	. Soit depuis le fichier fxml (fx:controller="controleur.scene.NomControleur)
4. Configurer la nouvelle vue dans la class 'App' :
	. Ajouter un objet 'Page' en attribut (ex: private Page AccueilPage;)
	. Initialiser la page dans la fonction 'loadViews' (this.AccueilPage = new Page(this, accueil.fxml)
	. Ajouter une fonction 'switchToNomVue' (ex: switchToAccueil)
5. Ajouter également une fonction switchToNomVue, dans la classe scene.SceneControleur