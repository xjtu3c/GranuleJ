//benchmark for  granule instance method invocation that is invoked by granule method in the same granule.
granule gInstance2(JGFMethodBench){
	{ 
		return true;
	}
		
}

public class JGFMethodBench within gInstance2{
          	public void instance_method(){
		Math.exp(k);
	}

          public synchronized void synch_instance_method(){
		Math.exp(k);
	}

}	
