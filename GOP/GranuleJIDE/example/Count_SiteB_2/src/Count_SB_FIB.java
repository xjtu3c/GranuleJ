
granule Count_SB_FIB(Count) {
	external String location;
	external int inputSet;
	{
		//inputSet==0 means the num a <=45
		return location.equals("b")&& (inputSet==0);
	}	
}

class Count within Count_SB_FIB{
	//递归int运算fib
	public void countResult(int a) {
		System.out.println("Caculate fib in Int scope");
		int res = fib(a);
		setResult(Long.valueOf(res));
	}
	

	public int fib(int n){  
	     int f1 =1;
	     int f2 =2;
	     int f3 =0;
       if (n==1||n==2) {
     	 return n;
         } 
       return fib(n-1)+fib(n-2);
       }
}
