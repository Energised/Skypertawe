/**
* Message.java
* @author Wenju Mu, Dan Woolsey
*
* Class to create Message objects
*/

import java.util.Date;

public class Message {

	private String recipient;
	private String sender;
	private Date dateTime;

	public Message(String recipient,String sender){
		this.recipient = recipient;
		this.sender = sender;
		this.dateTime = new Date();
	}

	public void setRecipient(String recipient){
		this.recipient = recipient;
	}

	public void setSender(String sender){
		this.sender = sender;
	}

	public String getRecipient(){
		return this.recipient;
	}

	public String getSender(){
		return this.sender;
	}

	public void sendMessage(){
		// to implement
	}

	/**
	* Implemented for testing purposes
	*/

	public static void main(String [] args){
		Message m = new Message("user1", "user2");// show the two user messsage detail & time
		System.out.println(m.getRecipient());
		System.out.println(m.getSender());
	}
}
