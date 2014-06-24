
granule B2I expands B2L(Site){
	external int InputSet;
	{
		return InputSet==1;
	}
	class Site{
      private long res;
      public void work() {
   	   res = 0l;
   	   int n = getNum();
 	       long f1 =1l;
 	       long f2 =2l;
          if (n==1||n==2) {
       	     res = n;
           } 
          for (int i=3;i<=n;i++){   
       	      res=f1+f2;
              f1=f2;
              f2=res;
           }
         System.out.println("Result is :"+res);
      }
      public void show() {
    	 System.out.println("Digital:" + res);
      }

    }
}
