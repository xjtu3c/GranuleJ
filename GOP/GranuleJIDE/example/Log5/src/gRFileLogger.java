import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
//粒
//上下文：磁盘
granule gRFileLogger(RemoteAppender){
	external int hasDisk;
	{
		if(hasDisk==0)
			return true;
		else 
			return false;
	}
	

}
//影子类
//追加日志到本地文件
class RemoteAppender within gRFileLogger {
	public void log(String msg) {
		System.out.println("now in the gRFileLogger,the msg is :"+msg);
		seed.log(msg);
	}
}