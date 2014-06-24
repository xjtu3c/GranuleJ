package AST;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.io.File;
import java.util.*;
import beaver.*;
import java.util.ArrayList;
import java.util.zip.*;
import java.io.*;
import java.util.Stack;
import java.util.regex.Pattern;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.xml.transform.TransformerException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Collection;

/**
 * @ast class
 * @declaredat :0
 */
public class GranuleNode0 extends java.lang.Object {

	public String granulename = new String();

 
        public String contextname= new String();

 

	public GranuleNode0(String granulename,String contextname){ 
		
                this.granulename = granulename;
		this.contextname = contextname;

	}


	
	public String getGranulename(){ 
		return granulename;
	}

 

	public void setGranulename(String granulename){ 

		this.granulename = granulename; 

	}

 


	public void setContextname(String contextname){ 

		this.contextname = contextname; 

	}

 

	public String getContextname(){ 

		return contextname; 

	}


}
