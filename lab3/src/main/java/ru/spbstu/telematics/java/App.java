package ru.spbstu.telematics.java;

import java.util.Locale;

public class App {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        Room room = new Room();
        Thread roomThread = new Thread(room);
        Settings settings = new Settings(room, 28, 0.4);
        Thread settingsThread = new Thread(settings);
        Controller controller = new Controller(room, settings);
        Thread controllerThread = new Thread(controller);

        roomThread.start();
        settingsThread.start();
        controllerThread.start();
    }
}
