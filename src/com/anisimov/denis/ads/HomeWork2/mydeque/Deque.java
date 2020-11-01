package com.anisimov.denis.ads.HomeWork2.mydeque;

import com.anisimov.denis.ads.HomeWork2.myqueue.Queue;

public interface Deque<E> extends Queue<E> {

    boolean insertLeft(E value);
    boolean insertRight(E value);
    E removeLeft();
    E removeRight();
}
