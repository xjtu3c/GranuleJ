external int n;
external String Appearance;
external String Efficiency;
external int Typesize;

 class GT3 {
	 public static void main(String[] args){
	       System.out.println("e is created");
	       E e1=new E();        
	       System.out.println("d is created");
	       D d=new D();
             R r=new R();
             d.display(5);
	       e1.display(5);
             r.display(5);
  }
}

class R {
   public void display(int i){
   	System.out.println("display in R is invoked ");
 }
}

class E extends R {
	  private  int m;
	  private float x;
	  public void display(int i){
	    	System.out.println("display in E is invoked ");
	  }
}

class D extends R {
    private double m;
    private R d;
    public void display(int i){
    	System.out.println("display in D is invoked ");
  }
}

granule Re0(R){
    external String Efficiency;
   {return Efficiency.equals("Trival");}
}

granule Et0(E){ 
    external int n;
    {return n==0;} 
}

granule Dt3(D){ 
    external int Typesize;
    {return Typesize == 0;}
}

class R within Re0 {
	   public void display(int i){
	    	System.out.println("display in R in Re0 is invoked ");
	  }
}
class E within Re0 {

}

class E within Et0 {
	   public void display(){
	   System.out.println("display in E in Et0 is invoked ");
	  }
}
class D within Dt3 {
             public void display(){
	System.out.println("display in D in Dt3 is invoked ");
	}
}