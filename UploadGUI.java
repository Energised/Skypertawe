import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField; 
import javax.swing.JPanel;
public class UploadGUI extends GUI {


	private static final long serialVersionUID = 1L;
	

	private UploadGUI() {
		super();
		
	}
	private void uploadsetup(){
		JLabel userLabel = new JLabel("Image name:");
		JTextField userText = new JTextField(20);
		JButton loginButton = new JButton("Add");
		JPanel panel = new JPanel();
		setSize(400,200);

		
		userLabel.setBounds(20,20,80,25);
		

		
	
		userText.setBounds(100,20,165,25);
		

		
		
	 loginButton.setBounds(20, 80, 80, 25);
	
		
		add(userLabel);
		add(userText);
		add(loginButton);
		add(panel);
		
		
		
	}
	 public static void main(String[] args) {
	       UploadGUI upload = new UploadGUI();
	       upload.uploadsetup();
	     
	       
	       upload.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       upload.setVisible(true);
	       
	    }
}