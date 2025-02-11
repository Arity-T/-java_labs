package ru.spbstu.telematics.java;

import java.util.Random;

/* 
 * Симулирует вентилятор, установленный в комнате. Может изменять поля комнаты, 
 * а именно - уменьшать влажность в ней.
 */
public class Fan implements Runnable {
    private Room room;

    public Fan(Room room) {
        this.room = room;
    }

    private volatile boolean isOn;

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    private Random random = new Random();
    private double humidityMaxStep = 1;
    private long maxStepTimeMs = 3000;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (isOn)
                room.adjustHumidity(-random.nextDouble() * humidityMaxStep);

            Utils.sleepRandomTime((long) (maxStepTimeMs * 0.5), maxStepTimeMs);
        }
    }
}
