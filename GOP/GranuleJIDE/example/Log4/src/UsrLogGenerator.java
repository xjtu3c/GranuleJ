public class UsrLogGenerator extends AbstractGenerator {
	private String eventId;
	private String eventType;
	private String eventTime;
	private String eventSource;
	private int line;

	public void produceLog(){
	
	}

	public LogItem generate() {
		super.checkStatus();
		produceLog();
		return new LogItem(eventId, eventType, eventTime, eventSource);
	}

}
