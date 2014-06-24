package granulej.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class TempFile {

	private String path;
	private String filename;
	
    public TempFile(){
      path = System.getProperty("user.dir")+File.separator+"temp";
      filename=path+File.separator+"temp.txt";
      createNewFile();
    }   
    
    public void createNewFile(){
    	try{
    		File file = new File(path);
    		if(!file.exists())
    		file.mkdirs();
    		File ab_file=new File(filename);
    		if(!ab_file.exists()){
    		  ab_file.createNewFile();
    		}
    	} catch(IOException e)
    	{
    	  e.printStackTrace();
    	}   	
    }
  
    public String readFile()
    {
    	StringBuffer sb=new StringBuffer();
    	String con_seq="";
    	try{
        BufferedReader bufread=new BufferedReader(new FileReader(filename));
        while((con_seq=bufread.readLine())!=null)
        {
        	sb.append(con_seq);
        	sb.append("\n");
        }
        return sb.toString();
    	}catch(FileNotFoundException e)
    	{
    	  e.printStackTrace();
    	}catch(IOException e)
    	{
    	  e.printStackTrace();
    	}
    	return sb.toString();
    }
    
    public void writeFile(String str){
    	 String filein = str+"\r\n";
         RandomAccessFile mm=null;
         try{
      	   mm = new RandomAccessFile(filename, "rw");
           mm.writeBytes(filein);
         }catch(IOException e)
         {
      	 e.printStackTrace();
         } finally {
      	   if(mm!=null){
      		   try{
      			  mm.close();
      		   } catch(IOException ie)
      		   {
      			  ie.printStackTrace();
      		   }
      	   }
         }   	
    }
    
    public void replaceByStr(String old,String replace)
    {
    	String temp="";
    	try{
    	File file=new File(filename);
    	BufferedReader buffer=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    	StringBuffer buf=new StringBuffer();
    	 for (int j = 1; (temp = buffer.readLine()) != null && !temp.equals(old); j++) {
         buf = buf.append(temp);
         buf = buf.append(System.getProperty("line.separator"));
         }
    	 buf = buf.append(replace);
    	 
    	 while ((temp = buffer.readLine()) != null) {
              buf = buf.append(System.getProperty("line.separator"));
              buf = buf.append(temp);
          }

         buffer.close();
         
         FileOutputStream fos = new FileOutputStream(file);
         PrintWriter pw = new PrintWriter(fos);
         pw.write(buf.toString().toCharArray());
         pw.flush();
         pw.close();      
          
    	}catch(IOException e)
    	{
    	  e.printStackTrace();
    	}   	
    }   
    
     public void deleteFile()
     {
    	 File file=new File(filename);
         if(file.isFile()&&file.exists())
         file.delete();
    	 File p=new File(path);
         if(p.isDirectory() && p.exists())
         file.delete();    	 
     }  
     
     public static void main(String args[])
     {   	
    	
     }	
}
