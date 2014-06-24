package granulej.lang;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import granulej.util.xmlUtil;


public class SendPacket extends GranulePacket {
	private String md5;
	private String granuleName;
    
    public String getMd5Code()
    {
      return md5;
    }
    public void setMd5Code(String md5)
    {
      this.md5=md5;
    }

    public String getGranuleName(){
    	
    	return granuleName;
    }
    public void setGranuleName(String granulename)
    {
    	this.granuleName=granulename;
    }
    public String getMd5FromConfigFile()
    {
      return getMDCodeFromFile(getGranuleConfigFile(getConfigFile()));
    }
    
    public String getMd5FromConfigFile(String gfile)
    {
    	return getMDCodeFromFile(getGranuleConfigFile(gfile));
    } 
    
    public String getMDCodeFromFile(String gfullfile)
    {
    	Document doc=xmlUtil.load(gfullfile);
    	doc.normalize();
    	Element root=doc.getDocumentElement();
    	return XmlParser.getMd5Code(root);   	
    } 
    
    public String getGranuleConfigFile(String gfile)
    {
       return IndividualInfo.getInstance().getWorkDirectory()+gfile;
    }  
    
}
