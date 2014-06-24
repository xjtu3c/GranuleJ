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

public class JGFMethodBench implements JGFSection1{


  public static final int INITSIZE = 10000;
  //public static final int INITSIZE=10;
  public static final int MAXSIZE = 100000000;
  //public static final int MAXSIZE=100;
  public static final double TARGETTIME = 10.0; 
  
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

    
    // Non-empty synchronised instance method
    JGFInstrumentor.addTimer("Section1:Method:Same:SynchronizedInstance", "calls");     
    time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:SynchronizedInstance"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:SynchronizedInstance");  
      for (i=0; i<size; i++){
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
	synch_instance_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:SynchronizedInstance");          time = JGFInstrumentor.readTimer("Section1:Method:Same:SynchronizedInstance");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:SynchronizedInstance", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:SynchronizedInstance");

/*
    // Test non empty final instance method
    JGFInstrumentor.addTimer("Section1:Method:Same:FinalInstance", "calls");  
    time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:FinalInstance"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:FinalInstance");       
      for (i=0; i<size; i++){
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
	final_instance_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:FinalInstance");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:FinalInstance");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:FinalInstance", (double) 16*size);
      size *=2; 
    }
    
    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:FinalInstance");

 */
    
    // Test non empty static method
    JGFInstrumentor.addTimer("Section1:Method:Same:Class", "calls");  
    time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Class"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Class");       
      for (i=0; i<size; i++){
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
	class_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:Class");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:Class");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Class", (double) 16*size);
      size *=2; 
    }
    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:Class");



    // Test empty synchronized class method
    JGFInstrumentor.addTimer("Section1:Method:Same:SynchronizedClass", "calls");     
    time = 0.0; 
    size = INITSIZE; 

    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:SynchronizedClass"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:SynchronizedClass");       
      for (i=0; i<size; i++){
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
	synch_class_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:SynchronizedClass");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:SynchronizedClass");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:SynchronizedClass", (double) 16*size);
      size *=2; 
    }
    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:SynchronizedClass");

   
    MethodTester mt = new MethodTester(); 

    JGFInstrumentor.addTimer("Section1:Method:Other:Instance", "calls");     
    time = 0.0; 
    size = INITSIZE; 

    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Other:Instance"); 
      JGFInstrumentor.startTimer("Section1:Method:Other:Instance");       
      for (i=0; i<size; i++){
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
	mt.instance_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Other:Instance");      
      time = JGFInstrumentor.readTimer("Section1:Method:Other:Instance");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Other:Instance", (double) 16*size);
      size *=2; 
    }
    
    JGFInstrumentor.printperfTimer("Section1:Method:Other:Instance");
    

    
    MethodTester2 mt2 = new MethodTester2(); 

    JGFInstrumentor.addTimer("Section1:Method:Other:InstanceOfAbstract", "calls");     
    time = 0.0; 
    size = INITSIZE; 

    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Other:InstanceOfAbstract"); 
      JGFInstrumentor.startTimer("Section1:Method:Other:InstanceOfAbstract");       
      for (i=0; i<size; i++){
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
	mt2.instance_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Other:InstanceOfAbstract");      
      time = JGFInstrumentor.readTimer("Section1:Method:Other:InstanceOfAbstract");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Other:InstanceOfAbstract", (double) 16*size);
      size *=2; 
    }
    
    JGFInstrumentor.printperfTimer("Section1:Method:Other:InstanceOfAbstract");


    JGFInstrumentor.addTimer("Section1:Method:Other:Class", "calls");     
    time = 0.0; 
    size = INITSIZE; 

    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Other:Class"); 
      JGFInstrumentor.startTimer("Section1:Method:Other:Class");       
      for (i=0; i<size; i++){
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
	MethodTester.class_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Other:Class");      
      time = JGFInstrumentor.readTimer("Section1:Method:Other:Class");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Other:Class", (double) 16*size);
      size *=2; 
    }
    
    JGFInstrumentor.printperfTimer("Section1:Method:Other:Class");

    

  }    
 

  public static boolean fitness(){return true;} 
  public void instance_method(){k++;}



  synchronized public void synch_instance_method(){fitness();k++; fitness(); 
 }

  public void final_instance_method(){fitness();k++;fitness();}

  public static void class_method() {fitness();k++; fitness();}

  synchronized public static void synch_class_method() {fitness();k++;  fitness();}


  public static void main (String argv[]){

    JGFInstrumentor.printHeader(1,0);

    JGFMethodBench mb = new JGFMethodBench(); 
    mb.JGFrun();
    
}
}
class MethodTester {

  static int k = 0; 
  
  static int k1=0;
  
  private int k0 = 0;
  public void instance_method(){k++;}

  public static void class_method() {k++;}
    
}

abstract class AbstractMethodTester{

   static int k = 0; 
   public abstract void instance_method();
} 

class MethodTester2 extends AbstractMethodTester{

  public void instance_method(){k++;}

}


granule g(JGFMethodBench){
		{ return false;}
		
        class JGFMethodBench{
            private int d_d=0;
            public void instance_method(){k++;}
           /* public static void class_method() { k++;} 
            public final void final_instance_method(){k++;}
            synchronized public static void synch_class_method() {k++;}
            synchronized public void synch_instance_method(){k++;}*/
          }			
}





