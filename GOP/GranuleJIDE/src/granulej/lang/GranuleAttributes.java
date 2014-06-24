package granulej.lang;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class GranuleAttributes {
    
	   protected long contentLength = -1;
	   protected Attributes attributes = null;
	   public static final String CONTENT_LENGTH = "getcontentlength";
	   
	   public GranuleAttributes(Attributes attributes) {
	        this.attributes = attributes;
	    }
	   
	   public long getContentLength() {
		        if (contentLength != -1L)
		            return contentLength;
		        
		        if (attributes != null) {
		            Attribute attribute = attributes.get(CONTENT_LENGTH);
		            if (attribute != null) {
		                try {
		                    Object value = attribute.get();
		                    if (value instanceof Long) {
		                        contentLength = ((Long) value).longValue();
		                    } else {
		                        try {
		                            contentLength = Long.parseLong(value.toString());
		                        } catch (NumberFormatException e) {
		                           e.printStackTrace();
		                        }
		                    }
		                } catch (NamingException e) {
		                   e.printStackTrace();
		                }
		            }
		        }
		        return contentLength;
		    }
	   
	   public void setContentLength(long contentLength) {
	        this.contentLength = contentLength;
	        if (attributes != null)
	            attributes.put(CONTENT_LENGTH, Long.valueOf(contentLength));
	    }
}
