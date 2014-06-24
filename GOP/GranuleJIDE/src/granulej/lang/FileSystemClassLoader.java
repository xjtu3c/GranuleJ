package granulej.lang;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.naming.directory.DirContext;

public class FileSystemClassLoader extends ClassLoader {

	private String rootDir;	
	private ByteArrayOutputStream baos=null;
	private InputStream ins=null;
	
	public FileSystemClassLoader()
	{
		
	}
	public FileSystemClassLoader(String rootDir) {
		//this.parent = getParent();
		//system = getSystemClassLoader();
		this.rootDir = rootDir;
	}
	
	@SuppressWarnings("unchecked") 
	protected Class<?> findClass(final String name) throws ClassNotFoundException{	
		Class<?> clazz=null;
		//先找緩存
		if(hasLoadedResources()){
	    clazz = findAlreadyLoadedClass(name);	    
		}
	    //再找系統
	    if(null == clazz){
	     clazz = findLoadedClass(name);
	    }	
        //然後突破安全限制，進行裝載
	    if (null == clazz) {	 
		AccessControlContext ac=AccessController.getContext();
		try{
		    return (Class) AccessController.doPrivileged(
		       new PrivilegedExceptionAction() {
		         public Object run() throws ClassNotFoundException {
		    	   byte[] classData = getClassData(name);
		    	   contentLength=classData.length;
				   if (classData == null) {
					throw new ClassNotFoundException();
				    }
				   else{
					return defineClass(name,classData,0,classData.length);
				   }
		          }
		       },ac);	
		       } catch (java.security.PrivilegedActionException pae) {
	        return super.findClass(name);
	       }
	    } 	    
         return clazz;
	}	

	private byte[] getClassData(String className) {
		String path = classNameToPath(className);
		try {
            //load from the network
			/*if(checkURL(path)){
			URL url = new URL(path);
			ins = url.openStream();
			}*/
			ins = new FileInputStream(path);			
			baos = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			while ((bytesNumRead = ins.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesNumRead);
			}			
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}	

	private String classNameToPath(String className) {
		return rootDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
	}
	
	@SuppressWarnings("finally")
	public Class<?> loadClass(String name) throws ClassNotFoundException {
	   Class<?> c = null;	 
       try {    	   
		   c = super.loadClass(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {			
			if (c == null) {
				c = this.findClass(name);				
		    }
		   return c;
		}	   
	} 
	
	protected void SearchAndCache(String name, Class cls){
	      String classPath=classNameToPath(name);	
		   GranuleEntry entry=null;
		   entry=findResourceFromInternal(name);		  
		   if(entry!=null)
		     return ;
		   else {			   
			    /*synchronized (resourceEntries) {
				    entry=new GranuleEntry();
			        entry.loadedClass = cls;
		            entry.source = getURL(classPath);
			        GranuleEntry entry2 = (GranuleEntry) resourceEntries.get(name);
			           if (entry2 == null) {
			                resourceEntries.put(name, entry);
			            } else {
			                entry = entry2;
			            }        
			     }*/
			   entry=new GranuleEntry();
			   entry.loadedClass=cls;
			   entry.source=getURL(classPath);
			   putResource(name,entry);		   
		   }
	}

    protected GranuleEntry findResourceFromInternal0(String name){
        try{
    	  lock.lock();   		
    	  GranuleEntry entry = (GranuleEntry) resourceEntries.get(name);
    	  if(entry!=null){
    		 entry.updateLastAccess();
    		 entry.getCount().incrementAndGet();
    		 return entry;
    	  }
    	  return null;   	  
    	} finally{
    	  lock.unlock();    		
    	}   	
    }
	
	protected GranuleEntry findResourceFromInternal(String name)
	{
		GranuleEntry entry = (GranuleEntry) resourceEntries.get(name);
	    if (entry != null){
	    return entry;  
	    }
	    return null;		
	}	
	
	protected GranuleEntry findResourceFromInternal(File file, String path){
	       GranuleEntry entry = new GranuleEntry();
	       try {
	           entry.source = getURI(new File(file, path));
	       } catch (MalformedURLException e) {
	           return null;
	       }
	       return entry;
	   }
  
    protected void setParentClassLoader(ClassLoader pcl) {
        parent = pcl;
    }    
 
    protected Class<?> findAlreadyLoadedClass(String name) {
        GranuleEntry entry = resourceEntries.get(name);       
        if (entry != null) {
            return entry.loadedClass;
        }
        return (null); 
    }
    
   @SuppressWarnings("finally")
   protected URL getURL(String name){
	  URL url=null;
	  if(null==name && "".equals(name))
		return null;	  
	  try{
		File file=new File(name);
		if(file.exists() && file.isFile())
	    url=getURI(file);		   
	  }catch(MalformedURLException e) {
		 e.printStackTrace();
	  } finally{
	   return url;
	  }
   }
    
   protected URL getURI(File file) throws MalformedURLException {
      File realFile = file;
      try {
       realFile = realFile.getCanonicalFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return realFile.toURI().toURL();
    }
     
   private boolean checkURL(String path)
   {
	 if(path.startsWith("http"))
	 return true;
	 return false;
   } 
   
   
   protected void putResource(String name, GranuleEntry entry){
	   try{
		  lock.lock();
		  if(resourceEntries.size()> CACHE_MAX_SIZE-1 && resourceEntries.containsKey(name)){
			Set<Map.Entry<String,GranuleEntry>> entries=resourceEntries.entrySet();
			removeRencentlyLeastAccess(entries);			  
		  }
		  GranuleEntry entry2 = (GranuleEntry) resourceEntries.get(name);
          if (entry2 == null) {
               resourceEntries.put(name, entry);
           } else {
               entry = entry2;
           } 		   
	   }finally{
		  lock.unlock();		   
	   } 	   
   }
   
   protected void removeRencentlyLeastAccess(Set<Map.Entry<String,GranuleEntry>> entries){
	   int least=0; 
	   int earliest=0;
	   String removeByCount=null;
	   String removeByTime=null;
	   Iterator<Map.Entry<String, GranuleEntry>> it = entries.iterator();
       if (it.hasNext()) {
           Map.Entry<String,GranuleEntry> entry = it.next();
           least = entry.getValue().getCount().get();
           removeByCount = entry.getKey();
           earliest =(int)entry.getValue().getLastAccess().get();
           removeByTime = entry.getKey();
       }
       while (it.hasNext()) {
           Map.Entry<String, GranuleEntry> entry = it.next();
           if (entry.getValue().getCount().get() < least) {
               least = entry.getValue().getCount().get();
               removeByCount = entry.getKey();
           }
           if (entry.getValue().getLastAccess().get() < earliest) {
               earliest = entry.getValue().getCount().get();
               removeByTime = entry.getKey();
           }
       }
  
       if (least > MINI_ACCESS) {
           resourceEntries.remove(removeByTime);
       } else {
           resourceEntries.remove(removeByCount);
       }   
   }   
   
   
   protected boolean hasLoadedResources() {
       return !resourceEntries.isEmpty();
   }
   
   protected int size(){
	   try{
		 lock.lock();
		 return resourceEntries.size();
	   } finally{
		 lock.unlock();   
	   }   
   }  
    
   Iterator getCachedResources() {
       return resourceEntries.values().iterator();
   }   
   
   protected void cleanCacheResources() {
       for (Iterator ci=resourceEntries.values().iterator(); ci.hasNext(); ) {
           if (ci.next() == NOT_FOUND_RESOURCE) {
               ci.remove();
           }
       }
   }  
   
   protected void clearWholeCache(){
      try{
      lock.lock();
	  for(String s:resourceEntries.keySet()){
	    resourceEntries.put(s,null);
	  }
	    resourceEntries.clear();
      } finally{
    	 lock.unlock();    	  
      }
      }    
    protected void removeCacheResource(String name)
    {
	   try{
       lock.lock();
       GranuleEntry entry = resourceEntries.get(name);
	   if(entry!=null){
		entry=null;
	   }
	   resourceEntries.remove(name);
	   } finally{
	   lock.unlock();
	   }	   
    }
    
    protected boolean ContainsResource(String name)
    {
    	try{
           lock.lock();
           return resourceEntries.containsKey(name);
    	} finally{
    		lock.unlock();
    	}    		
    }   
   
    private final static int CACHE_MAX_SIZE = 100;    
    protected static HashMap<String, GranuleEntry> resourceEntries = new HashMap<String, GranuleEntry>(CACHE_MAX_SIZE);
    
    private static final Lock lock = new ReentrantLock();
    private static int MINI_ACCESS = 10;
    
    
    protected ClassLoader system=null;    
    protected boolean delegate = false;	
    protected ClassLoader parent=null;
    protected static String[] repositories = new String[0];
    protected DirContext resources = null;   
    protected static File[] files = new File[0];
    protected static boolean hasExternalRepositories = false;
    protected long contentLength=-1; 
    final static GranuleEntry NOT_FOUND_RESOURCE=new GranuleEntry();
    final static String URL_FLAG="file";
    
    public static void doWork()
	{
		try{
			FileSystemClassLoader tc=new FileSystemClassLoader("E:\\bdGranuleJ\\example\\Count_SiteA_1\\bin");
	        Class c = tc.loadClass("ML");
	        //tc.SearchAndCache("ML",c);
		    Method method1=c.getDeclaredMethod("fitness");
			method1.setAccessible(true);
		    //System.out.println(method1.invoke(c));
		} catch(Exception e)
		{
		  e.printStackTrace();
		}
	}
    public static void main(String args[]) throws Exception
    {
    	FileSystemClassLoader cl=new FileSystemClassLoader();
    	long startTime=System.currentTimeMillis();  
    	for(int i=0;i<1000000;i++){
    	cl.doWork();
    	//System.out.println("i:"+i);
    	}
    	long endTime=System.currentTimeMillis(); //获取结束时间  
    	System.out.println("程序运行时间： "+(endTime-startTime)+"ms");  
    }    
   }