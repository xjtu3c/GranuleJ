package granulej.lang;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Resource {
	
	 public Resource() {
	      
	    }	    
	    public Resource(InputStream inputStream) {
	        setContent(inputStream);
	    }	    
	    public Resource(byte[] binaryContent) {
	        setContent(binaryContent);
	    }
	    protected byte[] binaryContent = null;
	    protected InputStream inputStream = null;
	    
	    public InputStream streamContent() throws IOException {
	        if (binaryContent != null) {
	            return new ByteArrayInputStream(binaryContent);
	        }
	        return inputStream;
	    }
	   
	    public byte[] getContent() {
	        return binaryContent;
	    }

	    public void setContent(InputStream inputStream) {
	        this.inputStream = inputStream;
	    }
	    public void setContent(byte[] binaryContent) {
	        this.binaryContent = binaryContent;
	    }	    
}
