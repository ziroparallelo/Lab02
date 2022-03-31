package id.polito.tdp.alien.model;

import java.util.*;

public class Dizionario {
	
	private Map<String, LinkedList<String>> dizionario;
	private LinkedList<String>  valori;
	
	public Dizionario()
	{
		dizionario = new TreeMap<String, LinkedList<String> >();
	}
	
	public void aggiungiTraduzione(String[] s)
	{
		//devo creare una lista per ogni chiave
		
		valori = new LinkedList<String> ();
		
		//chiave già esistente
		
		if(dizionario.containsKey(s[0]))
		{
			// aggiungo la parola alla lista della chiave
			
			for(String ss: dizionario.keySet())
				if(ss.compareTo(s[0]) == 0)
					dizionario.get(ss).add(s[1]);
			return;
		}
		
		//chiave non esistente
		
		valori.add(s[1]);
		dizionario.put(s[0], valori);
	}
	
	public String cercaParola(String p)
	{	
		String s = "**** "+p+" ****\n\n\n";
		for(String pp: dizionario.get(p))
		{
			s = s+pp+"\n";
		}
		return s;
	}

	@Override
	public String toString() 
	{
		String s = "***** DIZIONARIO *****\n\n\n";
		for(String p: dizionario.keySet())
			for(String ss :(LinkedList<String>) dizionario.get(p))
				s = s + p+" "+ss+"\n";	
		return s;
	}

	public Map<String, LinkedList<String>> getDizionario() {
		return dizionario;
	}

	
	
	

}
