public class SysLogGenerator extends AbstractGenerator {
	private String msg;
	private String file;
	private String event;
	private String method;
	private int line;

	public SysLogGenerator(){
		produceLog();
	}

	public void produceLog(){
		msg = "A sys log";
		file = "module.c";
		event = "get device";
		method = "checkDevice";
		line = 2209;
	
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
