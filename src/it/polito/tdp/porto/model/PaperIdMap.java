package it.polito.tdp.porto.model;

import java.util.*;

public class PaperIdMap {

	private Map<Integer, Paper> articoli;

	public PaperIdMap() {
		this.articoli = new TreeMap<>();
	}
	
	public Paper get(int i){
		return articoli.get(i);
	}
	
	public Paper put(Paper a){
		Paper articolo = articoli.get(a.getEprintid());
		
		if(articolo==null){
			
			articoli.put(a.getEprintid(), a);
			return a;
		
		}else{
			return articolo;
		}
	}
}
