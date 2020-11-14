package com.anisimov.denis.ads.HomeWork5;

import java.util.ArrayList;

public class Main {

    private static final int MAX_TREES = 20;
    private static final int MAX_LEVEL = 4;
    private static final int MAX_LEVEL2 = 6;

    private static final int maxRandom = 25;
    private static final int minRandom = -25;
    private static final int maxRandom2 = 100;
    private static final int minRandom2 = -100;

    public static void main(String[] args) {
//        testTree();
//        testRemovedElement();
        calculatePercentOfUnbalancedTrees(MAX_TREES, MAX_LEVEL, maxRandom, minRandom);
        calculatePercentOfUnbalancedTrees(MAX_TREES, MAX_LEVEL2, maxRandom2, minRandom2);
    }

    private static void calculatePercentOfUnbalancedTrees(int maxTrees, int maxLevel, int maxRandom, int minRandom) {
        int unbalancedCounts = 0;
        ArrayList<TreeImpl<Integer>> listOfTrees = new ArrayList<>();
        for (int i = 0; i < maxTrees; i++) {
            listOfTrees.add(new TreeImpl<>(maxLevel));
            for (int j = 0; j <= Math.pow(2, maxLevel) - 1; j++) {
                listOfTrees.get(i).add(rnd(maxRandom, minRandom));
            }
        }
        for (TreeImpl<Integer> tree : listOfTrees) {
            tree.display();
            if (tree.isBalanced()) {
                System.out.println("BALANCED");
            } else {
                unbalancedCounts++;
                System.out.println("UNBALANCED");
            }
        }
        System.out.println("Процент несбалансированных: " + unbalancedCounts / ((double) maxTrees / 100));
    }

    private static int rnd(int maxRandom, int minRandom) {
        int max = maxRandom - minRandom;
        return (int) (Math.random() * ++max) + minRandom;
    }

    private static void testRemovedElement() {
        Tree<Integer> tree = new TreeImpl<>();
        tree.add(60);
        tree.add(25);
        tree.add(66);
        tree.add(15);
        tree.add(5);
        tree.add(20);
        tree.add(45);
        tree.add(30);
        tree.add(55);
        tree.add(32);

        tree.remove(25);
        tree.display();
    }

    private static void testTree() {
        Tree<Integer> tree = new TreeImpl<>();
        tree.add(60);
        tree.add(50);
        tree.add(66);
        tree.add(40);
        tree.add(55);
        tree.add(70);
        tree.add(31);
        tree.add(45);
        tree.add(57);
        tree.add(67);
        tree.add(81);

        System.out.println("Find 70: " + tree.contains(70));
        System.out.println("Find 700: " + tree.contains(700));

//        tree.traverse(Tree.TraverseMode.IN_ORDER, System.out::println);
//        tree.traverse(Tree.TraverseMode.PRE_ORDER, System.out::println);
        tree.traverse(Tree.TraverseMode.POST_ORDER, System.out::println);
    }
}
