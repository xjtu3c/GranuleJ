
granule Count_SC_FAA(Count) {
	external String location;
	external int inputSet;
	{
		//inputSet==0 means the input number<=12
		return (location.equals("c"))&& (inputSet==0);
	}	
}

class Count within Count_SC_FAA{
	//递归int运算fact
	public void countResult(int a) {
		System.out.println("Caculate fact in Int scope");
		int res = doFactorial(a);
		setResult(Long.valueOf(res));
	}	
	
	public int doFactorial(int n){
		   if(n<0){
			   return -1;
		   }
		   if(n==0){
			   return  1;
		   }
		   return n*doFactorial(n-1);
		}
}
