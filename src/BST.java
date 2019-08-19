/**
 * BST.java
 * @author Dan Woolsey
 *
 * Binary Search Tree implementation for Skypertawe
 */

package src;

import java.util.ArrayList;

public class BST
{
    private Node root;
    private Node placeholder;
    private int numOfNodes;

    private ArrayList<Account> orderedAccounts = new ArrayList<Account>();

    private ReadWriteAccount rwa;

    public BST()
    {
        this.root = null;
    }

    // getters and setters
    // NB: only need getRoot() for now

    public Node getRoot()
    {
        return this.root;
    }

    public void insertAccount(Account a)
    {
        if(this.root == null)
        {
            this.numOfNodes++;
            this.root = new Node(a);
        }
        else
        {
            placeholder = this.root;
            this.numOfNodes++;
            findInsertLocation(a);
        }
    }

    public void findInsertLocation(Account a)
    {
        String nameToAdd = a.getUsername().toLowerCase();
        String nameToCompare = placeholder.getValue().getUsername().toLowerCase();
        int comparison = nameToAdd.compareTo(nameToCompare);
        if(comparison <= 0)
        {
            if(placeholder.getLeftChild() != null)
            {
                placeholder = placeholder.getLeftChild();
                findInsertLocation(a);
            }
            else
            {
                placeholder.setLeftChild(new Node(a));
            }
        }
        else
        {
            if(placeholder.getRightChild() != null)
            {
                placeholder = placeholder.getRightChild();
                findInsertLocation(a);
            }
            else
            {
                placeholder.setRightChild(new Node(a));
            }
        }
    }

    /**
     * TESTING PURPOSES ONLY
     */
    public void inorderTreeWalk(Node x)
    {
        if(x != null)
        {
            inorderTreeWalk(x.getLeftChild());
            System.out.println(x.getValue().getUsername());
            inorderTreeWalk(x.getRightChild());
        }
    }

    public ArrayList<Account> inorderAccountWalk(Node x)
    {
        if(x != null)
        {
            inorderAccountWalk(x.getLeftChild());
            this.orderedAccounts.add(x.getValue());
            inorderAccountWalk(x.getRightChild());
        }
        return this.orderedAccounts;
    }

    public void populateTree() throws Exception
    {
        rwa = new ReadWriteAccount("data.db");
        ArrayList<Account> users = rwa.ReadAllAccounts();
        for(Account a : users)
        {
            this.insertAccount(a);
        }
    }

    public static void main(String[] args)
    {
        Account ac1 = new Account("energised", "Dan", "Woolsey", "07523050753", "17/01", "Swansea", 0, null, "profile-img.jpg");
        Account ac2 = new Account("gman", "Gary", "Waho", "07649752134", "7/12", "Bradford", 0, null, "profile-img.jpg");
        Account ac3 = new Account("bobby", "Bob", "Cobb", "07432257152", "9/11", "Ramsgate", 0, null, "profile-img.jpg");

        BST tree = new BST();
        tree.insertAccount(ac1);
        tree.insertAccount(ac2);
        tree.insertAccount(ac3);

        //tree.inorderTreeWalk(tree.root);
    }
}
