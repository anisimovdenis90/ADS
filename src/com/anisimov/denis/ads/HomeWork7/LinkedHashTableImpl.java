package com.anisimov.denis.ads.HomeWork7;

import java.util.LinkedList;

public class LinkedHashTableImpl<K, V> implements HashTable<K, V> {

    private final LinkedList<Node<K, V>>[] data;
    private int size;

    public LinkedHashTableImpl(int capacity) {
        this.data = new LinkedList[capacity];
        for (int i = 0; i < data.length; i++) {
            data[i] = new LinkedList<>();
        }
    }

    @Override
    public boolean put(K key, V value) {
        int index = hash(key);

        for (LinkedList<Node<K, V>> nodeLinkedList : data) {
            if (!nodeLinkedList.isEmpty()) {
                for (Node<K, V> node : nodeLinkedList) {
                    if (node.getKey().equals(key)) {
                        node.setValue(value);
                        return true;
                    }
                }
            }
        }

        Node<K, V> newNode = new Node<>(key, value);
        data[index].add(newNode);
        size++;
        return true;
    }

    private int hash(K key) {
        return key.hashCode() % data.length;
    }

    @Override
    public V get(K key) {
        int index = hash(key);

        if (!data[index].isEmpty()) {
            for (Node<K, V> node : data[index]) {
                if (node.getKey().equals(key)) {
                    return node.getValue();
                }
            }
        }

        return null;
    }

    @Override
    public V remove(K key) {
        int index = hash(key);

        for (int i = 0; i <= data[index].size(); i++) {
            if (!data[index].isEmpty()) {
                if (data[index].get(i).getKey().equals(key)) {
                    V value = data[index].get(i).getValue();
                    data[index].remove(i);
                    return value;
                }
            }
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println("----------");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%d = %s%n", i, data[i]);
        }
        System.out.println("----------");
    }

    static class Node<K, V> implements Entry<K, V> {

        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
