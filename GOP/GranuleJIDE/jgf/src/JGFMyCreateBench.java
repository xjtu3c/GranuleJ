import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jgfutil.*; 
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
*    Original version of this code by DHPC Group, Univ. of Adelaide       *
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
 */


import jgfutil.*; 

public class JGFMyCreateBench implements JGFSection1 {


  private static final int INITSIZE = 10000;
  private static  int MAXSIZE = 1000;
  private static final double TARGETTIME = 10.0; 
  
  JGFMyCreateBench(int  MAXSIZE){
	  this.MAXSIZE = MAXSIZE;
  }
  
  public void JGFrun(){

    int i,j,size;
    double time; 
    this.MAXSIZE = MAXSIZE;
    
    JGFInstrumentor.addTimer("Section1:Create:Object:Simple", "objects");
    JGFInstrumentor.addTimer("Section1:Create:Object:Simple2", "objects");
    
    j = 0;
    time = 0.0; 
    size = INITSIZE; 
    myObject mo =new myObject();
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Create:Object:Simple"); 
      JGFInstrumentor.startTimer("Section1:Create:Object:Simple"); 
      for (i=0;i<size;i++) {
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();  	  
    	  
      }
      JGFInstrumentor.stopTimer("Section1:Create:Object:Simple"); 
      //time = JGFInstrumentor.readTimer("Section1:Create:Object:Simple"); 
      JGFInstrumentor.addOpsToTimer("Section1:Create:Object:Simple", (double) 16*size);  
      size *=2; 
    }
    JGFInstrumentor.printperfTimer("Section1:Create:Object:Simple"); 
  
    System.gc(); // Reclaim memory
    /*
    System.out.println("1");
    (new GopContext()).addContext("g3%myObject");
    System.out.println("2");

     time = 0.0; 
     size = INITSIZE; 
      while (time < TARGETTIME && size < MAXSIZE){  
      JGFInstrumentor.resetTimer("Section1:Create:Object:Simple2"); 
      JGFInstrumentor.startTimer("Section1:Create:Object:Simple2"); 
      for (i=0;i<size;i++) {
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();
    	  mo = new myObject(); mo = new myObject();  	  
    	  
      }
      JGFInstrumentor.stopTimer("Section1:Create:Object:Simple2"); 
     // time = JGFInstrumentor.readTimer("Section1:Create:Object:Simple2"); 
      JGFInstrumentor.addOpsToTimer("Section1:Create:Object:Simple2", (double) 16*size);      
      
      size *=2; 
    }

    JGFInstrumentor.printperfTimer("Section1:Create:Object:Simple2"); 
    System.gc(); // Reclaim memory
*/
    
  }
  
public static void main (String argv[]){

    JGFInstrumentor.printHeader(1,0);
    int MAXSIZE = 100000;
    JGFMyCreateBench crb1 = new JGFMyCreateBench(5*MAXSIZE); 
    crb1.JGFrun();
    JGFMyCreateBench crb2 = new JGFMyCreateBench(10*MAXSIZE); 
    crb2.JGFrun();
    JGFMyCreateBench crb3 = new JGFMyCreateBench(20*MAXSIZE);     
    crb3.JGFrun();
    JGFMyCreateBench crb4 = new JGFMyCreateBench(40*MAXSIZE);     
    crb4.JGFrun();
    JGFMyCreateBench crb5 = new JGFMyCreateBench(80*MAXSIZE);     
    crb5.JGFrun();
    JGFMyCreateBench crb6 = new JGFMyCreateBench(120*MAXSIZE);     
    crb6.JGFrun();
    JGFMyCreateBench crb7 = new JGFMyCreateBench(240*MAXSIZE);     
    crb7.JGFrun();   
    
  }
}

class A {}

class AA {
  public AA() {}
}

class A1 {
 int a;
}

class A2 {
  int a,b;
}
class A4 {
  int a,b,c,d;
}
class A4L {
  long a,b,c,d;
}
class A4F {
  float a,b,c,d;
}
class A4if {
  int a,b,c,d;
  float g,h,i,j;
}

class AB {
  A a= new A();  
}

class ABC {
  A a;

  public ABC() {
    a = new A();
  }  
}

class B extends A {};




class myObject{
	int a, b, c;
	A a1;
	A1 ab;
	A2 abc;
}


