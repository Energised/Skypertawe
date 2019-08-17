/**
* TextMessage.java
* @author Wenju Mu, Dan Woolsey
*
* Class to construct a TextMessage object
*/


public class TextMessage extends Message{

	private String messageContent;

	public TextMessage(String recipient, String sender, String messageContent){
		super(recipient,sender);
		this.messageContent = messageContent;
	}

	public void setMessageContent(String messageContent){
		this.messageContent = messageContent;
	}

	public String getMessageContent()
	{
		return this.messageContent;
	}

	public void sendMessage(){
		// unused
	}

	/**
	* Implemented for testing purposes
	*/

	public static void main(String[] args){
		TextMessage t = new TextMessage("user1","user2","who are you");
		System.out.println(t.getMessageContent());
	}
}
