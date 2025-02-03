package ru.spbstu.telematics.java;


public class MyTreeSet<E extends Comparable<E>> {
    // Класс для представления узла дерева
    private class Node {
        E value;
        Node left;
        Node right;

        Node(E value) {
            this.value = value;
        }
    }

    private Node root;

    public MyTreeSet() {
        root = null;
    }

    public void add(E element) {
        if (root == null) {
            root = new Node(element);
            return;
        }

        Node currentNode = root;
        Node parentNode = null;

        while (currentNode != null) {
            int cmp = element.compareTo(currentNode.value);
            if (cmp == 0) {
                return;
            } 
            parentNode = currentNode;
            currentNode = cmp < 0 ? currentNode.left : currentNode.right;
        }

        Node newNode = new Node(element);
        
        if (element.compareTo(parentNode.value) < 0) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }
    }
}
