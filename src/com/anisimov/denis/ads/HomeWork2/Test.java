package com.anisimov.denis.ads.HomeWork2;

import com.anisimov.denis.ads.HomeWork2.myqueue.Queue;
import com.anisimov.denis.ads.HomeWork2.myqueue.QueueImpl;
import com.anisimov.denis.ads.HomeWork2.mystack.Stack;
import com.anisimov.denis.ads.HomeWork2.mystack.StackImpl;

public class Test {

    public static void main(String[] args) {
        testStack();
        testQueue();
        String testString = "ABCD";
        System.out.println(testString);
        System.out.println(stringInversion(testString));
    }

    private static void testStack() {
        System.out.println("Тестирование стека:");
        Stack<Integer> stack = new StackImpl<>(5);
        System.out.println("Добавляем 1: " + addToStack(stack, 1));
        System.out.println("Добавляем 2: " + addToStack(stack, 2));
        System.out.println("Добавляем 3: " + addToStack(stack, 3));
        System.out.println("Добавляем 4: " + addToStack(stack, 4));
        System.out.println("Добавляем 5: " + addToStack(stack, 5));
        System.out.println("Добавляем 6: " + addToStack(stack, 6));

        System.out.println("Размер стека: " + stack.size());
        System.out.println("Верхний элемент: " + stack.peek());

        System.out.println("Вывод значений из стека по порядку:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.out.println("Тестирование стека закончено.");
    }

    private static boolean addToStack(Stack<Integer> stack, int value) {
        if (!stack.isFull()) {
            stack.push(value);
            return true;
        }
        return false;
    }

    private static void testQueue() {
        System.out.println("Тестирование очереди:");
        Queue<Integer> queue = new QueueImpl<>(5);
        System.out.println("Добавляем 3: " + queue.insert(3));
        System.out.println("Добавляем 5: " + queue.insert(5));
        System.out.println("Добавляем 1: " + queue.insert(1));
        System.out.println("Добавляем 2: " + queue.insert(2));
        System.out.println("Добавляем 6: " + queue.insert(6));
        System.out.println("Добавляем 4: " + queue.insert(4));

        System.out.println("Первый элемент в очереди: " + queue.peekHead());
        System.out.println("Размер очереди: " + queue.size());
        System.out.println("Проверка на заполненность очереди: " + queue.isFull());

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }

    private static String stringInversion(CharSequence text) {
        final StackImpl<Character> stack = new StackImpl<>(text.length());
        final char[] invertChars = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            stack.push(text.charAt(i));
        }
        for (int i = 0; i < text.length(); i++) {
            invertChars[i] = stack.pop();
        }
        return new String(invertChars);
    }
}
