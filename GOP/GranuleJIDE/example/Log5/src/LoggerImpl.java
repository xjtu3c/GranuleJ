public class LoggerImpl implements Logger {
	private LoggerLevel level;
	private Generator gen;
	private Appender append;

	public LoggerImpl() {
		gen = new SysLogGenerator();
		append = new  RemoteAppender();
	}

	public LoggerImpl(Generator gen, Appender append) {
		this.gen = gen;
		this.append = append;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public Generator getGen() {
		return gen;
	}

	public void setGen(Generator gen) {
		this.gen = gen;
	}

	public Appender getAppend() {
		return append;
	}

	public void setAppend(Appender append) {
		this.append = append;
	}

	public void log(LoggerLevel level, LogItem item) {
		if(item != null){
			System.out.println("item.toString()........"+item.toString());
			append.log(item.toString());
		
		}
			
		else{
			// System.out.println("gen.generate().toString()......"+gen.generate().toString());
			append.log(gen.generate().toString());
		//	LogItem temp = gen.generate();			
	//	append.log(temp.toString());
		}
			
	}
}
