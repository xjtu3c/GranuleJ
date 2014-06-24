import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class Utility {

	public static Document loadFromXML(String filename) {
		try {
			File file = new File(filename);
			Document document = null;
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// boolean flag = true;
			// while (flag) {
			// if (file.canRead()) {
			// flag = false;
			document = builder.parse(file);
			// }
			// }
			return document;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void writeToXML(Document doc, String path) {
		try {
			File file = new File(path);
			// boolean flag = true;
			// while (flag) {
			// if (file.canWrite()) {
			// flag = false;
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getInputFromSocket(InputStream inputStream) {
		String path = "";
		char c;
		try {
			DataInputStream ds = new DataInputStream(inputStream);
			while ((c = (char) ds.readUnsignedByte()) != '\n') {
				if (c != '\r')
					path = path + c;
			}
			return path;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取服务器端目录
	public static String getServerDir() {
		return System.getProperty("user.dir") + File.separator + "server" + File.separator;
	}
}
