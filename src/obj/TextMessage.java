/**
* TextMessage.java
* @author Dan Woolsey
*
* Class to construct a TextMessage object
*/

package src.obj;

public class TextMessage extends Message
{

	private String messageContent;

	public TextMessage(String recipient, String sender, String messageContent)
    {
		super(recipient,sender);
		this.messageContent = messageContent;
	}

	/**
	* Overload constructor to handle rebuilding message objects when queried
	* from the database to preserve dateTime
	*/

	public TextMessage(String recipient, String sender, String dateTime, String messageContent)
	{
		super(recipient,sender);
		this.messageContent = messageContent;
		this.setDateTime(dateTime);
	}

	public void setMessageContent(String messageContent)
    {
		this.messageContent = messageContent;
	}

	public String getMessageContent()
	{
		return this.messageContent;
	}

	/**
	* Implemented for testing purposes
	*/

	public static void main(String[] args)
    {
		TextMessage t = new TextMessage("user1","user2","who are you");
        System.out.println("TO: " + t.getRecipient());
        System.out.println("FROM: " + t.getSender());
		System.out.println(t.getMessageContent());
        System.out.println("SENT AT: " + t.getDateTime());
	}
}
