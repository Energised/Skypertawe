/**
* The Edge class is used in the Graph and Vertex classes. It also uses the Vertex class.
* An edge consists of the two vertices that the edge is between, and a weight of the edge.
*
* @author  830169
* @version 1.0
*/
public class Edge implements Comparable<Edge> {
	
	private Vertex one, two;
	private int weight;
	
	/**
	 * constructor for edge
	 * @param one the first vertex involved in this edge
	 * @param two the second vertex involved in this edge
	 */
	public Edge(Vertex one, Vertex two){
		this.one = one;
		this.two = two;
		this.weight = 1;
	}
	
	
	/**
	 * alternative constructor for edge
	 * @param one the first vertex involved in this edge
	 * @param two the second vertex involved in this edge
	 * @param weight the weight of this edge
	 */
	public Edge(Vertex one, Vertex two, int weight){
		//if one is ordered before two when .compareTo() used:
		//then one = one && two = two,
		//else one = two && two = one
		 this.one = (one.getUsername().toLowerCase().compareTo(two.getUsername().toLowerCase()) <= 0) ? one : two;
		 this.two = (this.one == one) ? two : one;
		 this.weight = weight;
	}

	
	/**
	 * return the neighbour vertex along this edge
	 * @param current the current vertex
	 * @return returns the current vertex whether it is the first or second vertex of an edge
	 */
	public Vertex getVertices(Vertex current){
		if(!(current.equals(one) || current.equals(two))){
			return null;
		}
		return (current.equals(one)) ? two : one;
	}


	/**
	 * getter for the first vertex of this edge
	 * @return the first vertex involved in this edge
	 */
	public Vertex getOne(){
		return this.one;
	}
	
	/**
	 * getter for the second vertex of this edge
	 * @return the second vertex involved in this edge
	 */
	public Vertex getTwo(){
		return this.two;
	}
	
	/**
	 * getter for the weight of this edge
	 * @return the integer value of the weight of this edge
	 */
	public int getWeight(){
		return this.weight;
	}
	
	/**
	 * setter for the weight of this edge
	 * @param i the int value to set the weight of this edge to
	 */
	public void setWeight(int i){
		this.weight = i;
	}
	
	/**
	 * compare the weights of two edges, returns the difference in weights of this edge and another edge
	 * @param other the difference in weights of this edge and another edge
	 */
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}
	
	/**
	 * return a string representing the edge
	 * @return a string representation of the edge
	 */
	public String toString(){
		return "({" + one.getUsername() + ", " + two.getUsername() + "})";
	}
	
	/**
	 * return the edge as a hashcode int
	 * @return the hashcode representation of this edge
	 */
	public int hashCode(){
		return (one.getUsername() + two.getUsername()).hashCode();
	}
	

	/**
	 * compare with another edge and return true if they have same vertices
	 * @param other the other edge to compare to
	 * @return returns true if the vertices of both edges are the same
	 */
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
