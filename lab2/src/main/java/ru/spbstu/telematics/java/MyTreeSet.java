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

    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException("Нельзя добавить null в TreeSet!");
        }

        if (root == null) {
            root = new Node(element);
            return true;
        }

        Node currentNode = root;
        Node parentNode = null;

        while (currentNode != null) {
            int cmp = element.compareTo(currentNode.value);
            if (cmp == 0) {
                return false;
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

        return true;
    }
}
