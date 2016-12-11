// wenju mu

// nb: when sending a file, put it in the files folder and
//     set fname as the name of the file (no directory structure needed)

import java.io.File;

public class FileMessage extends TextMessage{

	private String fname;
	private File file;

	public FileMessage(String recipient,String sender,String msg,String fname){
		super(recipient, sender, msg);
		this.fname = fname; // used in rw for regenerating FileMessage objects
		String pathname = "files/" + fname; // build the path to the files folder
		this.file = new File(pathname);
	}

	public void setFile(String filename){	// set a new file		
		this.file = new File(filename);
	}

	public void setFname(String fname) // set a file name 
	{
		this.fname = fname;
		String pathname = "files/" + fname;
		this.setFile(pathname);
	}

	public File getFile(){
		return this.file;
	}

	public String getFname()
	{
		return this.fname;
	}

	public void sendMessage(){
		// to implement:
		// -> call write_file_message() in ReadWriteMessage
	}

	public static void main(String[] args)
	{
		FileMessage fm = new FileMessage("u1","u2","files","Thunder_Gun.exe");//build a file example 
		boolean b = fm.getFile().canExecute();
		System.out.println(b);
	}
}
