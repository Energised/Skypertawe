import java.util.ArrayList;

/**
* The Vertex class will be used in the Graph and Edge classes. It also uses the Edge class.
*  A vertex has a username to identify it by, and an arraylist of edges that it is involved in
*
* @author  830169
* @version 1.0 
*/

public class Vertex {
	//arraylist of edges connected to this vertex
	private ArrayList<Edge> edges;
	//username used to identify which account this vertex represents
	private String username;
	
	/**
	 * constructor for a vertex
	 * @param username the username that corresponds to the vertex
	 */
	public Vertex(String username){
		this.username = username;
		this.edges = new ArrayList<Edge>();
	}
	
	/**
	 * add an edge to the vertex's arraylist of edges that its involved in
	 * @param edge the edge to add
	 */
	public void addEdge(Edge edge){
		if (this.edges.contains(edge)){
			return;
		}
		this.edges.add(edge);
	}
	
	
	/**
	 * check if the edges arraylist contains a certain edge
	 * @param edge the edge being searched for
	 * @return returns true if the edge exists
	 */
	public boolean containsEdge(Edge edge){
		return this.edges.contains(edge);		
	}
	
	/**
	 * return the edge at specified index
	 * @param index the index of the arraylist that the edge is located at
	 * @return returns an edge
	 */
	public Edge getEdge(int index){
		return this.edges.get(index);
	}
	
	/**
	 * remove edge from the arraylist
	 * @param edge edge to be removed
	 */
	public void removeEdge(Edge edge){
		this.edges.remove(edge);
	}
	
	/**
	 * get number of edges
	 * @return the number of edges this vertex is involved in
	 */
	public int getNumberEdges(){
		return this.edges.size();		
	}
	
	/**
	 * returns the username of vertex
	 * @return username of the vertex
	 */
	public String getUsername(){
		return this.username;
	}
	
	/**
	 * return arraylist of edges for this vertex
	 * @return arraylist containing all edges this vertex is involved in
	 */
	public ArrayList<Edge> getEdges(){
		return this.edges;
	}
	
	
	
	
	
}
