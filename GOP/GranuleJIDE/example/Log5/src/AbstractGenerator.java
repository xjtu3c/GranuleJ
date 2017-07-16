/*
 * 抽象类，日志的生成
 * 主要提供了状态检查方法
 */
public abstract class AbstractGenerator implements Generator {
	private String name;
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public void checkStatus(){
     		System.out.println("check status...");
         }

	public abstract LogItem generate();
}
