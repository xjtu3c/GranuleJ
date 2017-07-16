import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class WriteUtil{
    public void write(String msg){
	try {   
		File file = new File("./log.txt");
		if(!file.exists())
			file.createNewFile();
	         	FileWriter writer = new FileWriter(file);
		writer.write(msg);   
	         	writer.close(); 
        	} catch (IOException e) {   
            		e.printStackTrace();   
        	}   
	System.out.println("write " + msg + "in file");
    }
}
