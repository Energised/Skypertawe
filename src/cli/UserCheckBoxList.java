/**
 * UserCheckBoxList.java
 * @author Dan Woolsey
 *
 * Extension of CheckBoxList to handle sending friend requests via Home
 * Utilised and updated within UserSearchBox.java
 *
 * NB: generic in source code is V by default
 */

package src.cli;

import src.obj.*;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.*;

import java.util.ArrayList;

public class UserCheckBoxList<Account> extends CheckBoxList<Account>
{
    public UserCheckBoxList()
    {
        super();
    }

    @Override
    public Interactable.Result handleKeyStroke(KeyStroke keyStroke)
    {
        //List<String> checkedNames = this.getCheckedItems();
        if(keyStroke.getKeyType() == KeyType.Enter)
        {
            if(this.isChecked(this.getSelectedIndex())) // if current item is selected
            {
                return Interactable.Result.HANDLED; // end subroutine
            }
            else
            {
                // setup sending a friend record
                ArrayList<Account> request = new ArrayList<Account>();
                // acc1 taken either from UserSearchBox OR from main (new static variable)
            }

            // if unchecked
            // send a friend request (as ArrayList<Account>), remove name from list (or something better)
        }
        return Interactable.Result.HANDLED;
    }

    // override handleKeyStroke so can't uncheck an entry who is already a friend
    // if a user gets checked then send them a friend request via Main.graph.addRecord()
    // request in the order of USER ACCOUNT; RECEIVING ACCOUNT as ArrayList<Account>

}
