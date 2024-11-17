package ru.spbstu.telematics.java;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        String path = (args.length > 0) ? args[0] : ".";
        for (String result : listFiles(path)) {
            System.out.println(result);
        }
    }

    public static List<String> listFiles(String path) {
        File filePath = new File(path);
        List<String> results = new ArrayList<>();

        if (filePath.exists()) {
            if (filePath.isFile()) {
                results.add(filePath.getPath());
            } else if (filePath.isDirectory()) {
                File[] files = filePath.listFiles();

                if (files != null) {
                    for (File file : files) {
                        results.add(file.getName());
                    }
                } else {
                    results.add("Ошибка при чтении содержимого директории.");
                }
            }
        } else {
            results.add("Директория или файл не существует или путь указан неверно.");
        }

        return results;
    }
}

