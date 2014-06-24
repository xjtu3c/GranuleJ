package granulej.lang;
import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.*;
import java.nio.ByteBuffer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;


public class GranuleClassLoader extends ClassLoader {
	    private String fileName;
	    public GranuleClassLoader(String fileName) {
	        this.fileName = fileName;
	    }
	    public GranuleClassLoader()
	    {
	    	
	    }
	    protected Class<?> findClass(String className) throws ClassNotFoundException {
	        Class clazz = this.findLoadedClass(className);
	        if (null == clazz) {
	            try {
	                String classFile = getClassFile(className);                
	                FileInputStream fis = new FileInputStream(classFile);
	                FileChannel fileC = fis.getChannel();
	                ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                WritableByteChannel outC = Channels.newChannel(baos);
	                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	                while (true) {
	                    int i = fileC.read(buffer);
	                    if (i == 0 || i == -1) {
	                        break;
	                    }
	                    buffer.flip();
	                    outC.write(buffer);
	                    buffer.clear();
	                }
	                fis.close();
	                byte[] bytes = baos.toByteArray();
	                clazz=defineClass(className,bytes,0,bytes.length);
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return clazz;
	    }
	    public byte[] loadClassBytes(String className) throws ClassNotFoundException {
	        try {
	            String classFile = getClassFile(className);
	            FileInputStream fis = new FileInputStream(classFile);
	            FileChannel fileC = fis.getChannel();
	            ByteArrayOutputStream baos = new
	                    ByteArrayOutputStream();
	            WritableByteChannel outC = Channels.newChannel(baos);
	            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	            while (true) {
	                int i = fileC.read(buffer);
	                if (i == 0 || i == -1) {
	                    break;
	                }
	                buffer.flip();
	                outC.write(buffer);
	                buffer.clear();
	            }
	            fis.close();
	            return baos.toByteArray();
	        } catch (IOException fnfe) {
	            throw new ClassNotFoundException(className);
	        }
	    }
	    private String getClassFile(String name) { 
	        StringBuffer sb = new StringBuffer(fileName);
	        name = name.replace('.', File.separatorChar) + ".class";	       
	        sb.append(File.separator + name);
	        return sb.toString();
	    }
	   public static void main(String[] args) {
	            try {	             
	            	GranuleClassLoader tc=new GranuleClassLoader();
	                Class c = tc.findClass("C");  	                
	                c.newInstance();
	                byte[] by=tc.loadClassBytes("C");
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace(); 
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (InstantiationException e) {
	                e.printStackTrace(); 
	            }
	        }
	    }
	
	

