external String location;
external int inputSet;

public class CounterFacade {
	public static void main(String[]args){
	  Count count = new Count();
	  int a = count.getInputData();
	  count.countResult(a);
   	  count.disPlayResult();
	}
}
