package com.anisimov.denis.ads.HomeWork3;

public interface LinkedList<E> extends Iterable<E> {

    /**
     * O(1)
     * add element to the beginning of list
     * @param value
     */
    void insertFirst(E value);

    /**
     * O(1)
     * @return remove element from the beginning of list
     */
    E removeFirst();

    /**
     * O(n)
     * @param value
     * @return
     */
    boolean remove(E value);

    /**
     * O(n)
     * @param value
     * @return
     */
    boolean contains(E value);

    int size();

    Node<E> firstElement();
    void setFirstElement(Node<E> node);

    boolean isEmpty();

    void display();

    E getFirst();

    class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
