import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ActGranuleManagement implements Runnable {

	private GranuleServer granuleServer;

	public ActGranuleManagement(GranuleServer gServer) {
		this.granuleServer = gServer;
	}

	// 负责个体的状态管理
	public void run() {
//		while (true) {
//			// 获取所有活动个体
////			Map<String, ActiveIndividual> activeGranule = granuleServer.getActive();
//			List<String> UnActiveList = new ArrayList<String>();
//			ActiveIndividual ind = null;
//			Date current = null;
//			for (String key : activeGranule.keySet()) {
//				ind = activeGranule.get(key);
//				current = new Date();
//				if (current.getTime() - ind.getStartTime().getTime() > 1000 * 60 * 5) {
//					UnActiveList.add(key);
//				}
//			}
//			activeGranule.remove(UnActiveList);
//		}
	}
}
