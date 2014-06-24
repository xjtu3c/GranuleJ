package granulej.lang;

import java.util.concurrent.CountDownLatch;

import org.w3c.dom.Document;

public class GranuleReRegisTask implements Runnable {
  
	private CountDownLatch downLatch;
	
	private CountDownLatch start;
	
	private Document doc;
	
	private int version;
    
    public GranuleReRegisTask(CountDownLatch start,CountDownLatch downLatch, Document doc, int version){  
        this.start=start;
    	this.downLatch = downLatch;
        this.doc=doc;
        this.version=version;
    }
   
    public void run() {  
        //System.out.println("老板正在等所有的人干活......");
        start.countDown();
        try {  
            this.downLatch.await(); 
            //System.out.println("活都干完了，老板开始检查了！");            
            String proj_path=IndividualInfo.getInstance().getWorkDirectory();
    		String md5=doc.getDocumentElement().getAttribute("md5");
    		String config_f=getFileSimpleName("TestGranuleTree");
    		String simip_f=getFileSimpleName("GranuleSimiPath");
    		new Thread(new GranuleRegister(md5, proj_path, config_f, simip_f)).start();            
        } catch (InterruptedException e) { 
        	throw new RuntimeException(e);
        } 
     }  
	 public String getFileSimpleName(String suffix)
	 {
		 return suffix+version+".xml";
	 }
	
}
