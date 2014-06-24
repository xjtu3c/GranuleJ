public class LocalAppender extends AbstractAppender{
	private String name;
	private WriteUtil output;
	public LocalAppender(){
		output = new WriteUtil();
	}
	public LocalAppender(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void log(String msg) {
		System.out.println(msg);
	}
}
