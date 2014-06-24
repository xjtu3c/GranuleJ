package granulej.lang;

import java.net.URL;
import java.security.cert.Certificate;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.jar.Manifest;

public class GranuleEntry {
  
	  public long lastModified = -1;
	  
	  public byte[] binaryContent = null;
	  
	  public volatile Class<?> loadedClass = null;
	  
	  public URL source = null; //文件路徑
	  
	  public URL codeBase = null; //远程方法所用到的代码基,現在木有使用
	  
	  public Manifest manifest = null;//jar文件的manifest
	  
	  public Certificate[] certificates = null;//網絡上的認證
	  
	  private AtomicInteger count;

      private AtomicLong lastAccess;
      
      public GranuleEntry(){
    	 this.count = new AtomicInteger(0);
         this.lastAccess = new AtomicLong(System.nanoTime());
      }

      public void updateLastAccess() {
         this.lastAccess.set(System.nanoTime());
      }
      
	  public Certificate[] getCertificates() {
		return certificates;
	  } 

      public void setCertificates(Certificate[] certificates) {
		this.certificates = certificates;
	  }

	  public long getLastModified() {
		return lastModified;
	  }

	  public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	  }

	public Class<?> getLoadedClass() {
		return loadedClass;
	}

	public void setLoadedClass(Class<?> loadedClass) {
		this.loadedClass = loadedClass;
	}

	public Manifest getManifest() {
		return manifest;
	}

	public void setManifest(Manifest manifest) {
		this.manifest = manifest;
	}

	public AtomicInteger getCount() {
		return count;
	}

	public void setCount(AtomicInteger count) {
		this.count = count;
	}

	public AtomicLong getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(AtomicLong lastAccess) {
		this.lastAccess = lastAccess;
	}
	
}
