package granulej.lang;

public interface Reloader {

	public void addRepository(String repository);
	
	public String[] findRepositories();
	
	public boolean modified();

}
