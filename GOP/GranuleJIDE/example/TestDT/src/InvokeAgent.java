
import java.lang.reflect.Array;
import java.util.ArrayList;

public class InvokeAgent {
	public static <IN,OUT> OUT invokeAgent(IN obj,String methodSignature,Object ...args)
	{
		System.out.println("invokeAgent");
		if(doFitness())
			return invokeMethod();
		else
			return replaceMethod();
	}
	
	public static <IN,OUT> boolean doFitness()
	{
		System.out.println("doFitness");
		return true;
	}
	
	public static <IN,OUT> OUT invokeMethod()
	{
		System.out.println("invokeMethod");
		return (OUT)new Object();
	}
	
	public static <IN,OUT> OUT replaceMethod()
	{
		System.out.println("replaceMethod");
		return (OUT)new Object();
	}
	
	public static void init()
	{
		System.out.println("init!");
	}
	
	public static void main(String args[])
	{
		ArrayList array = new ArrayList();
		array.add("3");
		//Array.get(index);
		//InvokeAgent.invokeAgent(array,"get#Integer",3,"test");
	}
}
