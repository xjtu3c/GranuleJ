
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.Collection;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.util.Stack;import java.util.regex.Pattern;import java.util.HashMap;import java.util.Iterator;import java.io.FileOutputStream;import java.io.IOException;import java.io.FileInputStream;import java.security.MessageDigest;import java.security.NoSuchAlgorithmException;import javax.xml.transform.stream.StreamResult;import javax.xml.transform.dom.DOMSource;import javax.xml.transform.TransformerFactory;import javax.xml.transform.Transformer;import javax.xml.parsers.DocumentBuilder;import javax.xml.parsers.DocumentBuilderFactory;import org.w3c.dom.Element;import org.w3c.dom.Document;import java.util.Map.Entry;import javax.xml.transform.TransformerException;import javax.xml.parsers.ParserConfigurationException;

public class MutableSet extends Set {
    // Declared in Sets.jrag at line 620


    private static int nbrOfCreatedSets = 0;

    // Declared in Sets.jrag at line 621

	public static int getNbrOfCreatedSets() { return nbrOfCreatedSets; }

    // Declared in Sets.jrag at line 623


	private static int nbrOfAdd = 0;

    // Declared in Sets.jrag at line 624

	public static int getNbrOfAdd() { return nbrOfAdd; }

    // Declared in Sets.jrag at line 626


	protected MutableSet(int size) {
		super(size);
		nbrOfCreatedSets++;
	}

    // Declared in Sets.jrag at line 631


	public void add(Object element) {

		nbrOfAdd++;

		int index = index(element);
		int offset = index >> 5;
		int bit = 1 << (index & 0x1f);
		if (bits.length > offset && (bits[offset] & bit) == bit) {
			return;
		}
		// Copy if needed
		if (offset >= bits.length) {
			int[] newBits = new int[offset + 1];
			for (int i = 0; i < bits.length; i++) {
				newBits[i] = bits[i];
			}
			bits = newBits;
		}
		bits[offset] |= bit;
	}

    // Declared in Sets.jrag at line 652


	public void add(Set set) {

		nbrOfAdd++;

		if (set.bits.length > bits.length) {
			int[] newBits = new int[set.bits.length];
			for(int i = 0; i < bits.length; i++) {
				newBits[i] = bits[i];
 			}
			bits = newBits;         
		}
		int i = 0;
		while (i < set.bits.length && (bits[i] & set.bits[i]) == set.bits[i]) { 
			i++;
		}
		if (i != bits.length) {
			for(; i < set.bits.length; i++)
				bits[i] |= set.bits[i];			
		}
	}

    // Declared in Sets.jrag at line 673

	
    public static MutableSet empty() {
      return new MutableSet(0);
    }


}
