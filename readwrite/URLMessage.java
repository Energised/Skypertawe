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

	/**
	* opens the url embedded in the message in the users default browser
	*/

	public void openURL(){
		try{
			if(Desktop.isDesktopSupported()){
				Desktop.getDesktop().browse(new URI(this.getURL()));
			}
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}

	public void sendMessage(){
		// to implement:
		// -> call write_url_message() method in ReadWriteMessage
	}

	public static void main(String[] args){
		URLMessage u = new URLMessage("u1","u2","look","www.google.com");
		u.openURL();

	}
}
