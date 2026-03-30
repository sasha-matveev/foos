package org.am.t.jv;

import java.util.HashMap;
import java.util.Map;

public class LruCache<K, V> {

    private final class Node {
        K key;
        V value;
        Node prev;
        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Object lock = new Object();
    private final Map<K, Node> map = new HashMap<>();
    private final int capacity;

    private Node head; // least recently used
    private Node tail; // most recently used

    public LruCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be > 0");
        }
        this.capacity = capacity;
    }

    public V get(K key) {
        synchronized (lock) {
            Node node = map.get(key);
            if (node == null) {
                return null;
            }
            moveToTail(node);
            return node.value;
        }
    }

    public void put(K key, V value) {
        synchronized (lock) {
            Node existing = map.get(key);
            if (existing != null) {
                existing.value = value;
                moveToTail(existing);
                return;
            }

            Node node = new Node(key, value);
            appendToTail(node);
            map.put(key, node);

            if (map.size() > capacity) {
                evictHead();
            }
        }
    }

    private void moveToTail(Node node) {
        if (node == tail) {
            return;
        }
        unlink(node);
        appendToTail(node);
    }

    private void appendToTail(Node node) {
        node.prev = tail;
        node.next = null;

        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }

        tail = node;
    }

    private void unlink(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }

        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }

        node.prev = null;
        node.next = null;
    }

    private void evictHead() {
        if (head == null) {
            return;
        }

        Node node = head;
        unlink(node);
        map.remove(node.key);
    }
}
