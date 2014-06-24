import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class UpdateBrocastThread implements Runnable {
	private Map<String, String> updateMap;
	private Map<String, String> portMap;

	public UpdateBrocastThread(Map<String, String> uMap, Map<String, String> pMap) {
		this.updateMap = uMap;
		this.portMap = pMap;
	}

	public void run() {
		Iterator<Entry<String, String>> iter = updateMap.entrySet().iterator();
		String ip = "";
		String contextStr = "";
		String port = "";
		Entry<String, String> entry = null;
		try {
			while (iter.hasNext()) {
				entry = (Entry<String, String>) iter.next();
				ip = (String) entry.getKey();
				contextStr = (String) entry.getValue();
				port = portMap.get(ip);
				Socket sock = new Socket(ip, Integer.valueOf(port));
				PrintWriter pWriter = new PrintWriter(sock.getOutputStream());
				pWriter.print(ConstantVariable.CONTEXT_UPDATE);
				pWriter.print(ConstantVariable.CONTEXT_UPDATE_FROM_SERVER);
				pWriter.print(contextStr);
				pWriter.flush();
				pWriter.close();
				sock.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
