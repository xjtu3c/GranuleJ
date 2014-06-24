public class LoggerLevel{
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