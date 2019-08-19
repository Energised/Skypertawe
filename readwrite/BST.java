import java.util.ArrayList;

/**
* The BST class creates a binary search tree that allows Account objects to be added as the elementsof BSTNode objects.
* BST has 4 different ways to retrieve Account objects from the tree.
*
* @author  830169
* @version 1.0
*/

public class BST {
	private BSTNode root;
	private BSTNode temp = this.root;
	private ArrayList<Account> searchResult;
	private int numberOfNodes = 0;

	public BST(){
		this.root = null;
	}

	/**
	 * attempt to add an account to the tree
	 * @param account the account to add to the tree
	 */
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

	/**
	 * find a location to insert the Account
	 * @param account the account to add
	 */
	private void findInsertLocation(Account account){
		String accountToAdd = account.getUsername().toLowerCase();
		String accountToCompare = temp.getAccount().getUsername().toLowerCase();
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

	/**
	 * begin searching for users who's username begins with the string being searched for
	 * @param searchString the string to search for
	 * @return the arraylist of accounts that were found by the search
	 */
	public ArrayList<Account> searchBeginningWith(String searchString){

		searchResult = new ArrayList<Account>();
		//begin search
		searchBeginningWith(searchString, this.root);
		//return result
		return this.searchResult;
	}

	/**
	 * finds first item in the tree that begins with search string
	 * @param searchString username being searched for
	 * @param temp the node reference that allows the tree to recursively call the method to traverse the tree
	 */
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


	/**
	 * adds the search results to an arraylist so they can be returned
	 * @param searchString the string being searched for
	 * @param temp the node reference that allows the tree to recursively call the method to traverse the tree
	 */
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

	/**
	 * print Account usernames alphabetically
	 * @param temp the node reference that allows the tree to recursively call the method to traverse the tree
	 */
	public void printAlphabetical (BSTNode temp){
		if(temp == null) return;
		printAlphabetical(temp.getLeft() );
		System.out.print("Username: " + temp.getAccount().getUsername() + "\n");
		printAlphabetical(temp.getRight() );
		}


	/**
	 * resets the searchResult arraylist, calls a method to add all Account objects to the arraylist, then returns the arraylist
	 * @return arraylist of all Account objects in the tree, ordered alphabetically by username
	 */
	public ArrayList<Account> getAllUsers(){
		this.searchResult = new ArrayList<Account>();
		arrayAllUsers(this.root);
		return this.searchResult;
	}

	/**
	 * adds all users to arraylist ordered alphabetically by username
	 * @param temp the node reference that allows the tree to recursively call the method to traverse the tree
	 */
	private void arrayAllUsers (BSTNode temp){
		if(temp == null) return;

		arrayAllUsers(temp.getLeft() );

		this.searchResult.add(temp.getAccount());

		arrayAllUsers(temp.getRight() );
		}

	/**
	 * returns an array containing the one Account that matches the search string exactly, empty if no match
	 * @param searchString string being searched for
	 * @return arraylist with one Account that matched the search string exactly, or empty if no match
	 */
	public ArrayList<Account> searchExact(String searchString){
		this.searchResult = new ArrayList<Account>();
		performExactSearch(searchString, this.root);
		return this.searchResult;
	}
	/**
	 * performs the search for an exact match
	 * @param searchString string being searched for
	 * @param temp the node reference that allows the tree to recursively call the method to traverse the tree
	 */
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

	/**
	 * resets the searchResult arraylist, calls method to perform search,
	 * returns arraylist of Account objects that contain the string
	 *
	 * @param searchString string being searched for
	 * @return arraylist of Account objects who's usernames contain the search string
	 */
	public ArrayList<Account> searchContains(String searchString){
		this.searchResult = new ArrayList<Account>();
		arrayContains(searchString, this.root);
		return this.searchResult;
	}

	/**
	 * find all Accounts with usernames that contain the search string, and add them to arraylist
	 * @param searchString string being searched for
	 * @param temp the node reference that allows the tree to recursively call the method to traverse the tree
	 */
	private void arrayContains (String searchString, BSTNode temp){
		if(temp == null) return;

		arrayContains(searchString, temp.getLeft());
		if (temp.getAccount().getUsername().toLowerCase().contains(searchString)){
			this.searchResult.add(temp.getAccount());
		}

		arrayContains(searchString, temp.getRight());

	}


	/**
	 * add accounts from an ArrayList<Account> to the BST
	 * @param accounts arraylist of Account objects to be added to the tree
	 */
	public void addAccountsFromArrayList(ArrayList<Account> accounts){
		for (int i = 0; i < accounts.size(); i++){
			addAccount(accounts.get(i));
		}
	}



	/**
	 * getter for number of nodes
	 * @return the number of nodes in the tree
	 */
	public int getNumberNodes(){
		return this.numberOfNodes;
	}

	/**
	 * getter for the root of the tree
	 * @return a reference to the root of the tree
	 */
	public BSTNode getRoot(){
		return this.root;
	}

}
