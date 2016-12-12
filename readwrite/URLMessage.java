/**
* URLMessage.java
* @author Wenju Mu, Dan Woolsey
*
* Class containing information about a URLMessage
*/


import java.awt.Desktop;
import java.net.URI;

public class URLMessage extends TextMessage{

	private String url;

	/**
	* Create a URLMessage
	*/

	public URLMessage(String recipient, String sender, String msg, String url){
		super(recipient, sender, msg);
		this.url = url;
	}

	/**
	* @param url new URL
	*/

	public void set_url(String url){
		this.url = url;
	}

	/**
	* @return String of the embedded URL
	*/

	public String getURL(){
		return this.url;
	}

	/**
	* Opens the url embedded in the message with the users default browser
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
		// unused
	}

	/**
	* In place for testing purposes
	*/

	public static void main(String[] args){
		URLMessage u = new URLMessage("u1","u2","look","www.google.com");// example to open google link
		u.openURL();

	}
}
