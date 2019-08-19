/**
 * Node.java
 * @author Dan Woolsey
 *
 * Nodes for the BST implementation for Skypertawe
 */

package src.obj;

public class Node
{
    private Account value;
    private Node leftChild;
    private Node rightChild;

    public Node(Account a)
    {
        this.value = a;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Account getValue()
    {
        return this.value;
    }

    public void setValue(Account a)
    {
        this.value = a;
    }

    public Node getLeftChild()
    {
        return this.leftChild;
    }

    public void setLeftChild(Node l)
    {
        this.leftChild = l;
    }

    public Node getRightChild()
    {
        return this.rightChild;
    }

    public void setRightChild(Node r)
    {
        this.rightChild = r;
    }
}
