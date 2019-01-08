package fr.umlv.ir2.graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageRank {
	
	public static Map<Integer, Double> launchPageRank(Graph graph, int k){
		final int nbPages = graph.numberOfVertices()-1;
		final Double epsilon = 1 / (double) (nbPages * 10);
//		System.out.println("epsilon = " + epsilon);
		Map<Integer, Double> map = getMapProbas(nbPages);
		Map<Integer, Double> newMap;
		
		//See initialization of probas
//		System.out.println("probabilité de départ :");
//		seeProbas(map);
		
		for(int iteration = 0; iteration < k; iteration++){
			newMap = new HashMap<>();
			for(int s = 0; s <= nbPages; s++){
				Double newProba = 0D;
				List<Integer> us = graph.getPredecesseurs(s);
				for(Integer u : us){
					Double newProbaU = map.get(u);
					if(s == 0){ // super-noeud
						newProbaU *= epsilon;
					}
					else if(u == 0){
						Double unSurN = 1F/ (double) nbPages;
						newProbaU *= unSurN;
					}
					else{
//						Integer nbSucc = graph.getSuccesseurs(u).size() - 1; //-1 pour enlever le super-noeud
						Integer nbSucc = graph.getNbSucc(u) - 1; //-1 pour enlever le super-noeud
						Double d = 1F - epsilon;
						Double d2 = d / (double) nbSucc;
						newProbaU *= d2;
					}
					newProba += newProbaU;
				}
				newMap.put(s, newProba);
			}
			map = newMap;
		}
//		System.out.println("Probabilités finales : ");
//		seeProbas(map);
		return map;
	}

	private static Map<Integer, Double> getMapProbas(final int nbPages) {
		final Map<Integer, Double> map = new HashMap<>();
		map.put(0, 0D);
		Double d = 1 / (double) nbPages;
		for(int i = 1; i <= nbPages; i++)
			map.put(i, d);
		return map;
	}

	private static void seeProbas(Map<Integer, Double> map){
		int i = 0;
		Double res = 0D;
		for(Double d : map.values()){
			System.out.println(i++ + " " + d);
			res += d;
		}
		System.out.println("total = " + res);
	}
}
