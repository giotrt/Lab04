package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO corso = new CorsoDAO();
	StudenteDAO studente = new StudenteDAO();
	
	public List<Corso> getTuttiICorsi(){
		List<Corso> corsi = new LinkedList<Corso>();
		corsi = corso.getTuttiICorsi();
		return corsi;
		
	}
	
	public Studente getNomeECognomebyMatricola(String matricola) {
		Studente s = studente.getNomeECognomebyMatricola(matricola);
		return s;
	}
	
	public List<Studente> getStudentiCorso(String codiceCorso){
		List<Studente> studenti = corso.getStudentiIscrittiAlCorso(codiceCorso);
		return studenti;
	}
	
	public boolean isPresente(String matricola) {
		boolean presente = studente.isPresente(matricola);
		return presente;
	}
	
	public List<Corso> getCorsiPerStudente(String matricola){
		List<Corso> corsi = studente.getCorsiPerStudente(matricola);
		return corsi;
	}
}
