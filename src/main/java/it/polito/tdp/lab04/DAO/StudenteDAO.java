package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getNomeECognomebyMatricola(String matricola) {
		
		Studente s = null;
		
		final String sql = "SELECT * "
				+ "FROM studente "
				+ "WHERE matricola = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matricola);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				 s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
			}
			System.out.println(s.toString());
			conn.close();
			return s;
		}catch(SQLException e){
			throw new RuntimeException("Errore Db", e);
		}
		

		
	}
	
	public boolean isPresente(String matricola) {
				
		final String sql = "SELECT * "
				+ "FROM studente "
				+ "WHERE matricola = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				return true;
			}
			conn.close();
			return false;
		}catch(SQLException e){
			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	public List<Corso> getCorsiPerStudente(String matricola){
		
		List<Corso> corsi = new LinkedList<Corso>();
		
		final String sql = "SELECT * "
				+ "FROM iscrizione i, corso c "
				+ "WHERE i.codins=c.codins AND i.matricola = ";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matricola);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
				
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}
			conn.close();
			return corsi;
			
		}catch(SQLException e){
			throw new RuntimeException("Errore Db", e);
		}
	}
	
}
