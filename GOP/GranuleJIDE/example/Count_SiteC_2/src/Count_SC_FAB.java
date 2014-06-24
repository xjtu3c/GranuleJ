granule Count_SC_FAB(Count) {
	external String location;
	external int inputSet;
	{
		//inputSet==1 means the number a>12
		return location.equals("c")&& inputSet==1;
	}	
}

class Count within Count_SC_FAB{
	//非递归long运算fact
	public void countResult(int a) {
	   System.out.println("Caculate fact in Long scope");
	   doFactorial(a);
	}	
	
	  public void doFactorial(int n){
		  long res=1l; 
		  if(n<0){
			res = -1l;
		    }
	       if(n==0){
	    	res = 1l; 
	    	} 
	       for(int i=1;i<=n;i++){ 
	    	   res *= i; 
	    	  } 
	       setResult(res); 
	      }
}
