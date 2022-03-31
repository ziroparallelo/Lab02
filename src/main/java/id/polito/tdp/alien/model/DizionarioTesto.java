package id.polito.tdp.alien.model;

import java.util.regex.PatternSyntaxException;

public class DizionarioTesto {

	public static void main(String[] args) 
	{
		Dizionario dizionario = new Dizionario();
		
		String s1 = "ddj0001j cazzo";

		
		String ss[] ;
		try {
		
			/*
		dizionario.aggiungiTraduzione(s1.split(" "));
		dizionario.aggiungiTraduzione(s2.split(" "));
		dizionario.aggiungiTraduzione(s3.split(" "));
		*/
			ss = s1.split(" ");;
		} catch (PatternSyntaxException pse)
		{
			System.err.println("ERRORE");
		}
		

	}

}
