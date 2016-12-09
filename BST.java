

//to add account:
//treeName.addAccount(account);
//or:
//treeName.addAccountsFromArrayList(accounts);


//search options:
// arrayName = treeName.searchBeginningWith("name");
// arrayName = treeName.searchExact("name");
// arrayName = treeName.searchContains("name");
// arrayName = treeName.getAllUsers("name");
import java.util.ArrayList;

public class BST {
	private BSTNode root;
	private BSTNode temp = this.root;
	
	//attributes used to store data gathered in recursive methods
	private int numberResults = 0;
	private ArrayList<Account> searchResult;
	private int arrayIndex;
	private int numberOfNodes = 0;
	
	public BST(){
		this.root = null;
	}
	
	//attempt to add an account to the tree
	public void addAccount (Account account){
		if (this.root == null){
			this.numberOfNodes++;
			this.root = new BSTNode(account);
		}
		else{
			temp = this.root;
			this.numberOfNodes++;
			findInsertLocation(account);
		}
	
	}
	//find a location to insert the Account
	private void findInsertLocation(Account account){
		String accountToAdd = account.getUsername().toLowerCase();
		String accountToCompare = temp.getAccount().getUsername().toLowerCase();;
		int comparisonValue = accountToAdd.compareTo(accountToCompare);
		if(comparisonValue <= 0){
			if(temp.getLeft() != null){
				temp = temp.getLeft();
				findInsertLocation(account);
			}
			else{
				temp.setLeft(new BSTNode(account));
			}
		}
		
		if(comparisonValue > 0){
			if(temp.getRight() != null){
				temp = temp.getRight();
				findInsertLocation(account);
			}
			else{
				temp.setRight(new BSTNode(account));
			}
		}
	       
	}
	
	//begin searching for users who's username begins with the string being searched for
	//sorts so that names beginning with same string are together in the tree
	public ArrayList<Account> searchBeginningWith(String searchString){

		searchResult = new ArrayList<Account>();		
		//begin search
		searchBeginningWith(searchString, this.root);
		//return result
		return this.searchResult;
	}
	
	
	//finds first item in the tree that begins with search string
	private void searchBeginningWith(String searchString, BSTNode temp){
		//reset 
		String usernameSubstring = temp.getAccount().getUsername().toLowerCase().substring(0, searchString.length());
		if (usernameSubstring.equals(searchString.toLowerCase())){
			beginningWithToArraylist(searchString, temp);
		}
		else{
			String accountToCompare = temp.getAccount().getUsername().toLowerCase();
			int comparisonValue = searchString.toLowerCase().compareTo(accountToCompare);
			
			if(comparisonValue <= 0){
				if(temp.getLeft() != null){
					temp = temp.getLeft();
					searchBeginningWith(searchString, temp);
				}	
			}
			
			if(comparisonValue > 0){
				if(temp.getRight() != null){
					temp = temp.getRight();
					searchBeginningWith(searchString, temp);
				}
			}
		}
	}
	
	
	
	//adds the search results to an array so they can be returned
	private void beginningWithToArraylist(String searchString, BSTNode temp){
		if (temp.getLeft() != null){
			beginningWithToArraylist(searchString, temp.getLeft());
		}
		
		if (temp.getAccount() != null){
			if (temp.getAccount().getUsername().toLowerCase().substring(0, searchString.length()).equals(searchString.toLowerCase())){
				this.searchResult.add(temp.getAccount());
			}
		}
		
		if (temp.getRight() != null){
			beginningWithToArraylist(searchString, temp.getRight());
		}
		
	}
	
	
	//print Account usernames alphabetically
	public void printAlphabetical (BSTNode temp){
		if(temp == null) return;
		printAlphabetical(temp.getLeft() );
		System.out.print("Username: " + temp.getAccount().getUsername() + "\n");
		printAlphabetical(temp.getRight() ); 
		}


	
	//returns all Accounts in an array
	public ArrayList<Account> getAllUsers(){
		this.searchResult = new ArrayList<Account>();
		arrayAllUsers(this.root);
		return this.searchResult;
	}
	
	
	// adds all users to array
	private void arrayAllUsers (BSTNode temp){
		if(temp == null) return;
		  
		arrayAllUsers(temp.getLeft() );
		
		this.searchResult.add(temp.getAccount());
	
		arrayAllUsers(temp.getRight() ); 
		}
	
	//returns an array containing one Account that matches the search string
	public ArrayList<Account> searchExact(String searchString){
		this.searchResult = new ArrayList<Account>();
		performExactSearch(searchString, this.root);
		return this.searchResult;
	}
	//performs the search for an exact match
	public void performExactSearch(String searchString, BSTNode temp){
		if (temp.getAccount().getUsername().toLowerCase().equals(searchString.toLowerCase())){
			this.searchResult.add(temp.getAccount());
		}
				
		String accountToCompare = temp.getAccount().getUsername().toLowerCase();;
		int comparisonValue = searchString.toLowerCase().compareTo(accountToCompare);
		if(comparisonValue <= 0){
			if(temp.getLeft() != null){
				temp = temp.getLeft();
				performExactSearch(searchString, temp);
			}
		}
		
		if(comparisonValue > 0){
			if(temp.getRight() != null){
				temp = temp.getRight();
				performExactSearch(searchString, temp);
			}
		}   
		
	
	}
	
	//search entire tree for all Accounts who's username contains the search string
	public ArrayList<Account> searchContains(String searchString){
		this.searchResult = new ArrayList<Account>();
		arrayContains(this.root, searchString);
		return this.searchResult;
	}
	
	//find all Accounts with usernames that contain the search string, and add them to array
	private void arrayContains (BSTNode temp, String searchString){
		if(temp == null) return;
		  
		arrayContains(temp.getLeft(), searchString);
		if (temp.getAccount().getUsername().toLowerCase().contains(searchString)){
			this.searchResult.add(temp.getAccount());
		}
		
		arrayContains(temp.getRight(), searchString); 
		  
	}
	
	

	
	
	//add accounts from an ArrayList<Account> to the BST
	private void addAccountsFromArrayList(ArrayList<Account> accounts){
		for (int i = 0; i < accounts.size(); i++){
			addAccount(accounts.get(i));
		}
	}
	
	
	
	
	
	
	//getters and setters
	
	private void resultCount(){
		this.numberResults++;
	}
	
	private int getResultCount(){
		return this.numberResults;
	}	
	
	
	private void setIndex(int i){
		this.arrayIndex = i;
	}
	
	private int getIndex(){
		return this.arrayIndex;
	}
	
	public int getNumberNodes(){
		return this.numberOfNodes;
	}
	

	public BSTNode getRoot(){
		return this.root;
	}
	
}
	
	





