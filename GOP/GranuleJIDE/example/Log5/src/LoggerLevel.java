//日志的生成级别  ：运行？ 调试？ 
public class LoggerLevel {
    private String levelName;
    public LoggerLevel(String name){
        levelName = name;
    }
    public void setName(String name){
        levelName = name;
    }
    public String getName(){
        return levelName;
    }
}