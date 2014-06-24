import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

granule DIS_SC_AA(Count) expands Count_SC_FAA{
	external String location;
	{
		return location.equals("c");
	}	
}

class Count within DIS_SC_AA{
	//模拟化显示--console显示，并写入文件
	public void disPlayResult() {
		System.out.println("Analogy:display the result:" + getResult());
		Properties prop = new Properties();
		try {
			String filePath = "E:\\client\\num.properties";
			InputStream fis = new FileInputStream(filePath);
			prop.load(fis);
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty("result", String.valueOf(getResult()));
			prop.store(fos, "Update '" + "result" + "' value");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
