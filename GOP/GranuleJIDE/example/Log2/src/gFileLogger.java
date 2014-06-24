import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

granule gFileLogger(LocalAppender){
	external int hasDisk;
	{
		if(hasDisk==1)
			return true;
		else 
			return false;
	}
}
class LocalAppender within gFileLogger {
	private WriteUtil out;
	public void log(String msg) {
		out = new WriteUtil();
		out.write(msg);
		seed.log(msg);
	}
}
