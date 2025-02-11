package ru.spbstu.telematics.java;

import java.util.Locale;

public class App {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        Room room = new Room();
        Settings settings = new Settings(28, 0.4);
        Controller controller = new Controller(room, settings);
        Thread controllerThread = new Thread(controller);
        controllerThread.start();
    }
}
