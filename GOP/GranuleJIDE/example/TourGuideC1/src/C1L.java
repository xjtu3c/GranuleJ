import java.math.BigInteger;

granule C1L(Site){
	external String Loc;
	{
	  return Loc.equals("Site C");
	}
  class Site{
     private BigInteger res;
	  
     public void work() {
	   res= BigInteger.ONE;
	   int n = getNum();
              for(int c=n; c>0; c--)
               res=res.multiply(BigInteger.valueOf(c));
               System.out.println("This is in C1L working ! The result is :"+res.toString());           
             }
            public void show(){            
            System.out.println("Analogy:"+res.toString());
           }
  }
}


