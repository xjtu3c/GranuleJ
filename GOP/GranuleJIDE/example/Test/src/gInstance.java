//benchmark for  granule instance method invocation that is invoked by granule method in the same granule.
granule gInstance(JGFMethodBench){
	{ 
		return false;
	}
		
}

public class JGFMethodBench within gInstance{
          	public void instance_method(){
		Math.exp(k);
	}

          public synchronized void synch_instance_method(){
		Math.exp(k);
	}

}	
