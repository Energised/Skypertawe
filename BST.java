
//to add account:
//treeName.addAccount(Account);

// search results returned as an array of Accounts
// some arrays contain null values at the end, use:   if (arrayName[i] != null){String username = arrayName[i].getUsername} etc

//search options:
//Account[] arrayName = treeName.searchBeginningWith("name");
//Account[] arrayName = treeName.searchExact("name");
//Account[] arrayName = treeName.searchContains("name");
//Account[] arrayName = treeName.getAllUsers("name");

public class BST {
	private BSTNode root;
	private BSTNode temp = this.root;
	
	//attributes used to store data gathered in recursive methods
	private int numberResults = 0;
	private Account[] searchResult = new Account[0];
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
	public Account[] searchBeginningWith(String searchString){
		sortBST();
		//reset index and searchResult
		setIndex(0);
		Account[] searchResult = new Account[0];		
		setSearchResult(searchResult);
		//begin search
		searchBeginningWith(searchString, this.root);
		//return result
		return getSearchResult();
	}
	
	
	//finds first item in the tree that begins with search string
	private void searchBeginningWith(String searchString, BSTNode temp){
		//reset 
		String usernameSubstring = temp.getAccount().getUsername().toLowerCase().substring(0, searchString.length());
		if (usernameSubstring.equals(searchString.toLowerCase())){
			findNumberOfResults(searchString, temp);
			searchResult = new Account[getResultCount()];
			setSearchResult(searchResult);
			addResultsToArray(searchString, temp, searchResult);
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
	
	
	
	//calculates the number of search results so that an array of correct size can be made
	private void findNumberOfResults(String searchString, BSTNode temp){
		
		if (temp.getLeft() != null){
			if (temp.getLeft().getAccount().getUsername().toLowerCase().substring(0, searchString.length()).equals(searchString.toLowerCase())){
			findNumberOfResults(searchString, temp.getLeft());
			}
		}
		
		resultCount();
		
		if (temp.getRight() != null){
			if (temp.getRight().getAccount().getUsername().toLowerCase().substring(0, searchString.length()).equals(searchString.toLowerCase())){
			findNumberOfResults(searchString, temp.getRight());
			}
		}
	}
	
	//adds the search results to an array so they can be returned
	private void addResultsToArray(String searchString, BSTNode temp, Account[] searchResult){
			if (temp.getLeft() != null){
			if (temp.getLeft().getAccount().getUsername().toLowerCase().substring(0, searchString.length()).equals(searchString.toLowerCase())){
				addResultsToArray(searchString, temp.getLeft(), getSearchResult());
			}
		}
		
		int index = getIndex();
		searchResult[index] = temp.getAccount();
		setIndex(index+1);
		setSearchResult(searchResult);
		
		if (temp.getRight() != null){
			if (temp.getRight().getAccount().getUsername().toLowerCase().substring(0, searchString.length()).equals(searchString.toLowerCase())){
				addResultsToArray(searchString, temp.getRight(), getSearchResult());
			}
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
	public Account[] getAllUsers(){
		setSearchResult(new Account[getNumberNodes()]);
		setIndex(0);
		arrayAllUsers(this.root);
		searchResult = getSearchResult();
		return searchResult;
	}
	
	
	// adds all users to array
	private void arrayAllUsers (BSTNode temp){
		if(temp == null) return;
		  
		arrayAllUsers(temp.getLeft() );
		
		Account[] result = getSearchResult();
		result[getIndex()] = temp.getAccount();
		setIndex(getIndex()+1);
		setSearchResult(result);
		
		arrayAllUsers(temp.getRight() ); 
		  
		}
	
	//returns an array containing one Account that matches the search string
	public Account[] searchExact(String searchString){
		setSearchResult(new Account[1]);
		performExactSearch(searchString, this.root);
		return getSearchResult();
	}
	//performs the search for an exact match
	public void performExactSearch(String searchString, BSTNode temp){
		if (temp.getAccount().getUsername().toLowerCase().equals(searchString.toLowerCase())){
			Account[] result = getSearchResult();
			result[0] = temp.getAccount();
			setSearchResult(result);
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
	public Account[] searchContains(String searchString){
		setSearchResult(new Account[getNumberNodes()]);
		setIndex(0);
		arrayContains(this.root, searchString);
		searchResult = getSearchResult();
		return searchResult;
	}
	
	//find all Accounts with usernames that contain the search string, and add them to array
	private void arrayContains (BSTNode temp, String searchString){
		if(temp == null) return;
		  
		arrayContains(temp.getLeft(), searchString);
		
		String username = temp.getAccount().getUsername().toLowerCase();
		if (username.contains(searchString)){
			Account[] result = getSearchResult();
			result[getIndex()] = temp.getAccount();
			setIndex(getIndex()+1);
			setSearchResult(result);
		}
		
		arrayContains(temp.getRight(), searchString); 
		  
	}
	
	
	//sorts the BST so that user names beginning with same substring are next to each other alphabetically
	public void sortBST(){
		setIndex(0);
		setSearchResult(new Account[getNumberNodes()]);
		arrayAllUsers(this.root);		
		Account[] arrayOfUsers = getSearchResult();
		int midPoint = (arrayOfUsers.length / 2);
		BST newTree = new BST();
		
		for(int j = midPoint; j<arrayOfUsers.length; j++){
			newTree.addAccount(arrayOfUsers[j]);
		}
		 
		
		for(int i = midPoint-1; i>=0; i--){
			newTree.addAccount(arrayOfUsers[i]);
		}
		
		this.root = newTree.getRoot();
		this.numberOfNodes = newTree.getNumberNodes();
	}
	
	
	
	
	
	
	
	//getters and setters
	
	private void resultCount(){
		this.numberResults++;
	}
	
	private int getResultCount(){
		return this.numberResults;
	}
	
	
	private void setSearchResult(Account[] result){
		this.searchResult = result;
	}
	
	private Account[] getSearchResult(){
		return this.searchResult;
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
	
	


