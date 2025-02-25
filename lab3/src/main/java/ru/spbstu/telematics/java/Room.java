package ru.spbstu.telematics.java;

import java.util.Random;

/* 
 * Симулирует физические процессы, протекающие в команте.
 */
public class Room implements Runnable {
    static private int roomCounter;

    public final String name;

    public Room() {
        roomCounter++;
        this.name = "#" + roomCounter;
    }

    // Температура измеряется в градусах цельсия
    private volatile double temperature = 24.0;

    public double getTemperature() {
        return temperature;
    }

    public synchronized void adjustTemperature(double delta) {
        this.temperature += delta;
    }

    // Относительная влажность в процентах
    private volatile double humidity = 0.5;

    public double getHumidity() {
        return humidity;
    }

    public synchronized void adjustHumidity(double delta) {
        this.humidity += delta;
    }

    // Параметры произвольного изменения температуры и влажности в комнате
    private Random random = new Random();
    private double temperatureMaxStep = 0.12; // примерно 0.5% от средних значений
    private double humidityMaxStep = 0.003;
    private long maxStepTimeMs = 500;

    @Override
    public void run() {
        // Пусть температура и влажность почти произвольно изменяются со временем,
        // но со временем становится немного холоднее (комната остывает), а влажность
        // немного растёт (потому что нужно иногда проветривать).
        while (!Thread.interrupted()) {
            temperature += (random.nextDouble() - 0.6) * 2 * temperatureMaxStep;
            humidity += (random.nextDouble() - 0.4) * 2 * humidityMaxStep;

            Utils.sleepRandomTime((long) (maxStepTimeMs * 0.5), maxStepTimeMs);
        }
    }
}
