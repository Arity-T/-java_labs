package ru.spbstu.telematics.java;

public class App 
{
    public static void main( String[] args )
    {
        MyTreeSet<Integer> tree = new MyTreeSet<>();
        System.out.println(tree.add(10));
        System.out.println(tree.add(20));
        System.out.println(tree.add(10));

        System.out.println("Содержимое дерева:");
        for (Integer val: tree) {
            System.out.println(val);
        }
    }
}
