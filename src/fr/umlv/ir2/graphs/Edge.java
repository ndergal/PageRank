package fr.umlv.ir2.graphs;

public class Edge {
	private final int start;
	private final int end;

	
	public Edge(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return start + " -- " + end;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Edge)) {
			return false;
		}
		Edge tmp = (Edge) obj;
		return tmp.start == start && tmp.end == end;
	}
	
	public boolean equalsEdge(Object obj) {
		if (!(obj instanceof Edge)) {
			return false;
		}
		Edge tmp = (Edge) obj;
		return tmp.start == start && tmp.end == end ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
		return result;
	}
}