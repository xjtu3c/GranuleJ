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


  //protected static final int INITSIZE = 10000;
  protected static final int INITSIZE=100;
  //protected static final int MAXSIZE = 1000000000;
  protected static final int MAXSIZE=1000;
  protected static final double TARGETTIME = 10.0; 
  
  static int  k = 10; 
  private int k0 = 0;
  
  private int k1 = 0,  k2 = 0,
  k3 =  0, k4 = 0, k5 = 0, k6 = 0, k7 = 0, k8 = 0,  k9 = 0, k10 = 0,
  k11 =0 , k12 = 0, k13 = 0, k14 = 0;  
  public void JGFrun(){

    int i,size;
    double time; 

    JGFInstrumentor.addTimer("Section1:Method:Same:Instance", "calls");     
    time = 0.0; 
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
   
    A mt = new A(); 

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
    

    
//    MethodTester2 mt2 = new MethodTester2(); 

    JGFInstrumentor.addTimer("Section1:Method:Other:SynchronizedInstance", "calls");     
    time = 0.0; 
    size = INITSIZE; 

    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Other:SynchronizedInstance"); 
      JGFInstrumentor.startTimer("Section1:Method:Other:SynchronizedInstance");       
      for (i=0; i<size; i++){
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();	
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
	mt.synch_instance_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Other:SynchronizedInstance");      
      time = JGFInstrumentor.readTimer("Section1:Method:Other:SynchronizedInstance");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Other:SynchronizedInstance", (double) 16*size);
      size *=2; 
    	}
    
	JGFInstrumentor.printperfTimer("Section1:Method:Other:SynchronizedInstance");


  	}    
 


  	public void instance_method(){
		Math.exp(k);
	}
  
  	public synchronized void synch_instance_method(){
		Math.exp(k);
	}

 	public static void main (String argv[]){

		JGFInstrumentor.printHeader(1,0);

   		JGFMethodBench mb = new JGFMethodBench(); 
    		mb.JGFrun();
    
	}

}











