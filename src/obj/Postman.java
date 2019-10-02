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
 *
 * FUNCTION LIST
 *  public Postman(ArrayList<Account> accs)
 *
 *  SETUP + ORGANISE POST
 *
 *      public void fillPostBag(ArrayList<Account> accs)
 *      public ArrayList<Message> getMessagesByUser(String username)
 *      public HashMap<String, ArrayList<Message>> sortUserPostByConvo(Account a, ArrayList<Message> msgs)
 *      public void printBag()
 *
 * SEND POST
 *
 *      public int sendMessage(TextMessage msg)
 *
 *
 */

package src.obj;

import src.*;
import src.obj.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Postman
{

    HashMap<Account, ArrayList<Message>> postBag;

    public Postman(ArrayList<Account> accs) throws Exception
    {
        this.postBag = new HashMap<Account, ArrayList<Message>>();
        // postBag.put(account, arraylist)
        this.fillPostBag(accs);
    }

    public void fillPostBag(ArrayList<Account> accs) throws Exception
    {
        ArrayList<Message> post;
        ReadWriteMessage rwm = new ReadWriteMessage("data.db");
        for(Account a : accs)
        {
            post = rwm.ReadMessages(a.getUsername());
            this.postBag.put(a, post);
        }
    }

    public ArrayList<Message> getMessagesByUser(String username)
    {
        ArrayList<Message> msgs;
        for(Map.Entry m : this.postBag.entrySet())
        {
            Account test = (Account) m.getKey();
            if(test.getUsername().equals(username))
            {
                //System.out.println("FOUND");
                msgs = (ArrayList<Message>) m.getValue();
                return msgs;
            }
        }
        return null; // NB: placeholder for now
    }

    // if key already exists
    //      add message to the value associated w/ key
    // otherwise
    //      generate new ArrayList<Message>
    //      put message in that ArrayList
    //      insert whole record into org_msgs

    public HashMap<String, ArrayList<Message>> sortUserPostByConvo(Account a, ArrayList<Message> msgs)
    {
        String friend;
        HashMap<String, ArrayList<Message>> org_msgs = new HashMap<String, ArrayList<Message>>();
        for(Message m : msgs)
        {
            String recipient = m.getRecipient();
            String sender = m.getSender();
            if(recipient.equals(a.getUsername()))
            {
                friend = sender;
            }
            else
            {
                friend = recipient;
            }
            //System.out.println("FRIEND: " + friend);
            if(org_msgs.containsKey(friend))
            {
                ArrayList<Message> val = org_msgs.get(friend);
                val.add(m);
                org_msgs.replace(friend, val);
            }
            else
            {
                ArrayList<Message> val = new ArrayList<Message>();
                val.add(m);
                org_msgs.put(friend, val);
            }
            //org_msgs.put(friend, m);
        }

        return org_msgs;
    }

    public void printBag()
    {
        Account t1;
        ArrayList<Message> msgs;
        for(Map.Entry m : postBag.entrySet())
        {
            t1 = (Account) m.getKey(); // must cast back to Account to use functions
            System.out.println("-> " + t1.getUsername() + " Messages");
            msgs = (ArrayList<Message>) m.getValue();
            for(Message x : msgs)
            {
                System.out.println("--- " + x);
                //System.out.println(x.getClass());
            }
        }
    }

    public int sendMessage(TextMessage msg) throws Exception
    {
        ReadWriteMessage rwm = new ReadWriteMessage("data.db");
        rwm.WriteTextMessage(msg);
        rwm.close();
        return 0;
    }

    public static void main(String[] args) throws Exception
    {
        ReadWriteAccount rwa = new ReadWriteAccount("data.db");
        ArrayList<Account> accs = rwa.ReadAllAccounts();

        Postman p = new Postman(accs);
        //p.printBag();
        Account g = rwa.ReadUserAccount("gman");
        ArrayList<Message> g_msgs = p.getMessagesByUser("gman");
        //ArrayList<Message> g_msgs = p.postBag.get(g);
        HashMap<String, ArrayList<Message>> msgs = p.sortUserPostByConvo(g, g_msgs);

        ArrayList<Message> z_msgs = msgs.get("zapdos");

        if(z_msgs != null)
        {
            System.out.println("is not null");
        }
        else
        {
            System.out.println("is null");
        }

        for(Map.Entry m : msgs.entrySet())
        {
            System.out.println(m.getKey());
            ArrayList<Message> ms = (ArrayList<Message>) m.getValue();
            for(Message x : ms)
            {
                System.out.println(x);
            }
        }
    }
}
