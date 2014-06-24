

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GopHashMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 1L;

	public static GopHashMap<String, List<String>> chm;

	// 默认缓存大小为6
	public static int maxSize = 5;

	public static int curSize = 0;

	public GopHashMap() {

	}

	public static GopHashMap<String, List<String>> getInstance() {
		chm = new GopHashMap<String, List<String>>();
		return chm;
	}

	// map为对象放入时间的hash;每插入一条数据也要相应插入一个对象时间戳，
	public void put(String key, String value) {
		List<String> list = new ArrayList<String>();
		if (chm.get(key) == null) {
			list.add(value);
			chm.put(key, list);
			curSize = curSize + 1;
		} else {
			list = chm.get(key);
			if (!list.contains(value)) {
				list.add(value);
				chm.put(key, list);
			}
		}
	}

	// 当实体数据大小来调整缓存的大小；
	public void resize(HashMap<String, List<String>> map,boolean flag) {
		int size = map.size();
		if (size >= 30 && maxSize == 5) {
			maxSize = 7;
		}
	}

	// 每删除一条数据都要删除对象时间戳，
	public void remove(String key, boolean flag) {
		chm.remove(key);
		if (curSize > 0) {
			curSize = curSize - 1;
		}
	}

	public int size() {
		return curSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int mSize) {
		maxSize = mSize;
	}
}
