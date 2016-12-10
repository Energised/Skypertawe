
public class BSTNode {
	private Account element;
	private BSTNode left;
	private BSTNode right;
	
	public BSTNode(Account m){
		this.element = m;
		this.left = null;
		this.right = null;
	}


	
	public void setAccount (Account m){
		this.element = m;
	}
	
	public Account getAccount (){
		if(this.element != null){
			return this.element;
		}
		else{
			return null;
		}
	}

	public BSTNode getLeft (){
		if(this.left != null){
			return this.left;
		}
		else{
			return null;
		}
	}
	
	public BSTNode getRight (){
		if(this.right != null){
			return this.right;
		}
		else{
			return null;
		}
	}
	
	public void setLeft (BSTNode l){
		this.left = l;
	}
	
	public void setRight (BSTNode r){
		this.right = r;
	}

	
	
	
}