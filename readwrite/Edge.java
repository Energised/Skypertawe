
public class Edge implements Comparable<Edge> {
	
	private Vertex one, two;
	private int weight;
	
	
	public Edge(Vertex one, Vertex two){
		this.one = one;
		this.two = two;
		this.weight = 1;
	}
	
	
	
	public Edge(Vertex one, Vertex two, int weight){
		//if one is ordered before two when .compareTo() used:
		//then one = one && two = two,
		//else one = two && two = one
		 this.one = (one.getUsername().toLowerCase().compareTo(two.getUsername().toLowerCase()) <= 0) ? one : two;
		 this.two = (this.one == one) ? two : one;
		 this.weight = weight;
	}

	
	
	//return the neighbour vertex along this edge
	public Vertex getEdges(Vertex current){
		if(!(current.equals(one) || current.equals(two))){
			return null;
		}
		return (current.equals(one)) ? two : one;
	}


	
	public Vertex getOne(){
		return this.one;
	}
	
	public Vertex getTwo(){
		return this.two;
	}

	public int getWeight(){
		return this.weight;
	}

	public void setWeight(int i){
		this.weight = i;
	}
	
	//compare the weights of two edges
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}

	//return a string representing the edge
	public String toString(){
		return "({" + one.getUsername() + ", " + two.getUsername() + "}, " + weight + ")";
	}
	
	//return the edge as a hashcode int
	public int hashCode(){
		return (one.getUsername() + two.getUsername()).hashCode();
	}
	

	
	//compare with another edge and return true if they have same vertices
	public boolean equals(Object other){
		//return false if the other object is not an edge
		if(!(other instanceof Edge)){
			return false;
		}
		//if it is an edge cast the object as an edge
		Edge e = (Edge)other;
		//compare and return true if vertices are the same
		return e.one.equals(this.one) &&  e.two.equals(this.two);
	}

	
	
	
}
