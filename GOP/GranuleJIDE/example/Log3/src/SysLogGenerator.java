public class SysLogGenerator extends AbstractGenerator {
	private String eventId;
	private String eventType;
	private String eventTime;
	private String eventSource;
 	private String msg;
	private int line;

	public SysLogGenerator(){
		produceLog();
	}

          public native String readSystemLog();
          static {
                    System.loadLibrary("../lib/readSystemLogDll");
          }

	public void produceLog(){
                    msg=readSystemLog();
                    String [] logInfos = msg.split("##");
                    eventId = logInfos[0];
                    eventType = logInfos[1];
                    eventTime = logInfos[2];
                    eventSource = logInfos[3];
	
	}

	public LogItem generate() {
		super.checkStatus();
		return new LogItem(eventId, eventType, eventTime, eventSource);
	}

	public String getMsg() {
          		return msg;
          }

	public void setMsg(String message) {
                    msg=message;
                    String [] logInfos = msg.split("##");
                    eventId = logInfos[0];
                    eventType = logInfos[1];
                    eventTime = logInfos[2];
                    eventSource = logInfos[3];
	}

}
