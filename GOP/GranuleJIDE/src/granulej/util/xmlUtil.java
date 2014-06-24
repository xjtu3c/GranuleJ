package granulej.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.ArrayList;

public class xmlUtil {

	public static String getClassFile(String name) {
		StringBuffer sb = new StringBuffer(name);
		name = name.replace('.', File.separatorChar) + ".class";
		sb.append(File.separator + name);
		return sb.toString();
	}

	private static String syspath;

	public static Document load(String file) {
		try {
			Document document = null;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(false);
			dbf.setNamespaceAware(false);
			DocumentBuilder builder = dbf.newDocumentBuilder();
			document = builder.parse(new File(file));
			document.normalize();
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveXml(Document doc, String filename) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String pathSetOption(String args) {
		String path = null;
		return path;
	}

	public static String getAppPath() {
		String curDir = System.getProperty("user.dir");
		return curDir;
	}

	public String getSystemClassPath() {
		loadSystemClassPath();
		return syspath;
	}

	public static void loadSystemClassPath() {
		try {			
			File file = new File("../classpath.txt");
			if (!file.exists())
				return;
			BufferedReader bf = new BufferedReader(new FileReader("../classpath.txt"));
			syspath = bf.readLine();
			System.out.println(syspath);
			File pfile = new File(syspath);
			if (!pfile.exists()) {
				bf.close();
				file.delete();
				return;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getApplicationPath(Class cls) {
		if (cls == null)
			throw new java.lang.IllegalArgumentException("parameter is not null !");
		ClassLoader loader = cls.getClassLoader();
		String clsName = cls.getName() + ".class";
		Package pack = cls.getPackage();
		System.out.println("package name is : " + (pack == null));
		String path = "";
		if (pack != null) {
			String packName = pack.getName();
			if (packName.startsWith("java.") || packName.startsWith("javax."))
				throw new java.lang.IllegalArgumentException("This is system class");
			clsName = clsName.substring(packName.length() + 1);
			if (packName.indexOf(".") < 0)
				path = packName + "/";
			else {
				int start = 0, end = 0;
				end = packName.indexOf(".");
				while (end != -1) {
					path = path + packName.substring(start, end) + "/";
					start = end + 1;
					end = packName.indexOf(".", start);
				}
				path = path + packName.substring(start) + "/";
			}
		}
		java.net.URL url = loader.getResource(path + clsName);
		String realPath = url.getPath();
		int pos = realPath.indexOf("file:");
		if (pos > -1)
			realPath = realPath.substring(pos + 5);
		pos = realPath.indexOf(path + clsName);
		realPath = realPath.substring(0, pos - 1);
		if (realPath.endsWith("!"))
			realPath = realPath.substring(0, realPath.lastIndexOf("/"));
		try {
			realPath = java.net.URLDecoder.decode(realPath, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return realPath;
	}

	//拷贝特定文件名称的字节码到特定的目录下  
	public static void copyByteFiles(String org_Path, String tar_path, ArrayList<String> files) {

		int len = files.size();
		if (len <= 0)
			return;
		if (org_Path == null || org_Path.equals("") || tar_path == null || tar_path.equals(""))
			return;
		File tarFile = new File(org_Path);
		if (!tarFile.exists())
			tarFile.mkdir();
		else {
			if (!tarFile.isDirectory())
				return;
		}
		String oldFile = "";
		String newFile = "";
		for (int i = 0; i < len; i++) {
			oldFile = tar_path + File.separator + files.get(i) + ".class";
			newFile = org_Path + File.separator + files.get(i) + ".class";
			File ofile = new File(oldFile);
			File nfile = new File(newFile);
			copyFile(nfile, ofile);
		}
	
		//拷贝其他的文件
		File tarDir=new File(tar_path);
		File orgDir=new File(org_Path);
		
		String path="";
		File parTarFolder=tarDir.getParentFile();
		if(parTarFolder.getName().startsWith("SH")){
		path=parTarFolder.getParent()+File.separator+"SH";
		}
		else if(parTarFolder.getName().startsWith("XA")){
	    path=parTarFolder.getParent()+File.separator+"XA";
		}
		else if(parTarFolder.getName().startsWith("HK")){
		path=parTarFolder.getParent()+File.separator+"HK";
		}
		if(!"".equals(path)){
		File tarfolder=new File(path);
		if(tarfolder.exists()){ 
	         File[] fileLogs = tarfolder.listFiles(  
	            new FilenameFilter(){  
	                        public boolean accept(File dir, String name) {  
	                            return (name.endsWith(".class")&&(name.startsWith("SH")||name.startsWith("XA")||name.startsWith("HK")|| name.startsWith("HongKongView")||name.startsWith("XianView")||name.startsWith("ShanghaiView")));
	                        }     
	                    }  
	          );
	         for(int i=0;i<fileLogs.length;i++){
	        	String fname=fileLogs[i].getName();
	        	//System.out.println("file name is :"+fname);
	        	//System.out.println("path is :"+new File(tarfolder.getAbsoluteFile()+File.separator+fname).getAbsolutePath());
	        	copyFile(new File(org_Path+File.separator+fname), new File(tarfolder.getAbsoluteFile()+File.separator+fname));	        	
	         }
	     }
		}
		//TODO 关联资源的拷贝问题
//		String parTarDir=tarDir.getParent()+File.separator;
//		String parOrgDir=orgDir.getParent()+File.separator;
//	    copyDirFile(parTarDir+"ViewIcon",parOrgDir+"ViewIcon");
//	    copyDirFile(parTarDir+"Video",parOrgDir+"Video");	
	}

	public static void createFile(String path, boolean isFile) {
		createFile(new File(path), isFile);
	}

	public static void createFile(File file, boolean isFile) {
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				createFile(file.getParentFile(), false);
			} else {
				if (isFile) {
					try {
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					file.mkdir();
				}
			}
		}
	}

	public static void copyFile(File targetFile, File file) {
		if (targetFile.exists()) {
			return;
		} else {
			createFile(targetFile, true);
		}
		try {
			FileInputStream is = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(targetFile);
			byte[] buffer = new byte[1024*5];
			int len;
			while ((len=is.read(buffer)) != -1) {
				fos.write(buffer,0,len);
			}
			is.close();
			fos.flush();
			fos.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	   public static void copyDirFile(String oldPath, String newPath) {
	        try {
	            new File(newPath).mkdirs();
	            File a=new File(oldPath);
	            System.out.println("oldPath:"+oldPath);
	            String[] file=a.list();
	            File temp=null;
	            for (int i = 0; i < file.length; i++) {
	                if(oldPath.endsWith(File.separator)){
	                    temp=new File(oldPath+file[i]);
	                }else{
	                    temp=new File(oldPath+File.separator+file[i]);
	                }
	                if(temp.isFile()){
	                    FileInputStream input = new FileInputStream(temp);
	                    FileOutputStream output = new FileOutputStream(newPath + "/" +
	                    (temp.getName()).toString());
	                    byte[] b = new byte[1024 * 5];
	                    int len;
	                    while ((len = input.read(b)) != -1) {
	                        output.write(b, 0, len);
	                    }
	                    output.flush();
	                    output.close();
	                    input.close();
	                }
	                if(temp.isDirectory()){
	                    copyDirFile(oldPath+"/"+file[i],newPath+"/"+file[i]);
	                }
	            }
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	public static void main(String args[]) {
		Document doc = load("D:\\test\\src\\Test\\bin\\TestGranuleTree.xml");
		//setNewIndividualMd5Code(doc);

		//ArrayList<String> aa=new ArrayList<String>();
		//aa.add("gFlowControl.class");
		//copyByteFiles("D:/test/src/Test/bin/","D:/test/Test10/Test/bin/",aa); 
		
		File tarDir=new File("D:/test/src/Test/bin//");
		System.out.println("name is :"+ tarDir.getName());
		System.out.println("parent is:"+tarDir.getParent());
	}
	
	
	
	
	
	
	
	

}
