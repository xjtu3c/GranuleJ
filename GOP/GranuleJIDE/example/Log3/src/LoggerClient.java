external int hasDisk;
external double safetyIndex;
external double overload;
external double useRatio;

public class LoggerClient {
	public static void main(String[] args) {
		Logger logger = new LoggerImpl(new SysLogGenerator(),
				new LocalAppender());
		LoggerLevel level = new LoggerLevel("debug");
		logger.log(level, null); 
	}
}
