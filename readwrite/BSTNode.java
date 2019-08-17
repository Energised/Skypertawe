/**
 * the BSTNode objects will be used to make up a BST.
 * the BSTNodes contain an element of type Account, and a left/right references of type BSTNode
 * 
 * @author 830169
 * @version 1.0
 */
public class BSTNode {
	private Account element;
	private BSTNode left;
	private BSTNode right;
	
	/**
	 * constructor for BSTNode
	 * @param account the account that the element will reference
	 */
	public BSTNode(Account account){
		this.element = account;
		this.left = null;
		this.right = null;
	}


	/**
	 * setter for the element of the BSTNode
	 * @param account the new Account that the element attribute will reference
	 */
	public void setAccount (Account account){
		this.element = account;
	}
	/**
	 * getter for the Account that the element attribute references
	 * @return the Account object of this node
	 */
	public Account getAccount (){
		if(this.element != null){
			return this.element;
		}
		else{
			return null;
		}
	}
	
	/**
	 * getter for the node to the left of this node
	 * @return reference to the left node
	 */
	public BSTNode getLeft (){
		if(this.left != null){
			return this.left;
		}
		else{
			return null;
		}
	}
	
	/**
	 * getter for node to the right of this node
	 * @return reference to the right node
	 */
	public BSTNode getRight (){
		if(this.right != null){
			return this.right;
		}
		else{
			return null;
		}
	}
	
	/**
	 * setter for the left node
	 * @param l reference to the new left node
	 */
	public void setLeft (BSTNode l){
		this.left = l;
	}
	
	/**
	 * setter for the right node
	 * @param r reference to the new right node
	 */
	public void setRight (BSTNode r){
		this.right = r;
	}

	
	
	
}
