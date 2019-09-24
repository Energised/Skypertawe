/**
 * UserSearchBox.java
 * @author Dan Woolsey
 *
 * TextBox based class handling input to display search results
 // * NB: Backspace seems to exit if the UserSearchBox is empty???
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
        if(keyStroke.getKeyType() == KeyType.Character)
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
            if(searchValue == "")
            {
                return Interactable.Result.UNHANDLED;
            }
            searchValue = searchValue.substring(0, searchValue.length()-1);
            this.setText(searchValue);
            this.setCaretPosition(searchValue.length());
            return Interactable.Result.HANDLED;
        }
        else if(keyStroke.getKeyType() == KeyType.Enter)
        {
            if(searchValue == "")
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
            return Interactable.Result.MOVE_FOCUS_NEXT;
        }
    }
}
