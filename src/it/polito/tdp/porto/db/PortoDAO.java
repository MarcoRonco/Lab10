package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.Creator;
import it.polito.tdp.porto.model.Paper;
import it.polito.tdp.porto.model.PaperIdMap;

public class PortoDAO {

	public List<Author> getAutori(AuthorIdMap a) {

		String sql = "SELECT * FROM author";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			List<Author> at = new ArrayList<>();

			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				Author aut = a.put(autore);
				at.add(aut);
				
			}
			return at;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Paper> getArticolo(PaperIdMap articoli) {

		final String sql = "SELECT * FROM paper";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			List<Paper> ar = new ArrayList<>();
			
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				Paper p = articoli.put(paper);
				ar.add(p);
			}
			
			return ar;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public Map<Paper, Creator> getCreator(AuthorIdMap autori, PaperIdMap articoli){
		
		final String sql = "SELECT * FROM creator";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			Map<Paper, Creator> c = new HashMap<>();
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Paper p = articoli.get(rs.getInt(1));
				Author a = autori.get(rs.getInt(2));
				
				if(!c.containsKey(p))
					c.put(p, new Creator(p, a));
				else
					c.get(p).addA(a);
			}
			
			return c;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	
}