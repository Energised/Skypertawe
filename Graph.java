import java.util.*;

//Example:

//Graph g = new Graph();
//Vertex v1 = new Vertex("Ricky3038");
//Vertex v2 = new Vertex("Juice");

//    this means do not overwrite if vertex already exists, true would allow overwrite		
//                      |
//                      V
//if (g.addVertex(v1, false) == true){
//	System.out.println("Vertex " + v1.getUsername() + " add successful");
//}
        //result: "Vertex Ricky3038 add successful"

//if (g.addVertex(v2, false) == true){
//	System.out.println("Vertex " + v2.getUsername() + " add successful");
//}
		//result: Vertex Juice add successful

//if (g.addVertex(v3, true) == true){
//	System.out.println("Vertex " + v3.getUsername() + " add successful");
//}
		//result: Vertex energised add successful

//g.addEdge(v1, v2);
//g.addEdge(v1, v3);

//Set<Edge> setOfEdges = g.getEdges();

//for (Edge e : setOfEdges) {
//    System.out.println(e.toString());

		//result: ({Juice, Ricky3038}, 1)
		//	  ({Ricky3038, energised}, 1)

//v1.getNumberEdges() = 2
//v2.getNumberEdges() = 1






public class Graph {

	private HashMap<String, Vertex> vertices;
	private HashMap<Integer, Edge> edges;

	//create empty graph
	public Graph(){
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Integer, Edge>();
	}
	
	//create graph with vertices from an arraylist
	public Graph(ArrayList<Vertex> vertices){
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Integer, Edge>();
		
		//adds vertices to hashmap based on username
		//if vertices have same username:
		//each successive vertex overwrites the previous
		for(Vertex v: vertices){
			this.vertices.put(v.getUsername(), v);
		}
	}
	
	
	
	//attempt to add an edge
	public boolean addEdge(Vertex one, Vertex two){
		return addEdge(one, two, 1);

	}
	
	//returns true if add was successful, false if not successful
	public boolean addEdge(Vertex one, Vertex two, int weight){
		//if one and two are the same vertex then false
		if(one.equals(two)){
			return false;
		}
		Edge e = new Edge(one, two, weight);
		//if the hashmap edges contains the key for this edge already then false
		if(edges.containsKey(e.hashCode())){
			return false;
		}
		// if vertex one or two already contain this edge then false
		else if(one.containsEdge(e) || two.containsEdge(e)){
			return false;
		}
		//add the edge to the hashmap edges
		edges.put(e.hashCode(), e);
		//add the edge to one and two
		one.addEdge(e);
		two.addEdge(e);
		return true;
	}
	
	
	//does the graph contain this edge?
	public boolean containsEdge(Edge e){
		if(e.getOne() == null || e.getTwo() == null){
			 return false;
		}
		//returns true if hashmap edges contains the hashcode key for the edge
		return this.edges.containsKey(e.hashCode());
	}

	
	//remove edge from graph
	public Edge removeEdge(Edge e){
		e.getOne().removeEdge(e);
		e.getTwo().removeEdge(e);
		return this.edges.remove(e.hashCode());
	}
	
	
	
	//add a vertex to the graph, returns true if add successful
	//boolean parameter value should be true if you want
	//to allow the vertex to be overwritten if it already exists
	public boolean addVertex(Vertex vertex, boolean overwriteExisting){
		Vertex current = this.vertices.get(vertex.getUsername());
		if(current != null){
			if(!overwriteExisting){
				 return false;
			}
			
			while(current.getNumberEdges() > 0){
				this.removeEdge(current.getEdge(0));
			}
		}
		vertices.put(vertex.getUsername(), vertex);
		return true;
	}
	
	
	
	
	
	
	//is this vertex in the graph?
	public boolean containsVertex(Vertex vertex){
		return this.vertices.get(vertex.getUsername()) != null;
	}

	//returns the vertex that has the specified username
	public Vertex getVertex(String username){
		return vertices.get(username);
	}

	
	//remove a vertex from the graph based on its username
	//returns the vertex that was removed
	public Vertex removeVertex(String username){
		//remove vertex from hashmap vertices
		Vertex v = vertices.remove(username);
		//remove all edges concerning this vertex
		while(v.getNumberEdges() > 0){
			this.removeEdge(v.getEdge(0));
		}
		return v;
	}
	
	
	//return set containing all edges of the graph
	public Set<Edge> getEdges(){
		return new HashSet<Edge>(this.edges.values());
	}
	
	
}
