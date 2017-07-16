/*
 * 系统日志生成器
 */
public class FileLogGenerator extends AbstractGenerator {
	private String msg;
	private String file;
	private String event;
	private String method;
	private int line;

	public FileLogGenerator(){
		produceLog();
	}

	public void produceLog(){
		msg = "A file log";
		file = "module.c";
		event = "get file";
		method = "checkFile";
		line = 1109;
	
	}

	public String getMsg(){
		return msg;

	}

	public void setMsg(String msg){
		this.msg = msg;
	}

	public LogItem generate() {
		super.checkStatus();
		return new LogItem(msg, file, event, method,
				line);
	}

}
