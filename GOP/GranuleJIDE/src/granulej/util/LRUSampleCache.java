package granulej.util;

import java.util.HashMap;
import java.util.Map;

public class LRUSampleCache<K,V> {
	private Node<K, V> head;
	private Node<K, V> tail;
	private final int maxSize;
	private Map<K, Node<K,V>> map = null;
	private DAInterface<K, V> daoInterface = new DAInterfaceImpl();

	public LRUSampleCache(int maxSize) {
		this.maxSize = maxSize; 
		head = new Node<K, V>(null, null);
		tail = new Node<K, V>(null, null);
		head.next = tail;
		tail.prev = head;
		map = new HashMap<K, Node<K, V>>(maxSize);
	}

	public Object get(Object key) {
		Node<K, V> node = map.get(key);
		if (node == null){
			node = daoInterface.getData(key);
			if(node==null)
				return null;
			addNodeToListAtHead(node);
			return node.data;
		}
		if (map.size() == 1)
			return node.data;
		removeNodeFromList(node);
		addNodeToListAtHead(node);
		return node.data;
	}
	
	public void put(K key, V data) {
		if (maxSize <= 0)
			return;
		Node<K, V> node = map.get(key);
		if (node != null) {
			removeNodeFromList(node);
			addNodeToListAtHead(node);
			node.data = data;
		} else {
			node = new Node<K, V>(key, data);
			map.put(key, node);
			addNodeToListAtHead(node);
			if (map.size() > maxSize) {
				map.remove(tail.prev.key);
				removeNodeFromList(tail.prev);
			}
		}
	}
	
	private synchronized void addNodeToListAtHead(Node<K, V> node) {
		node.next = head.next;
		node.prev = head;
		head.next.prev = node;
		head.next = node;
	}
	
	private synchronized void removeNodeFromList(Node<K, V> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
}