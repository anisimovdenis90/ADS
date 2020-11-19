package com.anisimov.denis.ads.HomeWork5;

import java.util.Stack;
import java.util.function.Consumer;

public class TreeImpl<E extends Comparable<? super E>> implements Tree<E> {

    private static final int DEFAULT_LEVEL = 30;
    private Node<E> root;
    private int size;
    private int maxLevel;

    public TreeImpl() {
        this(DEFAULT_LEVEL);
    }

    public TreeImpl(int maxLevel) {
        if (maxLevel < 0) {
             throw new IllegalArgumentException("Max level is lower then zero: " + maxLevel);
        }
        this.maxLevel = maxLevel;
    }

    @Override
    public boolean add(E value) {
        Node<E> newNode = new Node<>(value);

        if(isEmpty()) {
            this.root = new Node<>(value);
            size++;
            return true;
        }

        NodeAndParent nodeAndParent = doFind(value);
        Node<E> current = nodeAndParent.current;
        Node<E> parent = nodeAndParent.parent;

        if (current != null) {
            return false;
        }

        if (parent != null && parent.getLevel() < maxLevel && maxLevel > 0) {
            parent.addChild(newNode);
            size++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(E value) {
        return doFind(value).current != null;
    }

    private NodeAndParent doFind(E value) {
        int level = 0;
        Node<E> previous = null;
        Node<E> current = root;

        while (current != null) {
            current.setLevel(++level);
            if (current.getValue().equals(value)) {
                return new NodeAndParent(current, previous);
            }

            previous = current;
            if(current.isRightChild(value)) {
                current = current.getRightChild();
            } else {
                current = current.getLeftChild();
            }
        }

        return new NodeAndParent(null, previous);
    }

    @Override
    public boolean remove(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        Node<E> removedNode = nodeAndParent.current;
        Node<E> parentNode = nodeAndParent.parent;

        if (removedNode == null) {
            return false;
        }

        if (removedNode.isLeaf()) {
            if (removedNode == root) {
                root = null;
            } else if (parentNode.isRightChild(value)) {
                parentNode.setRightChild(null);
            } else {
                parentNode.setLeftChild(null);
            }
        } else if (removedNode.hasOnlyOneChild()) {
            Node<E> child = removedNode.getLeftChild() != null
                    ? removedNode.getLeftChild()
                    : removedNode.getRightChild();

            if (removedNode == root) {
                root = child;
            } else if (parentNode.isRightChild(value)) {
                parentNode.setRightChild(child);
            } else {
                parentNode.setLeftChild(child);
            }
        } else {
            Node<E> successor = getSuccessor(removedNode);
            if (removedNode == root) {
                root = successor;
            } else if (parentNode.isRightChild(value)) {
                parentNode.setRightChild(successor);
            } else {
                parentNode.setLeftChild(successor);
            }

            successor.setLeftChild(removedNode.getLeftChild());
        }
        size--;
        return true;
    }

    private Node<E> getSuccessor(Node<E> removedNode) {
        Node<E> successor = removedNode;
        Node<E> successorParent = null;
        Node<E> current = removedNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removedNode.getRightChild() && successorParent != null) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removedNode.getRightChild());
        }

        return successor;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void traverse(TraverseMode mode, Consumer<E> action) {
        switch (mode) {
            case IN_ORDER:
                inOrder(root, action);
                break;
            case PRE_ORDER:
                preOrder(root, action);
                break;
            case POST_ORDER:
                postOrder(root, action);
                break;
            default:
                throw new IllegalArgumentException("Unknown traverse mode: " + mode);
        }
    }

    private void postOrder(Node<E> current, Consumer<E> action) {
        if (current == null) {
            return;
        }
        postOrder(current.getLeftChild(), action);
        postOrder(current.getRightChild(), action);
        action.accept(current.getValue());
    }

    private void preOrder(Node<E> current, Consumer<E> action) {
        if (current == null) {
            return;
        }
        action.accept(current.getValue());
        preOrder(current.getLeftChild(), action);
        preOrder(current.getRightChild(), action);
    }

    private void inOrder(Node<E> current, Consumer<E> action) {
        if (current == null) {
            return;
        }
        inOrder(current.getLeftChild(), action);
        action.accept(current.getValue());
        inOrder(current.getRightChild(), action);
    }

    @Override
    public void display() {
        Stack<Node<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");
    }

    public boolean isBalanced() {
        return doIsBalanced(root);
    }

    private boolean doIsBalanced(Node<E> node) {
        return (node == null) ||
                doIsBalanced(node.getLeftChild()) &&
                        doIsBalanced(node.getRightChild()) &&
                        Math.abs(height(node.getLeftChild()) - height(node.getRightChild())) <= 1;
    }

    private int height(Node<E> node) {
        return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

    private class NodeAndParent {

        Node<E> current;
        Node<E> parent;

        public NodeAndParent(Node<E> current, Node<E> parent) {
            this.current = current;
            this.parent = parent;
        }
    }
}
