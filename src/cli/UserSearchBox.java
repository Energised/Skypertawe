/**
 * UserSearchBox.java
 * @author Dan Woolsey
 *
 * TextBox based class handling input to display search results
 *
 *
 * rough pseudocode for handleKeyStroke
 *  -> if a character is pressed
 *      -- get the character, add to search string
 *      -- move caret forward and return HANDLED
 *  -> if backspace/delete is pressed
 *      -- test for search string length=0; return MOVE_FOCUS_PREVIOUS
 *      -- remove end character, move caret, return HANDLED
 *  -> if enter is pressed
 *      -- test for search string length=0 return MOVE_FOCUS_NEXT
 *      -- search tree for usernames beginning with search string
 *      -- search for friends of the currently logged in user
 *      -- insert into the UserCheckBoxList, checking them if they're already friends
 *      -- return MOVE_FOCUS_NEXT
 *  -> else
 *      -- return MOVE_FOCUS_NEXT
 */

package src.cli;

import src.*;
import src.obj.*;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.gui2.*;

import java.util.ArrayList;

public class UserSearchBox extends TextBox
{

    Account acc;
    String searchValue = "";
    UserCheckBoxList<String> searchedUsers;

    public UserSearchBox()
    {
        super();
        searchedUsers = new UserCheckBoxList<String>();
    }

    public UserSearchBox(Account acc)
    {
        super();
        this.acc = acc;
        searchedUsers = new UserCheckBoxList<String>();
    }

    @Override
    public Interactable.Result handleKeyStroke(KeyStroke keyStroke)
    {
        if(keyStroke.getKeyType() == KeyType.ArrowLeft){ return Interactable.Result.MOVE_FOCUS_PREVIOUS; }
        else if((keyStroke.getKeyType() == KeyType.ArrowRight) | (keyStroke.getKeyType() == KeyType.Tab))
        {
            return Interactable.Result.MOVE_FOCUS_NEXT;
        }
        else if(keyStroke.getKeyType() == KeyType.Character)
        {
            Character t = keyStroke.getCharacter();
            searchValue += t;
            this.setText(searchValue);
            this.setCaretPosition(searchValue.length());
            //searchedUsers.addItem(searchValue);
            return Interactable.Result.HANDLED;
        }
        else if((keyStroke.getKeyType() == KeyType.Backspace) || (keyStroke.getKeyType() == KeyType.Delete))
        {
            if(searchValue.length() == 0) // number comparison > string comparison
            {
                return Interactable.Result.MOVE_FOCUS_PREVIOUS;
            }
            searchValue = searchValue.substring(0, searchValue.length()-1);
            this.setText(searchValue);
            this.setCaretPosition(searchValue.length());
            return Interactable.Result.HANDLED;
        }
        else if(keyStroke.getKeyType() == KeyType.Enter)
        {
            if(searchValue.length() == 0)
            {
                return Interactable.Result.MOVE_FOCUS_NEXT;
            }
            searchedUsers.clearItems();
            ArrayList<Account> results = Main.tree.searchBeginningWith(searchValue);
            ArrayList<String> friends = Main.graph.getFriendNames(acc);
            for(Account a : results)
            {
                // check reflexive condition
                if(a.getUsername().equals(acc.getUsername()))
                {
                    searchedUsers.addItem(a.getUsername(), true);
                }
                else if(friends.contains(a.getUsername()))
                {
                    searchedUsers.addItem(a.getUsername(), true);
                }
                else
                {
                    searchedUsers.addItem(a.getUsername());
                }
            }
            //setSearchResults();
            return Interactable.Result.MOVE_FOCUS_NEXT;
        }
        else
        {
            super.handleKeyStroke(keyStroke);
        }
        return Interactable.Result.HANDLED;
    }
}
