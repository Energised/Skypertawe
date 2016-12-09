// wenju mu

// nb: when sending a file, put it in the files folder and
//     set fname as the name of the file (no directory structure needed)

import java.io.File;

public class FileMessage extends TextMessage{

	private File file;

	public FileMessage(String recipient,String sender,String msg,String fname){
		super(recipient, sender, msg);
		fname = "files/" + fname; // build the path to the files folder
		this.file = new File(fname);
	}

	public void set_file(String filename){
		this.file = new File(filename);
	}

	public File get_file(){
		return this.file;
	}

	public void sendMessage(){
		// to implement:
		// -> call write_file_message() in ReadWriteMessage
	}

	public static void main(String[] args)
	{
		FileMessage f = new FileMessage("u1","u2","files","Thunder_Gun.exe");
		boolean b = f.get_file().canExecute();
		System.out.println(b);
	}
}
