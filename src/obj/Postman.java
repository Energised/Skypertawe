/**
 * Postman.java
 * @author Dan Woolsey
 *
 * Trivial message handling class
 *
 * Initially holds all messages in a Map<Account, ArrayList<Message>> (postBag)
 *
 * Using postBag.get(Account) will give us an ArrayList<Message> (all a users messages including what they sent)
 * Passed into sortConversations(ArrayList<Message>) will return a map appropriate for
 * the MessageWindow interface (Map<String, ArrayList<Message>>)
 */

package src.obj;

public class Postman
{
    public Postman()
    {
        // todo
    }
}
