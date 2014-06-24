
granule B1I extends B1L(Site){
	external int InputSet;
	{
		return InputSet==0;
	}
	class Site{
       private int res;
       //int范围内的递归fib运算
       public void work() {
    	   res = 0;
    	   int n = getNum();
  	       int f1 =1;
  	       int f2 =2;
           if (n==1||n==2) {
        	    res = n;
            } 
           for (int i=3;i<=n;i++){   
        	   res=f1+f2;
               f1=f2;
               f2=res;
            }
       }
       
       public void show() {
    	   System.out.println("Digital:" + res);
       }
    }
}
