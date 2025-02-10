package ru.spbstu.telematics.java;

import java.util.Random;

/* 
 * Симулирует физические процессы, протекающие в команте.
 */
public class Room implements Runnable {
    // Температура измеряется в градусах цельсия
    private volatile double temperature = 24.0;

    public double getTemperature() {
        return temperature;
    }

    // Относительная влажность в процентах
    private volatile double humidity = 0.5;

    public double getHumidity() {
        return humidity;
    }

    // Параметры произвольного изменения температуры и влажности в комнате 
    private Random random = new Random();
    private double temperatureMaxStep = 1;
    private double humidityMaxStep = 0.05;
    private long maxStepTimeMs = 3000;

    @Override
    public void run() {
        // Пусть температура и влажность произвольно изменяются со временем
        while (!Thread.interrupted()) {
            temperature += (random.nextDouble() - 0.5) * 2 * temperatureMaxStep;
            humidity += (random.nextDouble() - 0.5) * 2 * humidityMaxStep;

            try {
                Thread.sleep(getStepTime());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private long getStepTime() {
        // Спим от 0.5 * maxStepTimeMs до maxSteTimeMs миллисекунд
        return (long) (random.nextDouble() * 0.5 + 0.5) * maxStepTimeMs;
    }
}
