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

import src.*;
import src.obj.*;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.gui2.dialogs.*;

import java.util.ArrayList;

public class UserCheckBoxList<V> extends CheckBoxList<V>
{

    String a;

    public UserCheckBoxList(String a)
    {
        super();
        this.a = a;
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
                // setup sending a friend request
                String b = (String) this.getSelectedItem();

                Account sender = Main.tree.searchBeginningWith(a).get(0);
                Account recipient = Main.tree.searchBeginningWith(b).get(0);

                ArrayList<Account> request = new ArrayList<Account>();
                request.add(sender);
                request.add(recipient);

                int check;
                try
                {
                    check = Main.graph.addRecord(request);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    check = 0;
                }
                if(check == 1)
                {
                    MessageDialogButton mdb = MessageDialog.showMessageDialog(Main.w,
                                                        "Sent","Request to " + b + " sent!",
                                                        MessageDialogButton.Close);

                    this.setChecked(this.getSelectedItem(), true);
                }
                else
                {
                    // message saying failure?
                }
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
