package granulej.util;

public class Node<K,V> {

	public Node<K,V> prev;
	public Node<K,V> next;
	public K key;
	public V data;

	public Node() {}

	public Node(K key, V data) {
		this.key = key;
		this.data = data;
	}
}