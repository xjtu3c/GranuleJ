import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

//log
granule gConcoleLogger(LocalAppender){
	external int hasDisk;
	{
		if(hasDisk == 0)
			return true;
		else 
			return false;
	}
}

class LocalAppender within gConcoleLogger {
	
	public void log(String msg) {
		System.out.println("output the msg:" + msg + " in console");
		seed.log(msg);
	}
}
