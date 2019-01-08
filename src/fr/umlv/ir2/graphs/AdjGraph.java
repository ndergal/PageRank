package fr.umlv.ir2.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AdjGraph implements Graph {
	private final ArrayList<Sommet> sommets;
	private final int n; // number of vertices
	private int size;

	class Sommet{
		LinkedList<Integer> predecesseurs = new LinkedList<>();
		int nbSucc = 0;
		
		void addPredecesseur(int j){
			predecesseurs.add(j);
		}
	}

	public AdjGraph(int n) {
		this.n = n;
		this.sommets = new ArrayList<>(n);
		for(int i = 0; i < n; i++)
			this.sommets.add(i, new Sommet());
	}

	@Override
	public int numberOfEdges() {
		return size;
	}

	@Override
	public int numberOfVertices() {
		return n;
	}

	@Override
	public void addEdge(int i, int j) {
		if(i < n && j < n){
			this.sommets.get(i).addPredecesseur(j);
			this.sommets.get(j).nbSucc++;
			size++;
		}
	}

	@Override
	public boolean isSuccesseur(int i, int j) {
//		if(i < n && j < n){
//			LinkedList<Edge> list = this.successeurs.get(i);
//			for(Edge e : list){
//				if(e.equalsEdge(new Edge(i, j))){
//					return true;
//				}
//			}
//		}
		return false;
	}

	@Override
	public void printSuccesseurs(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++)
				sb.append((isSuccesseur(i, j)?1:0) + "|");
			sb.append("\r\n");
		}
		System.out.println(sb.toString());
	}

	@Override
	public void printPredecesseurs(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++)
				sb.append((isSuccesseur(j, i)?1:0) + "|");
			sb.append("\r\n");
		}
		System.out.println(sb.toString());
	}

	@Override
	public List<Integer> getSuccesseurs(int i) {
		List<Integer> list = new ArrayList<>();
		for(int j = 0; j < n; j++)
			if(isSuccesseur(i, j))
				list.add(j);
		return list;
	}

	@Override
	public List<Integer> getPredecesseurs(int i) {
		return sommets.get(i).predecesseurs;
	}

	@Override
	public Integer getNbSucc(int i) {
		return sommets.get(i).nbSucc;
	}

	@Override
	public Integer getNbVoisins(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	//public String toString() {
	//	StringBuilder sb = new StringBuilder();
	//	for(int i = 0; i < n; i++){
	//		for(int j = 0; j < n; j++)
	//			sb.append((isEdge(i, j)?1:0) + "|");
	//		sb.append("\r\n");
	//	}
	//	return sb.toString();
	//}
}

//public class AdjGraph implements Graph {
//	private final ArrayList<LinkedList<Edge>> successeurs;
//	private final int n; // number of vertices
//	private int size;
//
//	public AdjGraph(int n) {
//		this.n = n;
//		this.successeurs = new ArrayList<>(n);
//		for(int i = 0; i < n; i++){
//			this.successeurs.add(i, new LinkedList<Edge>());
//		}
//	}
//	
//	@Override
//	public int numberOfEdges() {
//		return size;
//	}
//	
//	@Override
//	public int numberOfVertices() {
//		return n;
//	}
//	
//	@Override
//	public void addEdge(int i, int j) {
//		if(i < n && j < n){
//			this.successeurs.get(i).add(new Edge(i,j));
//			size++;
//		}
//	}
//
//	@Override
//	public boolean isSuccesseur(int i, int j) {
//		if(i < n && j < n){
//			LinkedList<Edge> list = this.successeurs.get(i);
//			for(Edge e : list){
//				if(e.equalsEdge(new Edge(i, j))){
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public void printSuccesseurs(){
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < n; i++){
//			for(int j = 0; j < n; j++)
//				sb.append((isSuccesseur(i, j)?1:0) + "|");
//			sb.append("\r\n");
//		}
//		System.out.println(sb.toString());
//	}
//	
//	@Override
//	public void printPredecesseurs(){
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < n; i++){
//			for(int j = 0; j < n; j++)
//				sb.append((isSuccesseur(j, i)?1:0) + "|");
//			sb.append("\r\n");
//		}
//		System.out.println(sb.toString());
//	}
//
//	@Override
//	public List<Integer> getSuccesseurs(int i) {
//		List<Integer> list = new ArrayList<>();
//		for(int j = 0; j < n; j++)
//			if(isSuccesseur(i, j))
//				list.add(j);
//		return list;
//	}
//
//	@Override
//	public List<Integer> getPredecesseurs(int i) {
//		List<Integer> list = new ArrayList<>();
//		for(int j = 0; j < n; j++)
//			if(isSuccesseur(j, i))
//				list.add(j);
//		return list;
//	}
//
//	@Override
//	public Integer getNbVoisins(int i) {
//		int nbVoisins = 0;
//		//HashMap<Integer, Integer> voisins = new HashMap<>();
//		for(int j = 1; j < n; j++){
//			if(isSuccesseur(i, j) || isSuccesseur(j, i))
//				nbVoisins++;
//		}
//		return nbVoisins;
//	}
//	
////	@Override
////	public String toString() {
////		StringBuilder sb = new StringBuilder();
////		for(int i = 0; i < n; i++){
////			for(int j = 0; j < n; j++)
////				sb.append((isEdge(i, j)?1:0) + "|");
////			sb.append("\r\n");
////		}
////		return sb.toString();
////	}
//}