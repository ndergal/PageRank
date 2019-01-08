package fr.umlv.ir2.graphs;

import java.util.ArrayList;
import java.util.List;

public class MatGraph implements Graph {
	private final int[][] mat;
	private final int n; // number of vertices
	private int size;

	public MatGraph(int n) {
		this.n = n;
		this.mat = new int[n][n];
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
		if (i < n && j < n) {
			mat[i][j] = 1;
			size++;
		}
	}

	@Override
	public boolean isSuccesseur(int i, int j) {
		return i < n && j < n && mat[i][j] != 0;
	}

	@Override
	public void printSuccesseurs() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++)
				sb.append(mat[i][j] + "|");
			sb.append("\r\n");
		}
		System.out.println(sb.toString());
	}

	@Override
	public void printPredecesseurs() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++)
				sb.append(mat[j][i] + "|");
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
		List<Integer> list = new ArrayList<>();
		for(int j = 0; j < n; j++)
			if(isSuccesseur(j, i))
				list.add(j);
		return list;
	}

	@Override
	public Integer getNbVoisins(int i) {
		int nbVoisins = 0;
		for(int j = 1; j < n; j++){
			if(isSuccesseur(i, j) || isSuccesseur(j, i))
				nbVoisins++;
		}
		return nbVoisins;
	}

	@Override
	public Integer getNbSucc(int i) {
		int nbSucc = 0;
		for(int j = 0; j < n; j++)
			if(isSuccesseur(i, j))
				nbSucc++;
		return nbSucc;
	}
	
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < n; i++){
//			for(int j = 0; j < n; j++)
//				sb.append(mat[i][j] + "|");
//			sb.append("\r\n");
//		}
//		return sb.toString();
//	}
}