package granulej.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class GVMUtility {
	private String syspath;
	
	public static String getIp() {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;
			while (netInterfaces.hasMoreElements() && !finded) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> address = ni.getInetAddresses();
				while (address.hasMoreElements()) {
					ip = address.nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
						netip = ip.getHostAddress();
						finded = true;
						break;
					} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
						localip = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

	 public void setSystemPath(String path)
	 {
		 this.syspath=path;
	 }
	 
	 public String getSystemPath()
	 {
		 return syspath;
	 }	
	 public static void copyDirectory(String srcDir, String destDir)
	 {
		 try{
		 File dDir=new File(destDir);
		 File sDir=new File(srcDir);
//	     if(!new File(srcDir).exists() && new File(destDir).exists())
//	     return ;
	     dDir.mkdirs();
	     File[] file=sDir.listFiles();
	     int flength = file.length;
	     for(int i=0; i< flength; i++){
	     if(file[i].isFile() && !file[i].getName().endsWith(".dll")){
	    	 File sourceFile=file[i];   
             File targetFile=new File(dDir.getAbsolutePath()+File.separator+file[i].getName());  
             CopyFile(sourceFile,targetFile);  
           } 
	     if(file[i].isDirectory()&& file[i].getName().equals("granulej")){
           String dir1=srcDir + "/" + file[i].getName();    
           String dir2=destDir + "/"+ file[i].getName();  
           copyDirectory(dir1, dir2);    
	      }	    
         if(file[i].isDirectory() && file[i].getName().equals("lang"))
         {
            String dir1=srcDir + "/" + file[i].getName();    
            String dir2=destDir + "/"+ file[i].getName();  
            copyDirectory(dir1, dir2);         
         }
	     }
		 }catch(Exception e){
		  e.printStackTrace();
		 }
	}	 
	 public static void CopyFile(File srcFile, File destFile)
	 {
		 try {
			 int bytesum = 0;
			 int byteread = 0;
			  InputStream inStream = new FileInputStream(srcFile);
			   FileOutputStream fs = new FileOutputStream(destFile);
			   byte[] buffer = new byte[1444];
				  while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				  }
				  inStream.close();
				  fs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		 
	 public static void CopyFile(String srcFile, String destFile)
	 {
        try{
    	   File src=new File(srcFile);
    	   File dest=new File(destFile);
           if(!dest.exists()){
    	     dest.createNewFile();
             CopyFile(src,dest); 
           }
       }catch(IOException e)
       {
    	 e.printStackTrace();
       }
      }	
	 
   public static void main(String[] args){    	 
	   GVMUtility.CopyFile("E:/Repository/GranuleJIDE/system/GopContext.java", "D:/test/src/Test/src/GopContext.java");
     }   
     
}
