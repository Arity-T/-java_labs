package ru.spbstu.telematics.java;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class MyTreeSet<E extends Comparable<E>> implements Iterable<E> {
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
    private int size;

    public MyTreeSet() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException("Нельзя добавить null в TreeSet!");
        }

        if (root == null) {
            root = new Node(element);
            size++;
            return true;
        }

        Node currentNode = root;
        Node parentNode = null;

        while (currentNode != null) {
            int cmp = element.compareTo(currentNode.value);
            if (cmp == 0) return false;
            parentNode = currentNode;
            currentNode = cmp < 0 ? currentNode.left : currentNode.right;
        }

        Node newNode = new Node(element);
        
        if (element.compareTo(parentNode.value) < 0) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }

        size++;
        return true;
    }

    public boolean contains(E element) {
        if (element == null) {
            throw new NullPointerException("Нельзя добавить null в TreeSet!");
        }
        
        return contains(root, element);
    }
    
    private boolean contains(Node node, E element) {
        if (node == null) return false;

        int cmp = element.compareTo(node.value);
        if (cmp == 0) return true;
        return cmp < 0 ? contains(node.left, element) : contains(node.right, element);
    }

    public boolean remove(E element) {
        if (element == null) {
            throw new NullPointerException("Null элементы не допускаются");
        }
        if (!contains(element)) return false;
        root = remove(root, element);
        size--;
        return true;
    }

    private Node remove(Node node, E element) {
        if (node == null) return null;
        int cmp = element.compareTo(node.value);
        if (cmp < 0) {
            node.left = remove(node.left, element);
        } else if (cmp > 0) {
            node.right = remove(node.right, element);
        } else {
            // Узел для удаления найден
            if (node.left == null && node.right == null) {
                // Если он является листом, то просто удаляем его
                return null;
            } else if (node.left == null) {
                // Если у него нету левого поддерева, то заменяем правым узлом
                return node.right;
            } else if (node.right == null) {
                // Если у него нету правого поддерева, то заменяем левым узлом
                return node.left;
            } else {
                // Если есть оба поддерева, то заменяем самым наименьшим элементом правого поддерева
                Node successor = minValueNode(node.right);
                node.value = successor.value;
                node.right = remove(node.right, successor.value);
            }
        }
        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

	@Override
	public Iterator<E> iterator() {
        return new MyTreeSetIterator();
	}

    private class MyTreeSetIterator implements Iterator<E> {
        // На вершине стека всегда будет лежать наименьший элемент в дереве
        Stack<Node> nodeStack = new Stack<>();

        MyTreeSetIterator() {
            pushAllLeftToStack(root);
        }

        void pushAllLeftToStack(Node current) {
            if (current == null) return;
            nodeStack.push(current);
            pushAllLeftToStack(current.left);
        }
        
		@Override
		public boolean hasNext() {
            return nodeStack.size() != 0;
		}

		@Override
		public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            Node minNode = nodeStack.pop();

            if (minNode.right != null) {
                pushAllLeftToStack(minNode.right);
            }

            return minNode.value;
		}
    }
}
