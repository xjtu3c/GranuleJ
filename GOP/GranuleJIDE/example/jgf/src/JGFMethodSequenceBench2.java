/**************************************************************************
*                                                                         *
*             Java Grande Forum Benchmark Suite - Version 2.0             *
*                                                                         *
*                            produced by                                  *
*                                                                         *
*                  Java Grande Benchmarking Project                       *
*                                                                         *
*                                at                                       *
*                                                                         *
*                Edinburgh Parallel Computing Centre                      *
*                                                                         * 
*                email: epcc-javagrande@epcc.ed.ac.uk                     *
*                                                                         *
* Original version of parts of this code by DHPC Group, Univ. of Adelaide *
*                    See copyright notice below.                          *
*                                                                         *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/
/*
 *  Copyright (C) 1998, University of Adelaide, under its participation
 *  in the Advanced Computational Systems Cooperative Research Centre
 *  Agreement.
 *
 *  THIS SOFTWARE IS MADE AVAILABLE, AS IS, AND THE UNIVERSITY
 *  OF ADELAIDE DOES NOT MAKE ANY WARRANTY ABOUT THE SOFTWARE, ITS
 *  PERFORMANCE, ITS MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR
 *  USE, FREEDOM FROM ANY COMPUTER DISEASES OR ITS CONFORMITY TO ANY
 *  SPECIFICATION. THE ENTIRE RISK AS TO QUALITY AND PERFORMANCE OF
 *  THE SOFTWARE IS WITH THE USER.
 *
 *  Copyright of the software and supporting documentation is owned by the
 *  University of Adelaide, and free access is hereby granted as a license
 *  to use this software, copy this software and prepare derivative works
 *  based upon this software.  However, any distribution of this software
 *  source code or supporting documentation or derivative works (source
 *  code and supporting documentation) must include this copyright notice
 *  and acknowledge the University of Adelaide.
 *
 *
 *  Developed by: Distributed High Performance Computing (DHPC) Group
 *                Department of Computer Science
 *                The University of Adelaide
 *                South Australia 5005
 *                Tel +61 8 8303 4519, Fax +61 8 8303 4366
 *                http://dhpc.adelaide.edu.au
 *  Last Modified: 26 January 1999
 *
 *
 */

import jgfutil.*; 



public class JGFMethodSequenceBench2 implements JGFSection1{


	  private static final int INITSIZE = 10000;
	  private static final int MAXSIZE = 1000000000;
	  private static final double TARGETTIME = 10.0; 
	  
  
  static int  k = 0; 
  private int k0 = 0;
  private int k1 = 0,  k2 = 0,
  k3 =  0, k4 = 0, k5 = 0, k6 = 0, k7 = 0, k8 = 0,  k9 = 0, k10 = 0,
  k11 =0 , k12 = 0, k13 = 0, k14 = 0;
  public void JGFrun(){

    int i,size;
    double time; 

  
    // Test non empty instance method (increments a variable)
    JGFInstrumentor.addTimer("Section1:Method:Other:Instance", "calls");     
    time = 0.0; 
    size = INITSIZE; 
    MethodSeq_01 a1 = new MethodSeq_01();
//    MethodTester a1 = new MethodTester(); 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Other:Instance"); 
      JGFInstrumentor.startTimer("Section1:Method:Other:Instance");       
      for (i=0; i<size; i++){
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
	a1.instance_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Other:Instance");      
      time = JGFInstrumentor.readTimer("Section1:Method:Other:Instance");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Other:Instance", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Other:Instance");
    
    
    // Test non empty instance method (increments a variable)
    JGFInstrumentor.addTimer("Section1:Method:Same:Instance2", "calls");     
    MethodSeq_02 a2 = new MethodSeq_02();
    time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Instance2"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Instance2");       
      for (i=0; i<size; i++){
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
    	  a2.instance_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:Instance2");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance2");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance2", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance2");

    // Test non empty instance method (increments a variable)
    JGFInstrumentor.addTimer("Section1:Method:Same:Instance3", "calls");     
    
    MethodSeq_03 a3 = new MethodSeq_03();
    time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Instance3"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Instance3");       
      for (i=0; i<size; i++){
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
    	  a3.instance_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:Instance3");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance3");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance3", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance3");


   
    // Test non empty instance method (increments a variable)
    JGFInstrumentor.addTimer("Section1:Method:Same:Instance4", "calls");     
    time = 0.0; 
    size = INITSIZE; 
    MethodSeq_04 a4 = new MethodSeq_04();
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Instance4"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Instance4");       
      for (i=0; i<size; i++){
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	a4.instance_method();
	
      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:Instance4");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance4");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance4", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance4");
  
    // Test non empty instance method (increments a variable)
    MethodSeq_05 a5 = new MethodSeq_05();
    JGFInstrumentor.addTimer("Section1:Method:Same:Instance5", "calls");     
    time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Instance5"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Instance5");       
      for (i=0; i<size; i++){
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	a5.instance_method();
	


      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:Instance5");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance5");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance5", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance5");  
    

    
//Test non empty instance method (increments a variable)
    MethodSeq_06 a6 = new MethodSeq_06();
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance6", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance6"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance6");       
    for (i=0; i<size; i++){
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	a6.instance_method();
	
    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance6");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance6");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance6", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance6");  
  
  
  MethodSeq_07 a7 = new MethodSeq_07();
//Test non empty instance method (increments a variable)
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance7", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance7"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance7");       
    for (i=0; i<size; i++){
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
	a7.instance_method();
		
    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance7");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance7");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance7", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance7");  
  
  MethodSeq_08 a8 = new MethodSeq_08();
//Test non empty instance method (increments a variable)
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance8", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance8"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance8");       
    for (i=0; i<size; i++){
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();
	a8.instance_method();

	
    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance8");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance8");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance8", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance8");  
  
//Test non empty instance method (increments a variable)
  MethodSeq_09 a9 = new MethodSeq_09();
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance9", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance9"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance9");       
    for (i=0; i<size; i++){
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();
	a9.instance_method();

    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance9");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance9");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance9", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance9");  
  
  MethodSeq_10 a10 = new MethodSeq_10();
//Test non empty instance method (increments a variable)
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance10", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance10"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance10");       
    for (i=0; i<size; i++){
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();
	a10.instance_method();

    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance10");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance10");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance10", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance10");  
  
  MethodSeq_11 a11 = new MethodSeq_11();
//Test non empty instance method (increments a variable)
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance11", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance11"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance11");       
    for (i=0; i<size; i++){
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	a11.instance_method();
	
    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance11");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance11");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance11", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance11"); 
}

  public void instance_method(){k0++;}
  
  public void instance_method2(){
	 k0++; k1++;
  }
  
  public void instance_method3(){
	  k0++; k1++; k2++;
  }
  
  public void instance_method4(){
	   k0++; k1++; k2++; k3++;
 }
  
  public void instance_method5(){
	  k0++; k1++; k2++; k3++;k4++; 
}

  public void instance_method6(){
	  k0++; k1++; k2++; k3++;k4++; 
	  k5++; 
}

  public void instance_method7(){
	  k0++; k1++; k2++; k3++;k4++; 
	  k5++; k6++; 
}

  public void instance_method8(){
	  k0++; k1++; k2++; k3++;k4++; 
	  k5++; k6++; k7++; 
}

  public void instance_method9(){
	  k0++; k1++; k2++; k3++;k4++; 
	  k5++; k6++; k7++; k8++;
}

  public void instance_method10(){
	  k0++; k1++; k2++; k3++;k4++; 
	  k5++; k6++; k7++; k8++;k9++; 
	  
}

  public void instance_method11(){
	  k0++; k1++; k2++; k3++;k4++; 
	  k5++; k6++; k7++; k8++;k9++; 
	  k10++; 
}

  public static void main (String argv[]){

    JGFInstrumentor.printHeader(1,0);

    JGFMethodSequenceBench2 mb = new JGFMethodSequenceBench2(); 
    mb.JGFrun();
    
  }

}



class MethodSeq_01{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){k++;}
	  
	  public static void class_method() {k++;}
}

class MethodSeq_02{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){(new MethodSeq_01()).instance_method();};
	  
	  public static void class_method() {k++;}
}
class MethodSeq_03{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){(new MethodSeq_02()).instance_method();};
	  
	  public static void class_method() {k++;}
}
class MethodSeq_04{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){(new MethodSeq_03()).instance_method();};
	  
	  public static void class_method() {k++;}
}


class MethodSeq_05{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){(new MethodSeq_04()).instance_method();};
	  
	  public static void class_method() {k++;}
}

class MethodSeq_06{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){(new MethodSeq_05()).instance_method();};
	  
	  public static void class_method() {k++;}
}
class MethodSeq_07{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){(new MethodSeq_06()).instance_method();};
	  
	  public static void class_method() {k++;}
}
class MethodSeq_08{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){(new MethodSeq_07()).instance_method();};
	  
	  public static void class_method() {k++;}
}

class MethodSeq_09{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){(new MethodSeq_08()).instance_method();};
	  
	  public static void class_method() {k++;}
}
class MethodSeq_10{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){(new MethodSeq_09()).instance_method();};
	  
	  public static void class_method() {k++;}
}
class MethodSeq_11{
	  static int k = 0; 
	  
	  static int k1=0;
	  
	  private int k0 = 0;
	  public void instance_method(){(new MethodSeq_10()).instance_method();};
	  
	  public static void class_method() {k++;}
}







