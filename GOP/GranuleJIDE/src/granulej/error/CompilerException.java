package granulej.error;

public class CompilerException extends Exception {

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
		
		System.out.println("Compiler Error");
	}

}
