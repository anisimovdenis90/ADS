package com.anisimov.denis.ads.HomeWork1;

import java.util.Random;

public class Main {

    private final static int CAPACITY = 100_000;

    public static void main(String[] args) {
        Random random = new Random();
        ArrayImpl<Integer> array = new ArrayImpl<>(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            array.add(random.nextInt());
        }

        ArrayImpl<Integer> array2 = new ArrayImpl<>(array);
        ArrayImpl<Integer> array3 = new ArrayImpl<>(array);

        startTest(array::sortBubble, "Время пузырьковой сортировки: ");
        startTest(array2::sortSelect, "Время сортировки выбором: ");
        startTest(array3::sortInsert, "Время сортировки вставкой: ");
    }

    public static void startTest(Runnable test, String message) {
        final long startTime = System.currentTimeMillis();
        test.run();
        final long stopTime = System.currentTimeMillis();
        System.out.println(message + (stopTime - startTime));
    }

//    Время пузырьковой сортировки: 40750
//    Время сортировки выбором: 17054
//    Время сортировки вставкой: 11778
}
