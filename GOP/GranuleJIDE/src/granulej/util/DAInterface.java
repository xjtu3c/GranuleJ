package granulej.util;
public interface DAInterface<K,V> {

	Node<K, V> getData(Object key);
	void insertData(Node<K,V> node);
}
