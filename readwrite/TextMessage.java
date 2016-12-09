// wenju mu

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
		// -> creates instance of ReadWriteMessage
		// -> call write_text_message();
	}

	public static void main(String[] args){
		TextMessage t = new TextMessage("user1","user2","who are you");
		System.out.println(t.getMessageContent());
	}
}
