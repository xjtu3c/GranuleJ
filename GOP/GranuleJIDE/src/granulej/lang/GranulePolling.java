package granulej.lang;

import gui.constant.GranuleConstant;

import java.io.File;
import java.lang.ClassNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import granulej.gui.action.RunAction;
import granulej.util.xmlUtil;


public class GranulePolling {
	
	private static GranulePolling instance = null;

	private ReceivePacket r_packet;

	private SendPacket s_packet;

	public static synchronized GranulePolling getInstance() {
		if (instance == null)
			instance = new GranulePolling();
		return instance;
	}

	private GranulePolling() {
		init();
	}
	
	private void init() {
		s_packet = new SendPacket();
		r_packet = null;
	}
	
    //做一次相似粒查找，gname表示要查找的粒名，times表示查找的次数	
	public Object[] getPollingResult(String gname,int times) {	
		 Object[] res = new Object[2];
		 if(gname==null||times<0)
			 return res;
		 
		 s_packet.setGranuleName(gname);
		 s_packet.setMd5Code(IndividualInfo.getInstance().getMd5());
		
		 //第2次及以后的查找都以前一次的查找结果为线索进行查找，这主要是配合服务器上管理粒，是用类似链表的结构来存贮的,查找必须以上一个粒为依据
		 if (times == 0) {
			 s_packet.setRetryInfo(GranuleConstant.RETRY_NULL);
			 s_packet.setPosition(String.valueOf(0));
		 } else {
			 s_packet.setRetryInfo(r_packet.getRetryInfo());
			 s_packet.setPosition(r_packet.getPosition());
		 }
		 
		//开始查找，将当前的查找结果存到r_packet中
		GranuleSearch gs = new GranuleSearch(s_packet);
        gs.SimilarLookup();
		GranulePolling gp = GranulePolling.getInstance();
		Class simi_g = gp.getSimilarGranuleClass(gs.getLookupResult());
		
//		System.out.println("Similar granule is :"+simi_g);	
		
		res[0] = simi_g;
	    if(simi_g==null||gp.getConfigFile()==null){
	    	res[1]=GranuleConstant.GRANULE_NOT_EXIST;
	    }
	    else{
	    	res[1] = gp.getConfigFile();
	    }
		
		return res;
	}
	
	//获取相似粒
	public Class<?> getSimilarGranuleClass(String result) {
		Class<?> simig_class = null;
		if (result != null && !result.equals("")) {
			ReceivePacket rp = enclosingPacketFromReceiveSide(result);		
			if(rp.getSimilarGranuleName()!=null){	
			simig_class = loadGranule(rp.getSimilarGranuleFilePath(), rp.getSimilarGranuleName());
			return simig_class;
			}	
		}		
		return simig_class;
	}
	
	//实现粒的远端加载
	public static Class<?> loadGranule(String path, String gname) {
		Class<?> class1 = null;		
		if(!new File(path+File.separator+gname+".class").exists())
	    return null;
		try {			
			FileSystemClassLoader newloader = new FileSystemClassLoader(path);			
			class1 = newloader.loadClass(gname);			
			newloader.SearchAndCache(gname, class1);
			return class1;
		} catch (ClassNotFoundException e) {				
			e.printStackTrace();
		}
		return class1;
	}
	
	//找到相似粒后，从top到down的检查适合性	
	public Class<?> getAllSimilarGranuleClass(String result) {
		Class<?> simig_class =null;
		if (result != null && !result.equals("")) {
			ReceivePacket rp = enclosingPacketFromReceiveSide(result);
			if(rp.getSimilarGranuleName()!=null){	
			   boolean res=true;
			   ArrayList<String> gs=rp.getParents();
			   String path=rp.getSimilarGranuleFilePath();
			   for(int i=0;i< gs.size() && res;i++){			  
			    try{			    
			    	simig_class=loadGranule(path,gs.get(i));
			    	if(simig_class==null)
			        return null;
					Method pubMethod=simig_class.getDeclaredMethod("fitness");
					pubMethod.setAccessible(true);
					res=(Boolean)pubMethod.invoke(simig_class,(Object)null);
			     } catch(Exception e)
			     {
					     e.printStackTrace();
				 }	
				}
		    if(!res)
		    return null;			
			else
			return simig_class;
			}	
		}		
		return simig_class;
	}

	//解析传回的参数
	public static String getSimilarGranulePath(String filename) {
		if (filename != null && "".equals(filename)) {
			int index = filename.lastIndexOf(File.separator);
			filename = filename.substring(0, index > 0 ? index : 0);
		}
		return filename;
	}

	//从配置文件中根据传回的相似路径解析粒，得到粒的名称
	public void setReceivePacket(ReceivePacket r_packet) {
		this.r_packet = r_packet;
	}

	public ReceivePacket getReceivePacket() {
		return this.r_packet;
	}

	public String getCurrentSystemDir() {
		return System.getProperty("user.dir");
	}

	public String getConfigFile() {
		return r_packet.getConfigFile();
	}

	//解析字符串并放入接收包内
	public ReceivePacket enclosingPacketFromReceiveSide(String result) {		
		String[] res = new String[4];
		if (!result.isEmpty() && !result.trim().equals(GranuleConstant.GRANULE_SEARCH_FAIL)) {
			res = result.split(";");
			r_packet = ReceivePacket.getInstance();
			r_packet.setConfigFile(res[0]);
			r_packet.setSimilarPath(res[1]);
			r_packet.setRetryInfo(res[2]);
			r_packet.setPosition(res[3]);
		} else {
			r_packet = ReceivePacket.getInstance();
			r_packet.setConfigFile(null);
			r_packet.setSimilarPath(null);
		}
		return r_packet;
	}
	
	public static void main(String args[]) {
		getInstance().getSimilarGranuleClass(null);
	}
}
