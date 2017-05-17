package it.polito.tdp.porto.model;

import java.util.*;

public class AuthorIdMap {

	private Map<Integer, Author> autori;

	public AuthorIdMap() {
		this.autori = new TreeMap<>();
	}
	
	public Author get(int i){
		return autori.get(i);
	}
	
	public Author put(Author a){
		Author autore = autori.get(a.getId());
		
		if(autore==null){
			
			autori.put(a.getId(), a);
			return a;
		
		}else{
			return autore;
		}
	}
}
