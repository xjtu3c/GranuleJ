import java.math.BigInteger;
granule A2I expands A2L(Site){
	external int InputSet;
	{
		return InputSet==1;
	}
	class Site{
        private BigInteger res;
        public void work() {
           res= BigInteger.ONE;
           int n = getNum();
           for(int c=n; c>0; c--)
               res=res.multiply(BigInteger.valueOf(c));
         }
        
        public void show() {
        	System.out.println("Digital:" + res.toString());
        }
     }
}
