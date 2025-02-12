package ru.spbstu.telematics.java;

import java.util.Random;

/* 
 * Симулирует нагреватель, установленный в комнате. Может изменять поля комнаты, 
 * а именно - увеличивать температуру в ней.
 */
public class Heater implements Runnable {
    private Room room;

    public Heater(Room room) {
        this.room = room;
    }

    private volatile boolean isOn;

    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    private Random random = new Random();
    private double temperatureMaxStep = 0.25;
    private long maxStepTimeMs = 500;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (isOn)
                room.adjustTemperature(random.nextDouble() * temperatureMaxStep);

            Utils.sleepRandomTime((long) (maxStepTimeMs * 0.5), maxStepTimeMs);
        }
    }
}
