package com.anisimov.denis.ads.HomeWork4;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>(Arrays.asList(
                new Item("Книга", 600, 1),
                new Item("Бинокль", 5000, 2),
                new Item("Аптечка", 1500, 4),
                new Item("Ноутбук", 40000, 2),
                new Item("Котелок", 500, 1)));
        Knapsack knapsack = new Knapsack();
        getBestKnapsack(items, knapsack);
        System.out.println(knapsack);
    }

    public static void getBestKnapsack(ArrayList<Item> items, Knapsack knapsack) {
        if (items.isEmpty()) {
            return;
        }

        knapsack.addItems(items);

        for (int i = 0; i < items.size(); i++) {
            ArrayList<Item> copy = new ArrayList<>(items);
            copy.remove(i);
            getBestKnapsack(copy, knapsack);
        }
    }

    private static class Knapsack {

        private static final int MAX_WEIGHT = 5;

        private int maxWeight;
        private int currentWeight;
        private int currentPrice;
        private List<Item> items;

        public Knapsack() {
            this(MAX_WEIGHT);
        }

        public Knapsack(int maxWeight) {
            this.maxWeight = maxWeight;
            items = new ArrayList<>();
        }

        public void addItems(Collection<Item> collection) {
            int collectionPrice = 0, collectionWeight = 0;
            for (Item item : collection) {
                collectionPrice += item.getPrice();
                collectionWeight += item.getWeight();
            }
            if (collectionWeight <= maxWeight) {
                if (currentPrice < collectionPrice) {
                    currentPrice = collectionPrice;
                    currentWeight = collectionWeight;
                    items.clear();
                    items.addAll(collection);
                }
            }
        }

        @Override
        public String toString() {
            return "Knapsack{" +
                    "maxWeight=" + maxWeight +
                    ", currentWeight=" + currentWeight +
                    ", currentPrice=" + currentPrice +
                    ", items=" + items +
                    '}';
        }
    }

    private static class Item {
        private String name;
        private int price;
        private int weight;

        public Item(String name, int price, int weight) {
            this.name = name;
            this.price = price;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    ", weight=" + weight +
                    '}';
        }
    }
}
