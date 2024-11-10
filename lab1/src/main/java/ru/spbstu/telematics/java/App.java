package ru.spbstu.telematics.java;

import java.io.File;

public class App {
    public static void main(String[] args) {
        String path = (args.length > 0) ? args[0] : ".";

        File directory = new File(path);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                }
            } else {
                System.out.println("Ошибка при чтении содержимого директории.");
            }
        } else {
            System.out.println("Директория не существует или путь указан неверно.");
        }
    }
}
