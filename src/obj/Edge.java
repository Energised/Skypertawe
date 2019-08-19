/**
 * Edge.java
 * @author Dan Woolsey
 *
 * Edge class for use with Vertex and Graph classes
 */

package src.obj;

class Edge
{
    public Vertex v1;
    public Vertex v2;
    private int weight;

    public Edge(Vertex v1, Vertex v2, int weight)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public int getWeight()
    {
        return this.weight;
    }

    public String toString()
    {
        return "User 1 -> " + v1.getAccount().getUsername() + "\n" +
               "User 2 -> " + v2.getAccount().getUsername() + "\n" +
               "Weight = " + this.getWeight();
    }
}
