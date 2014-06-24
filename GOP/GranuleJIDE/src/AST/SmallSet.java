
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.Collection;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.util.Stack;import java.util.regex.Pattern;import java.util.HashMap;import java.util.Iterator;import java.io.FileOutputStream;import java.io.IOException;import java.io.FileInputStream;import java.security.MessageDigest;import java.security.NoSuchAlgorithmException;import javax.xml.transform.stream.StreamResult;import javax.xml.transform.dom.DOMSource;import javax.xml.transform.TransformerFactory;import javax.xml.transform.Transformer;import javax.xml.parsers.DocumentBuilder;import javax.xml.parsers.DocumentBuilderFactory;import org.w3c.dom.Element;import org.w3c.dom.Document;import java.util.Map.Entry;import javax.xml.transform.TransformerException;import javax.xml.parsers.ParserConfigurationException;

public class SmallSet extends java.lang.Object {
    // Declared in Sets.jrag at line 245

    HashSet set = new HashSet();

    // Declared in Sets.jrag at line 246

    public Iterator iterator() { return set.iterator(); }

    // Declared in Sets.jrag at line 247

    public static SmallSet empty() { return emptySet; }

    // Declared in Sets.jrag at line 248

    private static SmallSet emptySet = new SmallSet() {
      public SmallSet union(SmallSet set) { return set; }
      public SmallSet union(Object element) { return new SmallSet().union(element); }
      public SmallSet compl(SmallSet set) { return this; }
      public SmallSet compl(Object element) { return this; }
      public SmallSet intersect(SmallSet set) { return this; }
      public boolean isEmpty() { return true; }
    };

    // Declared in Sets.jrag at line 256

    public static SmallSet full() { return fullSet; }

    // Declared in Sets.jrag at line 257

    private static SmallSet fullSet = new SmallSet() {
      public SmallSet union(SmallSet set) { return this; }
      public SmallSet union(Object element) { return this; }
      public SmallSet compl(SmallSet set) {
    	  throw new Error("compl not supported for the full set");
      }
      public SmallSet compl(Object element) {
        throw new Error("compl not supported for the full set");
      }
      public SmallSet intersect(SmallSet set) { return set; }
      public boolean isEmpty() { return false; }
    };

    // Declared in Sets.jrag at line 270


    protected SmallSet() {
    }

    // Declared in Sets.jrag at line 272

    public SmallSet union(SmallSet set) {
      if(set.isEmpty() || this.equals(set))
        return this;
      SmallSet newSet = new SmallSet();
      newSet.set.addAll(this.set);
      newSet.set.addAll(set.set);
      return newSet;
    }

    // Declared in Sets.jrag at line 281

    
    public SmallSet union(Object element) {
      if(contains(element))
        return this;
      SmallSet newSet = new SmallSet();
      newSet.set.addAll(this.set);
      newSet.set.add(element);
      return newSet;
    }

    // Declared in Sets.jrag at line 290

    
    public SmallSet compl(SmallSet set) {
      if(set.isEmpty())
        return this;
      SmallSet newSet = new SmallSet();
      newSet.set.addAll(this.set);
      newSet.set.removeAll(set.set);
      return newSet;
    }

    // Declared in Sets.jrag at line 299

    
    public SmallSet compl(Object element) {
      if(!set.contains(element))
        return this;
      SmallSet newSet = new SmallSet();
      newSet.set.addAll(this.set);
      newSet.set.remove(element);
      return newSet;
    }

    // Declared in Sets.jrag at line 308

    
    public SmallSet intersect(SmallSet set) {
      if(this.equals(set) || set == fullSet)
        return this;
      SmallSet newSet = new SmallSet();
      newSet.set.addAll(this.set);
      newSet.set.retainAll(set.set);
      return newSet;
    }

    // Declared in Sets.jrag at line 317


    public boolean contains(Object o) {
      return set.contains(o);
    }

    // Declared in Sets.jrag at line 321


    public boolean equals(Object o) {
      if (o == null) return false;
      if(this == o) return true;
      if(o instanceof SmallSet) {
        SmallSet set = (SmallSet)o;
        return this.set.equals(set.set);
      }
      return super.equals(o);
    }

    // Declared in Sets.jrag at line 331


    public boolean isEmpty() {
      return set.isEmpty();
    }

    // Declared in Sets.jrag at line 334

    public void add(SmallSet set) {
      this.set.addAll(set.set);
    }

    // Declared in Sets.jrag at line 337

    public void add(Object o) {
      this.set.add(o);
    }

    // Declared in Sets.jrag at line 340

    public static SmallSet mutable() {
      return new SmallSet();
    }


}
