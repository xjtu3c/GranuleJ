package granulej.util;

public class Entry<K,V>
{
    Entry<K,V> prev;
    Entry<K,V> next;
    V value;
    K key;
    
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
    public Entry() {}

	public Entry(K key, V value) {
	    this.key = key;
	    this.value = value;
    }	
}
