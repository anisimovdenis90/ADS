package com.anisimov.denis.ads.HomeWork1;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = { 8, 0, 4, 7, 3, 7, 10, 12, -3 };
        System.out.println("Было");
        System.out.println(Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);
        System.out.println("Стало");
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0) {
            return;//завершить выполнение если длина массива равна 0
        }

        if (low >= high) {
            return;//завершить выполнение если уже нечего делить
        }

        // выбрать опорный элемент
        final int base = array[low + (high - low) / 2];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < base) {
                i++;
            }

            while (array[j] > base) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
}
