package controleur;

import java.awt.TextField;

import javax.management.loading.PrivateClassLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class inscription_Personnel_Controleur {
	
	@FXML
	private Button validerButton ; 
	@FXML
	private Button retour ; 
	@FXML 
	private TextField nom; 
	private TextField prenom; 
	private TextField email; 
	private TextField adresse; 
	private TextField N_tel; 

	@FXML
	private void validation(ActionEvent event) {
		
		
		System.out.println("couocou");
		
	}

}
