
granule Count_SB_FIA(Count) {
	external String location;
	external int inputSet;
	{
		//inputSet==1 mean count num a >45
		return location.equals("b")&& inputSet==1;
	}	
}

class Count within Count_SB_FIA{

	public void countResult(int a) {
		System.out.println("Caculate fib in Long scope");
		fib(a);
	}	
	
	public void fib(int n){  
	     long res;
	     long f1 =1l;
	     long f2 =2l;
	     long f3 =0l;
        if (n==1||n==2) {
      	    f3 = n;
          } 
        for (int i=3;i<=n;i++){   
      	   f3=f1+f2;
           f1=f2;f2=f3;
          }
       res = f3;
       setResult(Long.valueOf(res));
 }
}
