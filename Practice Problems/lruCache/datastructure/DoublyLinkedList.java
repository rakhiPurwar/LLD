package lruCache.datastructure;

import lruCache.model.Node;

public class DoublyLinkedList<K,V>{
    private final Node<K,V> head;
    private final Node<K,V> tail;

    public DoublyLinkedList() {
        this.head = new Node<>(null,null);
        this.tail = new Node<>(null,null);
        head.next = tail;
        tail.prev = head;
    }

    public void AddAtHead(Node<K,V> node){
        removeNode(node);
        addAtHead(node);
    }

   public void addAtHead(Node<K,V> node) {
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    public void removeNode(Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public Node removeFromTail(){
        if(tail.prev == head ) return null;
        Node<K,V> node = tail.prev;
        removeNode(node);
        return node;
    }

    public Node<K, V> getHead() {
        return head;
    }

    public Node<K, V> getTail() {
        return tail;
    }
}
