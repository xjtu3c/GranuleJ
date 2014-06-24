import java.math.BigInteger;

granule C1L(Site){
	external String Loc;
	{
		return Loc.equals("Site C");
	}
  class Site{
     private BigInteger res;
	  
     public void work() {
	   BigInteger res= BigInteger.ONE;
	   int n = getNum();
       for(int c=n; c>0; c--)
           res=res.multiply(BigInteger.valueOf(c));
       }

     public void show(){
	    System.out.println("Analogy:"+res.intValue());
     }
  }
}


