/**
 * UserCheckBoxList.java
 * @author Dan Woolsey
 *
 * Extension of CheckBoxList to handle sending friend requests via Home
 * Utilised and updated within UserSearchBox.java
 *
 * rough pseudocode for handleKeyStroke
 *  ->
 *
 *
 *
 *
 *
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
        if(keyStroke.getKeyType() == KeyType.ArrowDown){ return Interactable.Result.MOVE_FOCUS_DOWN; }
        else if(keyStroke.getKeyType() == KeyType.ArrowUp){ return Interactable.Result.MOVE_FOCUS_UP; }
        else if(keyStroke.getKeyType() == KeyType.Backspace){ return Interactable.Result.MOVE_FOCUS_UP; }
        else if(keyStroke.getKeyType() == KeyType.Enter)
        {
            if(this.isChecked(this.getSelectedItem())) // if current item is checked
            {
                return Interactable.Result.HANDLED; // end subroutine
            }
            else
            {

                this.setChecked(this.getSelectedItem(), true);

                // setup sending a friend record
                // ArrayList<Account> request = new ArrayList<Account>();
                // acc1 taken either from UserSearchBox OR from main (new static variable)
            }
        }
        return Interactable.Result.HANDLED;
    }

    // override handleKeyStroke so can't uncheck an entry who is already a friend
    // if a user gets checked then send them a friend request via Main.graph.addRecord()
    // request in the order of USER ACCOUNT; RECEIVING ACCOUNT as ArrayList<Account>

}
