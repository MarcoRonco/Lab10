package it.polito.tdp.porto.model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge{

	private Paper p;

	public Edge() {
		super();
	}

	public Paper getP() {
		return p;
	}

	public void setP(Paper p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return p.toString();
	}
}
