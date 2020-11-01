package com.anisimov.denis.ads.HomeWork2.mydeque;

import com.anisimov.denis.ads.HomeWork2.myqueue.QueueImpl;

public class DequeIml<E> extends QueueImpl<E> implements Deque<E> {

    public DequeIml(int maxSize) {
        super(maxSize);
    }

    @Override
    public boolean insertLeft(E value) {
        return super.insert(value);
    }

    @Override
    public boolean insertRight(E value) {
        if (isFull()) {
            return false;
        }

        if (tail <= 0) {
            tail = data.length - 1;
        }

        data[tail--] = value;
        size++;
        return true;
    }

    @Override
    public E removeLeft() {
        if (isEmpty()) {
            return null;
        }

        if (head == 0) {
            head = data.length;
        }

        E removedValue = data[--head];
        size--;
        return removedValue;
    }

    @Override
    public E removeRight() {
        return super.remove();
    }
}
