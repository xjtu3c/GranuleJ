import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;
import java.io.*;

public class Count {
	private Long result;
	public int getInputData() {
		Properties props = new Properties();
		try {
			String filePath = "E:\\client\\num.properties";
                               InputStream fis = new FileInputStream(filePath);
                                props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.valueOf(props.getProperty("num"));
	}

	public void countResult(int a) {
		System.out.println("Default Caculator");
	}

	public void disPlayResult() {
		System.out.println("Default Display");
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

}
