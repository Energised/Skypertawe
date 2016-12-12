import java.util.*;

/**
* The Graph class creates a graph using objects from the Vertex class and objects from the Edge class
*
* @author 830169
* @version 1.0
*/


public class Graph {

	private HashMap<String, Vertex> vertices;
	private HashMap<Integer, Edge> edges;


	/**
	 * create empty graph
	 */
	public Graph(){
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Integer, Edge>();
	}

	/**
	 * create graph with vertices from an arraylist
	 * @param vertices an arraylist of vertices at be added to the graph
	 */
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



	/**
	 * attempt to add an edge
	 * @param one a first vertex to create edge between
	 * @param two a second vertex to create edge between
	 * @return a boolean that is true if the add was successful
	 */
	public boolean addEdge(Vertex one, Vertex two){
		return addEdge(one, two, 1);

	}

	/**
	 * returns true if add was successful, false if not successful
	 * @param one a first vertex to create edge between
	 * @param two a second vertex to create edge between
	 * @param weight the weight of the edge
	 */
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

	/**
	 * check if an edge has two vertices, if not it is likely to be deleted as it isn't a complete edge
	 * @param e edge being searched for
	 * @return true if the edge has two vertices, false otherwise
	 */
	public boolean containsEdge(Edge e){
		if(e.getOne() == null || e.getTwo() == null){
			 return false;
		}
		//returns true if hashmap edges contains the hashcode key for the edge
		return this.edges.containsKey(e.hashCode());
	}

	/**
	 * remove edge from graph
	 * @param e edge to be removed
	 * @return the edge that has been removed
	 */
	public Edge removeEdge(Edge e){
		e.getOne().removeEdge(e);
		e.getTwo().removeEdge(e);
		return this.edges.remove(e.hashCode());
	}


	/**
	 * add a vertex to the graph, returns true if add successful
	 * boolean parameter value should be true if you want
	 * to allow the vertex to be overwritten if it already exists
	 * @param vertex the vertex to be added to graph
	 * @param overWriteExisting true = allow overwriting if vertex already exists
	 * @return returns true if the vertex was added successfully, false otherwise
	 */
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





	/**
	 * check if the vertex is in the graphs vertices hashmap
	 * @param vertex the vertex being searched for
	 * @return returns true if the vertex is in the graph
	 */
	public boolean containsVertex(Vertex vertex){
		return this.vertices.get(vertex.getUsername()) != null;
	}


	/**
	 * returns the vertex from vertices hashmap that has the specified username
	 * @param username the username of the vertex being searched for
	 * @return the vertex that was searched for by its username
	 */
	public Vertex getVertex(String username){
		return vertices.get(username);
	}

	/**
	 * remove a vertex from the graph based on its username
	 * @param username the username of the vertex to be removed
	 * @return the vertex that was removed from the graph
	 */
	public Vertex removeVertex(String username){
		//remove vertex from hashmap vertices
		Vertex v = vertices.remove(username);
		//remove all edges concerning this vertex
		while(v.getNumberEdges() > 0){
			this.removeEdge(v.getEdge(0));
		}
		return v;
	}

	/**
	 * return set containing all edges of the graph
	 * @return a set containing all edges of the graph
	 */
	public Set<Edge> getEdges(){
		return new HashSet<Edge>(this.edges.values());
	}


	/**
	 * returns an arraylist containing the usernames, as strings, of all friends of the specified user
	 * @param username the username of the person whose friends are being searched for
	 * @return an arraylist containing usernames as strings
	 */
	public ArrayList<String> getFriends(String username){
		ArrayList<Edge> friendships = vertices.get(username).getEdges();
		ArrayList<String> friends = new ArrayList<String>();
		for (Edge friendship : friendships) {
			String friend = (friendship.getOne() == vertices.get(username)) ? friendship.getTwo().getUsername() : friendship.getOne().getUsername();
			if (friend != null){
				friends.add(friend);
			}
		}
		return friends;

	}



}
