package granulej.gui.datastructure;

public class ExecuteUnit {
	public Object newInstance=null;
	public Class newClass=null;
	public String methodName=null;
	public Class [] argsTypes=null;
	public Object [] args=null;
	
	public ExecuteUnit(Object newInstance,Class newClass)
	{
		this.newInstance=newInstance;
		this.newClass=newClass;
		this.methodName=methodName;
		this.argsTypes=argsTypes;
		this.args=args;
	}
}
