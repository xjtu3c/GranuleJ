public class LogItem {

	private String msg;
	private String file;
	private String event;
	private String method;
	private int line;

	public LogItem() {
	}

	public LogItem(String msg, String file, String event, String method,
			int line) {
		this.msg = msg;
		this.file = file;
		this.event = event;
		this.method = method;
		this.line = line;
	}

	public String toString() {
		return "msg:" + msg + " - from file " + file + " with event " + event
				+ " and method " + method + " in line " + line;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}
}
