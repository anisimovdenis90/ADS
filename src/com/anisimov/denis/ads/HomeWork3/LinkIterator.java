package com.anisimov.denis.ads.HomeWork3;

public class LinkIterator<E> {

    private LinkedList.Node<E> current;
    private LinkedList.Node<E> previous;
    private LinkedList<E> list;

    public LinkIterator(LinkedList<E> list) {
        this.list = list;
        this.reset();
    }

    public void reset() {
        current = list.firstElement();
        previous = null;
    }

    public boolean atEnd() {
        return (current.next == null);
    }

    public void nextLink() {
        if (current.next == null) {
            reset();
            return;
        }
        previous = current;
        current = current.next;
    }

    public E getCurrent() {
        return current.item;
    }

    public void insertAfter(E value) {
        LinkedList.Node<E> newNode = new LinkedList.Node<>(value, null);
        if (list.isEmpty()) {
            list.setFirstElement(newNode);
            current = newNode;
        } else {
            newNode.next = current.next;
            current.next = newNode;
            nextLink();
        }
    }

    public void insertBefore(E value) {
        LinkedList.Node<E> newNode = new LinkedList.Node<>(value, null);
        if (previous == null) {
            newNode.next = list.firstElement();
            list.setFirstElement(newNode);
            reset();
        } else {
            newNode.next = previous.next;
            previous.next = newNode;
            current = newNode;
        }
    }

    public E deleteCurrent() {
        E data = current.item;
        if (previous == null) {
            list.setFirstElement(current.next);
            reset();
        } else {
            previous.next = current.next;
            if(atEnd()) {
                reset();
            } else {
                current = current.next;
            }
        }
        return data;
    }
}
