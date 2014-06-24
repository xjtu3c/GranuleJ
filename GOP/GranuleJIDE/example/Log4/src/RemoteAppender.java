
public class RemoteAppender extends AbstractAppender{
	private String name;
	public RemoteAppender(){}
	public RemoteAppender(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void log(String msg) {
		System.out.println("log method in class RemoteAppender.");
	}
}
