
granule A1I expands A1L(Site){
	external int InputSet;
          {
	 return InputSet==0;
	}
	
  class Site{
    private long res;
       public void work() {
           res = 1;
           int n = getNum();
           if(n==0||n==1){
        	   res = 1;
           }
           if(n>1) {
            for(int i=n;i>0;i--) 
        	   res*=i;
           }
         System.out.println("A1I work :" + res);
       }
       
       public void show(){
       	 System.out.println("Digital:" + res);
       }
   }
}
