package it.polito.tdp.porto.model;

import java.util.*;

public class Creator {
	
	private Paper p;
	private List<Author> a = new ArrayList<>();
	
	public Creator(Paper p, Author a) {
		super();
		this.a.add(a);
		this.p = p;
	}

	public List<Author> getA() {
		return a;
	}

	public Paper getP() {
		return p;
	}

	public void addA(Author a){
		this.a.add(a);
	}
}
