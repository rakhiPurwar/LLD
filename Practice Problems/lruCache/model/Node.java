package lruCache.model;


//Map gives direct access to the DLL node; DLL maintains the usage order.
public class Node<K,V>{
    public K key;
    public V value;
    public Node<K,V> prev, next;

    public Node(K key, V value){
        this.key = key;
        this.value = value;
    }
}
