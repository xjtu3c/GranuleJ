package granulej.lang;

import java.io.File;

public class GranuleLoader {
	
	private String repositories[] = new String[0];
    
	public String[] findRepositories() {
          return repositories.clone();
    }
	
	public String[] getRepositories(){
	   return repositories.clone();
	}
	
	public String getRepositoriesString() {
        StringBuilder sb=new StringBuilder();
        for( int i=0; i<repositories.length ; i++ ) {
            sb.append( repositories[i]).append(":");
        }
        return sb.toString();
    }
	
	public void addRepository(String repository) {
		
	    for(int i=0; i< repositories.length; i++)
	    {
	    	if(repository.equals(repositories[i]))
	         return ;	
	    }
	    
	    String result[] =new String[repositories.length+1];
	    for(int i=0; i< repositories.length; i++)
	      result[i]=repositories[i];
        result[repositories.length]=repository;
        repositories=result;        
    }
	
	//粒的装载，解决不同路径的相同类的类的重载
	public static Class loadGranule(String gname)
	{
	  Class clazz = null;
	  String curPath=IndividualInfo.getInstance().getWorkDirectory();
	  try {
		  FileSystemClassLoader newloader = new FileSystemClassLoader(curPath);			
		  clazz = newloader.loadClass(gname);
		  newloader.SearchAndCache(gname, clazz);		 
		  return clazz;
		} catch (ClassNotFoundException e) {				
		   e.printStackTrace();
		}
		return clazz;
     }	
}
