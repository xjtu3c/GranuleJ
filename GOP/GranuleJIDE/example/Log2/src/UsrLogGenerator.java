public class UsrLogGenerator extends AbstractGenerator {
	private String msg;
	private String file;
	private String event;
	private String method;
	private int line;

	public void produceLog(){
		msg = "A usr log";
		file = "app.c";
		event = "get account";
		method = "checkAccount";
		line = 667;
	
	}

	public LogItem generate() {
		super.checkStatus();
		produceLog();
		return new LogItem(msg, file, event, method,
				line);
	}

}
