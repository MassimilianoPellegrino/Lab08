package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	Graph<Airport, DefaultWeightedEdge> grafo;
	ExtFlightDelaysDAO dao;
	Map<Integer, Airport> idMap;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
		idMap = new HashMap<>();
		dao.loadAllAirports(idMap);
	}
	
	public void creaGrafo(int x) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, idMap.values());
		for(Distance d: dao.getAvgDistances(x)) {
			Airport a1 = idMap.get(d.getIdA1());
			Airport a2 = idMap.get(d.getIdA2());
			if(!grafo.containsEdge(a1, a2))
				Graphs.addEdge(grafo, a1, a2, d.getAvgDistanza());
			else {
				DefaultWeightedEdge e = grafo.getEdge(a1, a2);
				double peso = grafo.getEdgeWeight(e);
				peso= (peso+d.getAvgDistanza())/2;
				grafo.setEdgeWeight(e, peso);
			}
		}
	}
	
	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public String stampaGrafo() {
		
		String result="";
		
		result+="Grafo creato con "+grafo.vertexSet().size()+" vertici e "+grafo.edgeSet().size()+" archi";
		result+="\n\nELENCO ROTTE:\n";
		for(DefaultWeightedEdge e: grafo.edgeSet()) {
			result+=e.toString().replaceAll("[()]", "").replaceAll("[:]", "-")+" : "+grafo.getEdgeWeight(e)+"\n";
		}
		
		return result;
		
	}
}
