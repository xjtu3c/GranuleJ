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
