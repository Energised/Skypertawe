/**
* Message.java
* @author Dan Woolsey
*
* Class to create Message objects
*/

package src;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Message
{

	private String recipient;
	private String sender;
	private String dateTime;

    private static final String DATE_FORMAT = "dd-MM-yyyy, HH:mm:ss";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	public Message(String recipient, String sender)
    {
		this.recipient = recipient;
		this.sender = sender;
		this.dateTime = dateFormat.format(new Date());
	}

	public void setRecipient(String recipient)
    {
		this.recipient = recipient;
	}

	public void setSender(String sender)
    {
		this.sender = sender;
	}

	/**
	* Used in rebuilding messages to preserve sent time
	*/

	public void setDateTime(String dateTime)
	{
		this.dateTime = dateTime;
	}

	public String getRecipient()
    {
		return this.recipient;
	}

	public String getSender()
    {
		return this.sender;
	}

    public String getDateTime()
    {
        return this.dateTime;
    }

	/**
	* Implemented for testing purposes
	*/

	public static void main(String[] args)
    {
		Message m = new Message("user1", "user2");// show the two user messsage detail & time
		System.out.println(m.getRecipient());
		System.out.println(m.getSender());
        System.out.println(m.getDateTime());
	}
}
