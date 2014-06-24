
import java.io.IOException;
import java.util.Properties;
import java.io.*;

public class Site {
	private int num;
	
	public void getdata(){
		Properties props = new Properties();
		try {
		String filePath = "E:\\client\\num.properties";
                     InputStream fis = new FileInputStream(filePath);
                     props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setNum(Integer.valueOf(props.getProperty("num")));
	}

	public void work() {
		System.out.println("Default work");
	}

	public void show() {
		System.out.println("Default show");
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
