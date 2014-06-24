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


public class JGFMethodSequenceBench implements JGFSection1{


  private static final int INITSIZE = 10000;
  private static final int MAXSIZE = 10000000;
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
    JGFInstrumentor.addTimer("Section1:Method:Same:Instance", "calls");      time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Instance"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Instance");       
      for (i=0; i<size; i++){
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();
	instance_method();

      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:Instance");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance");
 

    // Test non empty instance method (increments a variable)
    JGFInstrumentor.addTimer("Section1:Method:Same:Instance2", "calls");     
    time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Instance2"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Instance2");       
      for (i=0; i<size; i++){
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();
	instance_method2();

      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:Instance2");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance2");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance2", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance2");

    // Test non empty instance method (increments a variable)
    JGFInstrumentor.addTimer("Section1:Method:Same:Instance3", "calls");     
    time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Instance3"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Instance3");       
      for (i=0; i<size; i++){
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();
	instance_method3();

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
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Instance4"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Instance4");       
      for (i=0; i<size; i++){
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();
	instance_method4();

      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:Instance4");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance4");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance4", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance4");
  
    // Test non empty instance method (increments a variable)
    JGFInstrumentor.addTimer("Section1:Method:Same:Instance5", "calls");     
    time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Instance5"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Instance5");       
      for (i=0; i<size; i++){
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();
	instance_method5();

      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:Instance5");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance5");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance5", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance5");  
    

    
//Test non empty instance method (increments a variable)
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance6", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance6"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance6");       
    for (i=0; i<size; i++){
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();
	instance_method6();

    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance6");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance6");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance6", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance6");  
  
  
  
//Test non empty instance method (increments a variable)
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance7", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance7"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance7");       
    for (i=0; i<size; i++){
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();
	instance_method7();

    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance7");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance7");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance7", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance7");  
  
//Test non empty instance method (increments a variable)
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance8", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance8"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance8");       
    for (i=0; i<size; i++){
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
	instance_method8();
    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance8");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance8");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance8", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance8");  
  
//Test non empty instance method (increments a variable)
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance9", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance9"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance9");       
    for (i=0; i<size; i++){
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();
	instance_method9();

    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance9");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance9");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance9", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance9");  
  
//Test non empty instance method (increments a variable)
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance10", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance10"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance10");       
    for (i=0; i<size; i++){
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();
	instance_method10();

    }
    JGFInstrumentor.stopTimer("Section1:Method:Same:Instance10");      
    time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance10");
    JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance10", (double) 16*size);
    size *=2; 
  }    
  JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance10");  
  
//Test non empty instance method (increments a variable)
  JGFInstrumentor.addTimer("Section1:Method:Same:Instance11", "calls");     
  time = 0.0; 
  size = INITSIZE; 
  while (time < TARGETTIME && size < MAXSIZE){
    JGFInstrumentor.resetTimer("Section1:Method:Same:Instance11"); 
    JGFInstrumentor.startTimer("Section1:Method:Same:Instance11");       
    for (i=0; i<size; i++){
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();
	instance_method11();

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

    JGFMethodSequenceBench mb = new JGFMethodSequenceBench(); 
    mb.JGFrun();
    
  }

}










