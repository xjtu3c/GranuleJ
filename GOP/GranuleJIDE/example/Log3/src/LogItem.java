public class LogItem {

	private String eventId;
	private String eventType;
	private String eventTime;
	private String eventSource;

	public LogItem() {
	}

	public LogItem(String eventId, String eventType, String eventTime, String eventSource) {
		this.eventId =eventId;
		this.eventType = eventType;
		this.eventTime = eventTime;
		this.eventSource =eventSource;
	}

	public String toString() {
		return "ID: " + eventId + "   Type: " + eventType + "   Time: " + eventTime 
                            + "   Source: " + eventSource;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventSource() {
		return eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}
}
