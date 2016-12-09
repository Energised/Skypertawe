// wenju mu

import java.awt.Desktop;
import java.net.URI;

public class URLMessage extends TextMessage{

	private String url;

	public URLMessage(String recipient, String sender, String msg, String url){
		super(recipient, sender, msg);
		this.url = url;
	}

	public void set_url(String url){
		this.url = url;
	}

	public String getURL(){
		return this.url;
	}

	public void openURL(){
		if(Desktop.isDesktopSupported()){
			Desktop.getDesktop().browse(new URI(this.getURL()));
		}
		else{
			// display the url and message content on screen
		}
	}

	public void sendMessage(){
		// to implement:
		// -> call write_url_message() method in ReadWriteMessage
	}

	public static void main(String[] args){
		URLMessage u = new URLMessage("u1","u2","look","www.google.com");
		u.openURL()

	}
}
