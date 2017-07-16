import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class WriteUtil{
    public void write(String msg){
	try {   
		File file = new File("log.txt");
		if(!file.exists())
			file.createNewFile();
		System.out.println("write msg 1");
	         FileWriter writer = new FileWriter(file);
	         writer.write(msg);
	         System.out.println("write msg 2");
	         writer.close(); 
        	} catch (IOException e) {   
            e.printStackTrace();   
        	}   
	System.out.println("write msg: " + msg + "in file");
    }
}
