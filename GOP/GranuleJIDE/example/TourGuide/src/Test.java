external String Loc;
external int InputSet;

public class Test{
	public static boolean connect(){
		return true;
		}
	public static void default_behavior(){
		System.out.println("Default work and default show.");
		}
	public static void main(String[] args){
	    Site s = new Site();  
	    //while(true) {
	        if (connect()){
	          //check-fitness();
	          s.getdata(); 
	          s.work();
	          s.show();
	        }else{ 
	        	default_behavior();
	        }
	   //}
	}
}

