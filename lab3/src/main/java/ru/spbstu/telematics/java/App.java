package ru.spbstu.telematics.java;

public class App {
    public static void main(String[] args) {
        Room room = new Room();
        Settings settings = new Settings(28, 0.4);
        Controller controller = new Controller(room, settings);
        Thread controllerThread = new Thread(controller);
        controllerThread.start();
    }
}
