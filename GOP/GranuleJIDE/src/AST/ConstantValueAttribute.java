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
public class ConstantValueAttribute extends Attribute {

		public ConstantValueAttribute(ConstantPool p,ContextVarDeclaration f)
		{
			super(p,"ConstantValue");
			int constantvalue_index=constantvalue_index = f.type().addConstant(p, f.getInit().constant());
		    u2(constantvalue_index);
		}


	    public ConstantValueAttribute(ConstantPool p, FieldDeclaration f) {
	      super(p, "ConstantValue");
	      int constantvalue_index = constantvalue_index = f.type().addConstant(p, f.getInit().constant());
	      u2(constantvalue_index);
	    }


}
