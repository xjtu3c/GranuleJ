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



public class JGFMyCreateBench2 implements JGFSection1 {


  private static final int INITSIZE = 10000;
  private static final int MAXSIZE = 1000000;
  private static final double TARGETTIME = 10.0; 
  

  public void JGFrun(){

    int i,j,size;
    double time; 

    JGFInstrumentor.addTimer("Section1:Create:Object:Simple", "objects");
    JGFInstrumentor.addTimer("Section1:Create:Object:Simple2", "objects");
    

    time = 0.0; 
    size = INITSIZE; 
    myObject mo =new myObject();
    
    (new GopContext()).addContext("g3%myObject");
     
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

    
  }
  
public static void main (String argv[]){

    JGFInstrumentor.printHeader(1,0);

    JGFMyCreateBench2 crb = new JGFMyCreateBench2(); 
    crb.JGFrun();
    
  }
}


class GopContext {
	private  String path;
	private  Document document;
	private  Transformer transformer;
	private  StreamResult result;
	private  DocumentBuilder builder;

	
	public  void addContext(String name) {
		try {
			String place = System.getProperty("user.dir");

			place = place + "/bin/";
			System.out.println("place:" + place);
			path = place + "/UserClasses.xml";
			System.out.println("path:" + path);
			File dirFile = new File(place);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			path = place + "/UserClasses.xml";
	
			if (!new File(path).exists()) {
				Document doc = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder().newDocument();
				Element root = doc.createElement("userclass");
				doc.appendChild(root);
				writeXML(doc, path);
			}
			builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			document = builder.parse(new FileInputStream(path));
			transformer = TransformerFactory.newInstance().newTransformer();
			result = new StreamResult(new File(path));
			
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			document = builder.parse(new FileInputStream(path));
			Element rootElement = document.getDocumentElement();
			NodeList nodes = rootElement.getChildNodes();
        //    System.out.print(nodes.getLength());
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				System.out.println(node.toString());
				if (node instanceof Element) {
					Element child = (Element) node;
					Element newchild = document.createElement("shadowclass");
					newchild.setAttribute("name", name);
					///newchild.setAttribute("value", value);
					child.appendChild(newchild);
					break;
				}
			}

			Source source = new DOMSource(document);
			transformer.transform(source, result);
			
			System.out.println("hi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	private  void writeXML(Document doc, String path) {
		try {
			File f = new File(path);
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

