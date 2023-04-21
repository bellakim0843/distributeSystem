package grpc.examples.realtimeChat;

import java.io.File;

import javax.swing.JFileChooser;

//import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

public class fileChooser {
	
	public static void main(String[] args) {
		
		
		fileChooser file = new fileChooser();
		file.uploadFile();
		
	}
	public void uploadFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println(selectedFile.getAbsolutePath());	
		}
		
	}
	
	


}
