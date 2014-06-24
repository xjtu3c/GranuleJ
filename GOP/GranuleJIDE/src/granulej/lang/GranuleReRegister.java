package granulej.lang;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import org.w3c.dom.Document;

    public class GranuleReRegister implements Runnable{ 
		
	private Set<Runnable> tasks = new HashSet<Runnable>();

	private Document doc;

	private String gfname;
	
	private int version;
	
	private static int thread_num=2;	
	
	private ByteArrayOutputStream output;
	
	public GranuleReRegister(ByteArrayOutputStream output,int version)
	{
	   this.output=output;
	   this.version=version;	   
	}	

	public void addTask(Runnable one) {
		tasks.add(one);
	}

	public void removeTask(Runnable one) {
		tasks.remove(one);
	}

	public Collection<Runnable> getTasks() {
		return Collections.unmodifiableSet(tasks);
	}
	
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public String getGranuleFilename() {
		return gfname;
	}

	public void setGranuleFilename(String gfname) {
		this.gfname = gfname;
	}
	
	public void run(){	
	   doc=(Document)getObject(output);
	   if(doc==null)
	   return ;
	   gfname=IndividualInfo.getInstance().getConfigfile();
	   ExecutorService executor=Executors.newFixedThreadPool(thread_num+1);	   
       final CountDownLatch start = new CountDownLatch(1);
       final CountDownLatch latch = new CountDownLatch(thread_num);       
		        
       addTask(new xmlFileUpdate(start, latch, doc, gfname, "gTree",version));
	   addTask(new xmlFileUpdate(start, latch, doc, gfname,"path",version));
	   for (Iterator<Runnable> iter=getTasks().iterator();iter.hasNext();){
		   executor.execute(iter.next());
	   }
	    executor.execute(new GranuleReRegisTask(start,latch,doc,version)); 
		executor.shutdown();		  
	}
	
	private Object getObject(ByteArrayOutputStream outStream)
	{
		Object obj=null;
		try{
			ByteArrayInputStream bis=new ByteArrayInputStream(outStream.toByteArray());
			ObjectInputStream ois=new ObjectInputStream(bis);
			obj=ois.readObject();
			ois.close();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	   return obj;		
	}
     //序列化对象	
	 public Object getCopy(Object src){
		 Object obj = null;
	     try{		
		  ByteArrayOutputStream bos = new ByteArrayOutputStream();
		  ObjectOutputStream oos = new ObjectOutputStream(bos); 
		  oos.writeObject(src);
		  ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		  ObjectInputStream ois = new ObjectInputStream(bis);
		  obj = ois.readObject();	
	     }catch(IOException e){
			  e.printStackTrace();
	     }catch(ClassNotFoundException e) {
			e.printStackTrace();
	     }
	      return obj;
      }
}
