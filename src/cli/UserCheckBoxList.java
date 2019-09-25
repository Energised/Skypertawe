/**
 * UserCheckBoxList.java
 * @author Dan Woolsey
 *
 * Extension of CheckBoxList to handle sending friend requests via Home
 * Utilised and updated within UserSearchBox.java
 *
 *  NB: Still need to setup other keyStroke inputs for this object
 * rough pseudocode for handleKeyStroke
 *  -> if ArrowDown is pressed
 *      -- select next item in list (doesnt move out of component)
 *  -> if ArrowUp is pressed
 *      -- select previous item in list (doesnt move out of component)
 *  -> if ArrowLeft is pressed
 *      -- move to previous component
 *  -> if ArrowRight is pressed
 *      -- move to next component
 *  -> if enter is pressed
 *      -- if item is already checked - return HANDLED
 *      -- otherwise
 *          - TODO: setup new record for a request
 *          - check name in box
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
        if(keyStroke.getKeyType() == KeyType.ArrowLeft){ return Interactable.Result.MOVE_FOCUS_PREVIOUS; }
        else if((keyStroke.getKeyType() == KeyType.ArrowRight) | (keyStroke.getKeyType() == KeyType.Tab))
        {
            return Interactable.Result.MOVE_FOCUS_NEXT;
        }
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
        else
        {
            super.handleKeyStroke(keyStroke);
        }
        return Interactable.Result.HANDLED;
    }

    // override handleKeyStroke so can't uncheck an entry who is already a friend
    // if a user gets checked then send them a friend request via Main.graph.addRecord()
    // if the request already exists then inform the user with a pop up
    // request in the order of USER ACCOUNT; RECEIVING ACCOUNT as ArrayList<Account>

}
