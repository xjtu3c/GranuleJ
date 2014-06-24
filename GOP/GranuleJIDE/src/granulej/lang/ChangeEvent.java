package granulej.lang;

public class ChangeEvent {	    
	    private Object source;
	    private int version;

		public Object getSource() {
			return source;
		}

		public void setSource(Object source) {
			this.source = source;
		}

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}

		public ChangeEvent(Object source,int version)
	    {
	       this.source=source;
	       this.version=version;	    	
	    }
	    
}
