package com.anisimov.denis.ads.HomeWork3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedListImpl<E> implements LinkedList<E> {

    protected int size;
    protected Node<E> firstElement;

    @Override
    public void insertFirst(E value) {
        Node<E> newNode = new Node<>(value, firstElement);
        firstElement = newNode;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> removedElement = firstElement;
        E data = removedElement.item;
        firstElement = removedElement.next;
        removedElement.next = null;
        removedElement.item = null;
        size--;
        return data;
    }

    @Override
    public boolean remove(E value) {
        Node<E> current = firstElement;
        Node<E> previous = null;
        while (current != null) {
            if(current.item.equals(value)) {
                break;
            }
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        if (current == firstElement) {
            firstElement = firstElement.next;
        } else {
            previous.next = current.next;
        }
        current.next = null;
        current.item = null;
        size--;
        return true;
    }

    @Override
    public boolean contains(E value) {
        Node<E> current = firstElement;
        while (current != null) {
            if (current.item.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Node<E> firstElement() {
        return firstElement;
    }

    @Override
    public void setFirstElement(Node<E> node) {
        firstElement = node;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void display() {
        System.out.println("--------------");
        Node<E> current = firstElement;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
        System.out.println("--------------");
    }

    @Override
    public E getFirst() {
        return firstElement.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int cursor;
            private Node<E> next = firstElement;
            private Node<E> current;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (isEmpty()) {
                    throw new NoSuchElementException();
                }
                current = next;
                next = next.next;
                cursor++;
                return current.item;
            }
        };
    }
}
