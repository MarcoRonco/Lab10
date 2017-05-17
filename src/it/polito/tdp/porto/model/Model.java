package it.polito.tdp.porto.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private AuthorIdMap autori;
	private PaperIdMap articoli;
	private PortoDAO dao;
	private UndirectedGraph<Author, Edge> graph;
	
	public Model(){
		dao = new PortoDAO();
		autori = new AuthorIdMap();
		articoli = new PaperIdMap();
	}
	
	public List<Author> createGraph(){
		
		List<Author> vertici = dao.getAutori(autori);
		List<Paper> archi = dao.getArticolo(articoli);
		Map<Paper, Creator> creator = dao.getCreator(autori, articoli);
		
		graph = new Multigraph<>(Edge.class);
		Graphs.addAllVertices(graph, vertici);
				
		for(Creator c : creator.values()){
			for(Author a : c.getA()){
				for(Author b: c.getA()){
					if(!a.equals(b)){
						Edge e = graph.addEdge(a, b);
						e.setP(c.getP());			
					}
				}
			}
		}
		return vertici;
	}
	
	public List<Author> getCoautori(Author a){
		
		List<Author> coAutori = new ArrayList<>();
		
		for(Author f : Graphs.neighborListOf(graph, a)){
			if(!coAutori.contains(f)){
				coAutori.add(f);
			}
		}
		return coAutori;
	}
	
	public List<Edge> articoliTraAutori(Author a, Author b){
		
		List<Edge> x = new ArrayList<>();
		DijkstraShortestPath<Author, Edge> shortest = new DijkstraShortestPath(graph, a, b);
		
		for(Edge e : shortest.getPathEdgeList()){
			x.add(e);
		}
		
		return x;
	}
}
