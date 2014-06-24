public class LoggerImpl implements Logger {
	private LoggerLevel level;
	private Generator gen;
	private Appender append;

	public LoggerImpl() {
		gen = new UsrLogGenerator();
		append = new LocalAppender();
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
		if(item != null)
			append.log(item.toString());
		else  {
                           LogItem temp = gen.generate();
                           append.log(temp.toString());
                            //append.log(temp);
                           //gen.generate();
                          }
	}
}
