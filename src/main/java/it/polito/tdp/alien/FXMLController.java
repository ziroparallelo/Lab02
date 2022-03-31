package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;
import java.util.*;

import id.polito.tdp.alien.model.Dizionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Dizionario model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Label txtStatus;

    @FXML
    private URL location;

    @FXML
    private TextField txtTesto;

    @FXML
    private TextArea txtVocabolario;
    
    public void setModel(Dizionario model)
    {
    	this.model = model;
    }

    @FXML
    void doClearText(ActionEvent event) {
    	
    	model.getDizionario().clear();
    	txtTesto.clear();
    	txtVocabolario.clear();
    	txtStatus.setText("");

    }

    @FXML
    void handleTranslate(ActionEvent event) {
    	
    	// ss  && sss Variabile Stringa txtTesto 
    	// s Stringa txtTesto
    	// p vettore di parole splittato
    	
    	//1. acquisizione e controllo dati
    	
    	//SPLIT STRINGA
    	String[] p;
    	String s = txtTesto.getText();
    	
    	try {
    	p = s.split(" ");
    	} catch (PatternSyntaxException pse)
    	{
    		txtStatus.setText("ERRORE: formato richiesto <parola aliena> <traduzione>");
    		return;
    	}
    	
    	// non più di due parole
    	
    	if(p.length == 0 || p.length > 2)
    	{
    		txtStatus.setText("ERRORE: formato richiesto <parola aliena> <traduzione>");
    		return;
    	}
    	
    	//solo caratteri richiesti
    	
    	int ASCII;
    	for(String ss: p)
    	{
    		ss = ss.toLowerCase();
    		for(int i = 0; i<ss.length(); i++)
    		{
    			ASCII = (int) ss.charAt(i);
    			if( ASCII < 63 || (ASCII > 63 && ASCII < 97) || ASCII > 127)
    			{
    				txtStatus.setText("ERRORE: unici caratteri accettati [a-z,A-Z]");
    				txtTesto.clear();
    				return;
    			}
    		}
    	}
    	
    	//2. Chiedo al modello di fare l'operazione
    	
    	
    	
    	//Ho due operazioni:
    	
    	//AGGIUNGI TRADUZIONE
    	
    	if(p.length == 2)
    	{
    		model.aggiungiTraduzione(p);
        	txtVocabolario.setText(model.toString());
        	txtStatus.setText("");
        	txtTesto.clear();
    	}
    	
    	// CERCA PAROLA
    	if(p.length == 1)
    	{
    		int cont = 0;
    		
    		for(int i = 0; i<s.length(); i++)
    		{
    			ASCII = (int) s.charAt(i);
    			if(ASCII == 63)
    				cont++;
    			
    		}
    		
    		//CASO NORMALE
    		
    		if(cont == 0)
    		{
    			//PAROLA ESISTE?
    			boolean ok = model.getDizionario().containsKey(s);
            	if(ok)
            	{
            		txtVocabolario.setText(model.cercaParola(s));
            		txtStatus.setText("");
            		return;
            	}
            	else
            	{
            		txtStatus.setText("ERRORE: Parola non trovata");
            		txtTesto.clear();
            		return;
            	}
    		}
    		
    		//CASO CON UN ?
    		
    		if(cont == 1)
    		{
    			String output = "";
    			boolean flag = true;
				for(int j = 93; j< 127; j++)
				{
					for(String ss: model.getDizionario().keySet())
					{
						//s parola originale
						
						char c = (char) j;
						s.replace('?', c);
						
						if(ss.compareTo(s) == 0)
						{
							flag = false;
							output = output + model.cercaParola(ss) +"\n";
		            		txtStatus.setText("");
						}
					}
				}
				
				for(int j = 65; j< 90; j++)
				{
					for(String ss: model.getDizionario().keySet())
					{
						//s parola originale
						
						char c = (char) j;
						s.replace('?', c);
						
						if(ss.compareTo(s) == 0)
						{
							flag = false;
							output = "\n\n"+output + model.cercaParola(ss) +"\n";
		            		txtStatus.setText("");
		            		return;
						}
					}
				}
				if(flag)
				{
					txtStatus.setText("ERRORE: Nessuna corrispondenza");
            		txtTesto.clear();
            		return;
				}
    	    }
    		//CASO CON PIU' DI UN ?
    		
    		if(cont > 1)
    		{
    			txtStatus.setText("ERRORE: Parola può contenere al massimo un ?");
        		txtTesto.clear();
        		return;
    		}
    	}

    }

    @FXML
    void initialize() {
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVocabolario != null : "fx:id=\"txtVocabolario\" was not injected: check your FXML file 'Scene.fxml'.";
    }

}

