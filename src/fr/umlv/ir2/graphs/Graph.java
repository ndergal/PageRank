package fr.umlv.ir2.graphs;

import java.util.List;

public interface Graph {

	public final static int maxGraph = 1000;
	public final static int minGraph = -1000;

	/**
	 * Le nombre d'ar�tes du graphe.
	 * @return le nombre d'ar�tes du graphe
	 */
	int numberOfEdges();
	
	/**
	 * Le nombre de sommets du graphe.
	 * @return le nombre de sommets du graphe
	 */
	int numberOfVertices();

	/**
	 * Permet d'ajouter une ar�te orient�e au graphe.
	 * @param i la premi�re extr�mit� de l'ar�te
	 * @param j la seconde extr�mit� de l'ar�te
	 * @throws IndexOutOfBoundsException si i ou j n'est pas un sommet du graphe
	 */
	void addEdge(int i, int j);

	/**
	 * Teste l'existence d'une ar�te donn�e
	 * @param i la premi�re extr�mit� de l'ar�te
	 * @param j la seconde extr�mit� de l'ar�te
	 * @return true s'il existe une ar�te entre i et j; false sinon
	 * @throws IndexOutOfBoundsException si i ou j n'est pas un sommet du graphe
	 */
	boolean isSuccesseur(int i, int j);
	List<Integer> getSuccesseurs(int i);
	List<Integer> getPredecesseurs(int i);
	Integer getNbVoisins(int i);
	public void printSuccesseurs();
	public void printPredecesseurs();

	Integer getNbSucc(int i);
	
	/**
	 * Renvoie la probabilité d'un sommet donné.
	 * @param i le sommet
	 * @return la probabilité du sommet i
	 * @throws IndexOutOfBoundsException si i n'est pas un sommet du graphe
	 */
//	int getWeight(int i);

	/**
	 * Un it�rateur sur tous les voisins d'un sommet donn�.
	 * @param i le sommet � partir duquel partent les ar�tes fournies par l'it�rateur
	 * @return un it�rateur sur tous les voisins du sommet i
	 * @throws IndexOutOfBoundsException si i n'est pas un sommet du graphe
	 */
//	Iterator<Edge> edgeIterator(int i);

	/**
	 * Effectue une action sur tous les voisins (les ar�tes) d'un sommet donn�.
	 * @param i le sommet � partir duquel partent les ar�tes trait�es
	 * @param consumer l'acction effectu�e sur toutes les ar�tes voisines de i
	 * @throws IndexOutOfBoundsException si i n'est pas un sommet du graphe
	 */
//	void forEachEdge(int i, Consumer<Edge> consumer);

	/**
	 * Fournit une r�pr�sentaiuon du graphe au format .dot
	 * @return une r�pr�sentaiuon du graphe au format .dot
	 */
//	default String toGraphviz() { 
//		return null; // TODO � impl�menter 
//	}
}