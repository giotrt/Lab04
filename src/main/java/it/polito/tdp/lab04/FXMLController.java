package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbBox;

    @FXML
    private TextField txtCognome;

    @FXML
    private Label txtError;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtResults;

    @FXML
    void btnCercaCorsi(ActionEvent event) {
    	
    	txtError.setText("");
    	txtResults.clear();
    	
    	//controllo matricola
    	
    	try {
    		if(txtMatricola.getText().compareTo("")==0) {
    			txtError.setText("Inserire una matricola!");
    			return;
    		}
    		Integer matricola = Integer.parseInt(txtMatricola.getText());
    		if(matricola>999999 || matricola<100000) {
    			txtError.setText("Matricola non valida!");
    			return;
    		} 
    			
    	}catch(NumberFormatException e){
    		txtError.setText("Inserire un formato valido!");
    		return;
    	}
    	
    	//controllo se lo studente è presente nel db
    	
    	boolean presente = model.isPresente(txtMatricola.getText());
    	if(presente==false) {
    		txtError.setText("Studente non presente!");
    		return;
    	}
    	
    	List<Corso> corsi = model.getCorsiPerStudente(txtMatricola.getText());
    	for(Corso c : corsi) {
    		txtResults.appendText(c.descriviti());
    		txtResults.appendText("\n");
    	}
    	
    	
    		

    }

    @FXML
    void btnCercaIscrittiCorso(ActionEvent event) {
    	
    	Corso c = cmbBox.getValue();
    	
    	try {
    		if(c.getCodins().compareTo("")==0 ) {
        		txtError.setText("Nessun corso selezionato!");
        		return;
    		}
    		
    		List<Studente> studenti = model.getStudentiCorso(c.getCodins());
    		txtResults.clear();
    		if(studenti.size()==0) {
    			txtResults.appendText("Corso senza iscritti!");
    			txtResults.appendText("\n");
    			return;
    		}
    		for(Studente s : studenti) {
    			txtResults.appendText(s.toString());
    			txtResults.appendText("\n");
    		}
    	}catch(Exception e) {
    		txtError.setText("Nessun corso selezionato!");
    		return;
    	}
    	

    }
    
    @FXML
    void btnCompleta(ActionEvent event) {
    	//inserire controllo matricola
    	try {
    		if(txtMatricola.getText().compareTo("")==0) {
    			txtError.setText("Inserire una matricola!");
    			return;
    		}
    		Integer matricola = Integer.parseInt(txtMatricola.getText());
    		if(matricola>999999 || matricola<100000) {
    			txtError.setText("Matricola non valida!");
    			return;
    		} 
    			
    	}catch(NumberFormatException e){
    		txtError.setText("Inserire un formato valido!");
    		return;
    	}
    	boolean presente = model.isPresente(txtMatricola.getText());
    	if(presente==false) {
    		txtError.setText("Studente non presente!");
    		return;
    	}
    	Studente s = model.getNomeECognomebyMatricola(txtMatricola.getText());
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    }


    @FXML
    void btnIscriviti(ActionEvent event) {

    }

    @FXML
    void btnReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtError.setText("");
    	txtResults.clear();
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbBox.getItems().clear();
    	Corso vuoto = new Corso("", null, "", null);
    	cmbBox.getItems().add(vuoto);
    	List<Corso> corsi = model.getTuttiICorsi();
    	for(Corso c : corsi) {
    		cmbBox.getItems().add(c);
    	}
    }

    @FXML
    void initialize() {
        assert cmbBox != null : "fx:id=\"cmbBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtError != null : "fx:id=\"txtError\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResults != null : "fx:id=\"txtResults\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
