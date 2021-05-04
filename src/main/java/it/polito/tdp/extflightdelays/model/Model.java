package it.polito.tdp.extflightdelays.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	Graph<Airport, DefaultWeightedEdge> grafo;
	ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
	List<Airport> areoporti = dao.loadAllAirports();
	
	public void creaGrafo(int x) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, areoporti);
		for(Distance d: dao.getAvgDistances(x)) {
			Airport a1 = trovaAreoporto(d.getIdA1());
			Airport a2 = trovaAreoporto(d.getIdA2());
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
	
	public Airport trovaAreoporto(int id) {
		for(Airport a: areoporti)
			if(a.getId()==id)
				return a;
		
		return null;
	}
	
	
	
	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	/*public String stampaGrafo(int x) {
		
		creaGrafo(x);
		
		return "numero vertici: "+grafo.vertexSet().size()+"\n"
				+ "numero archi: "+grafo.edgeSet().size()+"\n"
						+ "elenco archi: "+grafo.edgeSet();
		
	}*/
}
