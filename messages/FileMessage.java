package message;

import java.io.File;

public class FileMessage extends TextMessage{

	private File file;

	public FileMessage(String recipient,String sender,String msg,String fname){
		super(recipient, sender, msg);
		this.file = new File(filename);
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
}
