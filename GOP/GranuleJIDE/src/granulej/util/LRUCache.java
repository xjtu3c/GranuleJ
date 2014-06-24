package granulej.util;

import java.util.*;

public class LRUCache<K,V> {
    private final int cacheSize;
    private Hashtable<K, Entry<K,V>> nodes;
    private int currentSize;
    private Entry<K,V> first;
    private Entry<K,V> last;

    public LRUCache(int i)
    {
        currentSize = 0;
        cacheSize = i;
        nodes = new Hashtable<K, Entry<K,V>>(i);
    }  
    
   public synchronized void addElement(K key, V value)
    { 
	    Entry<K,V> node = nodes.get(key);	  
        if (node == null)
        {
            if (currentSize >= cacheSize)
            {
                nodes.remove(last.key);
                removeLast();
            } 
            else
                currentSize++;
            node = new Entry<K,V>();
        }
        node.value = value;
        moveToHead(node);
        nodes.put(key, node);        
      }  
    

    private synchronized void moveToHead(Entry<K,V> node)
    {
        if (node == first)
            return;
        if (node.prev != null)
            node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        if (last == node)
            last = node.prev;
        if (first != null)
        {
            node.next = first;
            first.prev = node;
        }
        first = node;
        node.prev = null;
        if (last == null)
            last = first;

    }

    private synchronized void removeLast()
    {
        if (last != null)
        {
            if (last.prev != null)
                last.prev.next = null;
            else
                first = null;
            last = last.prev;
        }
    }

    public synchronized Entry<K,V> getElement(K key)
    {
        Entry<K,V> node = nodes.get(key);       
        if (node != null)
        {
            moveToHead(node);           
            return node;
        } else
            return null;
    }

    public boolean isExist(K key)
    {
        Entry<K,V> node = nodes.get(key);
        if (node != null)
            return true;
        return false;
    }

    public int size()
    {
        return currentSize;
    }
    public int capacity()
    {
        return cacheSize;
    }

    public void clear()
    {
        first = null;
        last = null;
        currentSize = 0;
    }
   
}