import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
//粒
//上下文：磁盘
granule gFileLogger(LocalAppender){
	external int hasDisk;
	{
		if(hasDisk==1)
			return true;
		else 
			return false;
	}
	

}
//影子类
//追加日志到本地文件
class LocalAppender within gFileLogger {
	private WriteUtil out;
	public void log(String msg) {
		out = new WriteUtil();
                     System.out.println("gFileLogger is writing the log .....");
		out.write(msg);
		seed.log(msg);
	}
}
