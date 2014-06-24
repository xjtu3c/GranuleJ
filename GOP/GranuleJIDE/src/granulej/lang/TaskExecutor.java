package granulej.lang;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.w3c.dom.Document;

public class TaskExecutor implements ChangeListener {
		
	private ByteArrayOutputStream sos;
	private int version;
	private ExecutorService gExecutorRegister;
	private static TaskExecutor instance=null;
	public static synchronized TaskExecutor getInstance() {
		if (instance == null) {
			instance = new TaskExecutor();
		}
		return instance;
	}	
     
	private TaskExecutor(){
	   gExecutorRegister=Executors.newFixedThreadPool(1);
	}

	public void actionChange(ChangeEvent e){
		 sos=writeObject((Document)e.getSource());
		 if(sos == null)
		 return;
		 version=e.getVersion();
		 if(!gExecutorRegister.isShutdown())
         gExecutorRegister.execute(new GranuleReRegister(sos,version));
	 }
	
	 //TODO 这里美誉释放线程池资源，导致程序在执行后无法正常退出，所以需要在最后执行该方法关闭线程池
     public void close(){
	   gExecutorRegister.shutdown();	   
     }
	 
	 public ByteArrayOutputStream writeObject(Document doc)
	 {
		 ByteArrayOutputStream bos=null;
		 ObjectOutputStream oos = null;
		 try{
			 bos=new ByteArrayOutputStream();
			 oos=new ObjectOutputStream(bos);
			 oos.writeObject(doc);
		 }catch(Exception e)
		 { 
			e.printStackTrace();
		 }
		 
		 try {
			 oos.close();
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }

		 return bos;		 
	 }
   }