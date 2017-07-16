public class LocalAppender extends AbstractAppender{
	private String name="";
	private WriteUtil output=null;
	public LocalAppender(){
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void log(String msg) {
		System.out.println("log method in LocalAppender");
	}
}
