import java.util.ArrayList;

public class Vertex {
	//arraylist of edges connected to this vertex
	private ArrayList<Edge> edges;
	//username used to identify which account this vertex represents
	private String username;
	
	public Vertex(String username){
		this.username = username;
		this.edges = new ArrayList<Edge>();
	}
	
	
	//add an edge to arraylist
	public void addEdge(Edge edge){
		if (this.edges.contains(edge)){
			return;
		}
		this.edges.add(edge);
	}
	
	
	
	//does this edge exist in the arraylist
	public boolean containsEdge(Edge edge){
		return this.edges.contains(edge);		
	}
	
	
	//returns edge at index
	public Edge getEdge(int index){
		return this.edges.get(index);
	}
	
	//remove edge from the arraylist
	public void removeEdge(Edge edge){
		this.edges.remove(edge);
	}
	
	
	//get number of edges
	public int getNumberEdges(){
		return this.edges.size();		
	}
	
	//returns the username of vertex
	public String getUsername(){
		return this.username;
	}
	
	//return arraylist of edges for this vertex
	public ArrayList<Edge> getEdges(){
		return this.edges;
	}
	
	
	
	
	
}
