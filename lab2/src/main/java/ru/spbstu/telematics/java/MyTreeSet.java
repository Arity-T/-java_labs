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
}
