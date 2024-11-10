package ru.spbstu.telematics.java;

import java.io.File;

public class App {
    public static void main(String[] args) {
        String path = (args.length > 0) ? args[0] : ".";

        File filePath = new File(path);

        if (filePath.exists()) {
            if (filePath.isFile()) {
                System.out.println(filePath.getPath());
            } else if (filePath.isDirectory()) {
                File[] files = filePath.listFiles();

                if (files != null) {
                    for (File file : files) {
                        System.out.println(file.getName());
                    }
                } else {
                    System.out.println("Ошибка при чтении содержимого директории.");
                }
            }
        } else {
            System.out.println("Директория или файл не существует или путь указан неверно.");
        }
    }
}
