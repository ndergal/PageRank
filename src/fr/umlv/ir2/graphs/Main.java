package fr.umlv.ir2.graphs;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	
	private static Graph createTableVoisinage(int nbPages, String nameClassGraph) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> classe = Class.forName(nameClassGraph);
		Constructor<?> construct = classe.getConstructor(Integer.TYPE);
		Graph g = (Graph) construct.newInstance(nbPages+1);
		for(int i = 1; i <= nbPages; i++){
			g.addEdge(i, 0);
			g.addEdge(0, i);
			g.addEdge(i, i);
		}
		return g;
	}
	
	public static Graph parseInitFile(Path path, Map<Integer, String> indexation2, String nameClassGraph) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Map<String, Integer> indexation = new HashMap<>();
		try(Scanner sc = new Scanner(path)){
			int a = 1;
			while(sc.hasNextLine()){
				String nextLine = sc.nextLine();
				try{
					String[] line = nextLine.split("\\|");
					indexation.put(line[0], a);
					indexation2.put(a, line[0]);
					a++;
				}catch (Exception e) {
//					return false;
					System.out.println("Problème de parsage ligne : " + a);
				}
			}
			System.out.println("nb lignes = " + a);
		}
		Graph g = createTableVoisinage(indexation.size(), nameClassGraph);
		System.out.println(g);
		try(Scanner sc = new Scanner(path)){
			while(sc.hasNextLine()){
				String nextLine = sc.nextLine();
				try{
					String[] line = nextLine.split("\\|");
					int index = indexation.get(line[0]);
					for(int i = 1; i < line.length; i++){
						int indexVoisin = indexation.get(line[i]);
						g.addEdge(index, indexVoisin);
					}
				}catch (Exception e) {
//					return false;
				}
			}
		}
		return g;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Map<Integer, String> indexation2 = new HashMap<>();
		Graph g = parseInitFile(Paths.get("/home/gwenael/Downloads/wikis/wiki-simple.txt"), indexation2, "fr.umlv.ir2.graphs.AdjGraph");
		System.out.println("Fichier parser");
//		int i = 1;
//		for(String s : file.keySet()){
//			indexation.put(s, i);
//			indexation2.put(i, s);
//			i++;
//		}
//		Graph g = createTableVoisinage(file.size(), "fr.umlv.ir2.graphs.AdjGraph");
//		for(Entry<String, List<String>> e : file.entrySet()){
//			i = indexation.get(e.getKey());
//			for(String s : e.getValue()) {
//				Integer j = indexation.get(s);
//				if(j != null)
//					g.addEdge(i, j);
//			}
//		}
			
//		fichier alea4-6 :
//		g.addEdge(2, 1);
//		g.addEdge(2, 3);
//		g.addEdge(2, 4);
//		g.addEdge(3, 1);
//		g.addEdge(3, 4);
//		g.addEdge(4, 1);
//		fichier alea4-12 :
//		g = createTableVoisinage(4, "fr.umlv.ir2.graphs.AdjGraph");
//		g.addEdge(1, 2);
//		g.addEdge(1, 3);
//		g.addEdge(1, 4);
//		g.addEdge(2, 1);
//		g.addEdge(2, 3);
//		g.addEdge(2, 4);
//		g.addEdge(3, 1);
//		g.addEdge(3, 2);
//		g.addEdge(3, 4);
//		g.addEdge(4, 1);
//		g.addEdge(4, 2);
//		g.addEdge(4, 3);
//		PageRank.launchPageRank(g, 12);
//		g.printSuccesseurs();
//		g.printPredecesseurs();
		Double res[] = new Double[1];
		res[0] = 0D;
		TreeSet<Entry<Integer, Double>> test = new TreeSet<Entry<Integer, Double>>((x,y)->{
			if(x.getValue() < y.getValue())
				return 1;
			else if(x.getValue() == y.getValue())
				return 0;
			else
				return -1;
		});
		Set<Entry<Integer, Double>> entrySet = PageRank.launchPageRank(g, 1).entrySet();
		System.out.println(entrySet.size());
		test.addAll(entrySet);
		int size = test.size();
		System.out.println(size);
//		entrySet.forEach(x->res[0] += x.getValue());
		for(int a = 0; a < 10 && a < size; a++){
			Entry<Integer, Double> e = test.pollFirst();
			System.out.println("La page web '" + ((indexation2.get(e.getKey())==null)?"super-noeud":indexation2.get(e.getKey())) + "' a une probabilité de " + e.getValue());
			res[0] += e.getValue();
		}
		System.out.println("total = " + res[0]);
		System.out.println(size);
	}
}