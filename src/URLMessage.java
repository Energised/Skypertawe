/**
 * URLMessage.java
 * @author Dan Woolsey
 *
 * Class to encapsulate a URL in a message
 */

package src;

 import java.awt.Desktop;
 import java.net.URI;

 public class URLMessage extends Message
 {

 	private String url;

 	/**
 	* Create a URLMessage
 	*/

 	public URLMessage(String recipient, String sender, String url)
    {
 		super(recipient, sender);
 		this.url = url;
 	}

    public URLMessage(String recipient, String sender, String dateTime, String url)
    {
        super(recipient, sender);
        this.url = url;
        this.setDateTime(dateTime);
    }

 	/**
 	* @param url new URL
 	*/

 	public void set_url(String url)
    {
 		this.url = url;
 	}

 	/**
 	* @return String of the embedded URL
 	*/

 	public String getURL()
    {
 		return this.url;
 	}

 	/**
 	* Opens the url embedded in the message with the users default browser
 	*/

 	public void openURL()
    {
 		try{
 			if(Desktop.isDesktopSupported()){
 				Desktop.getDesktop().browse(new URI(this.getURL()));
 			}
 		}catch(Exception e){
 			System.out.println(e.getStackTrace());
 		}
 	}

 	/**
 	* In place for testing purposes
 	*/

 	public static void main(String[] args)
    {
 		URLMessage u = new URLMessage("u1","u2","www.google.co.uk"); // example to open google link
 		u.openURL();

 	}
 }
