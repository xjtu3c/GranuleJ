package granulej.lang;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class IndividualInfo {
	private String configfile="TestGranule.xml";

	private String md5;

	private int version; //个体演化的版本号,空字符串为初始版本

	private List<Integer> ports = new ArrayList<Integer>();

	private static IndividualInfo instance = null;
	
	private String work_dir;//工作目录
	
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();	
	

	public String getWorkDirectory() {
		return work_dir;
	}

	public void setWorkDirectory(String work_dir) {
		this.work_dir = work_dir;
	}

	public static synchronized IndividualInfo getInstance() {
		if (instance == null) {
			instance = new IndividualInfo();
		}
		return instance;
	}
    	
	private IndividualInfo() {
		version=0;
		work_dir=getCurrentDirectory();
	}

	public String getConfigfile() {
		r.lock();
		try{
		return configfile;
		} finally{
		 r.unlock();
		}
	}

	public void setConfigfile(String configfile) {
		w.lock();
		try{
		this.configfile = configfile;
		} finally{
		  w.unlock();
		}
	}

	public String getMd5() {
		r.lock();
		try{
		  return md5;
	    } finally{
	    	r.unlock();
	    }		
     }

	public void setMd5(String md5) {
		w.lock();
		try{
		 this.md5=md5;
		} finally{
		  w.unlock();
		}
	}

	public synchronized int getPort() {
		while (ports.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int port = ports.get(0);
		ports.clear();
		notify();
		return port;
	}

	public synchronized void putPort(int port) {
		while (ports.size() == 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ports.add(port);
		notify();
	}

	public String getCurrentDirectory() {
		return System.getProperty("user.dir") + File.separator;
	}

	public void setVersion(int version) {
	    try{
	     w.lock();
	    this.version = version;
	    } finally{
	       w.unlock();
	    }
	}

	public int getVersion() {
		try{
	      r.lock();
		return version;
		} finally{
		  r.unlock();
		}
	}
}
