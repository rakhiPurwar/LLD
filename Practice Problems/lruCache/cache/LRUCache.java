package lruCache.cache;

import lruCache.datastructure.DoublyLinkedList;
import lruCache.model.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class LRUCache<K,V> {
    private final int capacity;
    private final DoublyLinkedList<K,V> list;
    private final Map<K, Node<K,V>> nodeMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.list = new DoublyLinkedList<>();
        this.nodeMap = new HashMap<>();
    }

    public synchronized V get(K key) {
        if (!nodeMap.containsKey(key)) return null;
        Node<K,V> node = nodeMap.get(key);
        list.AddAtHead(node);
        return node.value;
    }

    //chaehc then act
    public synchronized void put(K key, V value) {
        if (nodeMap.containsKey(key)) {
            Node<K,V> node = nodeMap.get(key);
            node.value = value;
            //move to head recently used
            list.AddAtHead(node);
        } else {
            if (nodeMap.size() == capacity) {
                Node<K,V> removedNode = list.removeFromTail();
                if (removedNode != null) {
                    nodeMap.remove(removedNode.key);
                }
            }
            Node<K,V> newNode = new Node<>(key, value);
            list.addAtHead(newNode);
            nodeMap.put(key, newNode);
        }
    }

    @Override
    public String toString(){
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        Node<K,V> current = list.getHead().next;
        while(current != list.getTail()){
            sj.add(current.key + "=" + current.value);
            current = current.next;
        }
        return sj.toString();

    }

}
