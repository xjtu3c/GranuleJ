package granulej.lang;

public class Context {
	private String name;

	private String value;

	private String modifiers;

	private String type;
	
	public Context(String name, String value)
	{
	   this.name=name;
	   this.value=value;
	}

	public Context(String name, String value, String modifiers) {
		this.name = name;
		this.value = value;
		this.modifiers = modifiers;
	}

	public String getModifiers() {
		return modifiers;
	}

	public void setModifiers(String modifiers) {
		this.modifiers = modifiers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
