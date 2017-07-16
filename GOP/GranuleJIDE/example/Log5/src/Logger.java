public interface Logger{
    public void setLevel(LoggerLevel level);
    public LoggerLevel getLevel();
    public void log(LoggerLevel level, LogItem item);
}