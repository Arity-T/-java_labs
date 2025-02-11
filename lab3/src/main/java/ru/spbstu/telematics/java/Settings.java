package ru.spbstu.telematics.java;

import java.util.Random;

/* 
 * Симулирует переодическое изменение настроек пользователем. 
 */
public class Settings implements Runnable {
    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    private double humidity;

    public double getHumidity() {
        return humidity;
    }

    public Settings(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // Параметры произвольного изменения настроек температуры и влажности в комнате
    private Random random = new Random();
    private double temperatureMaxStep = 10;
    private double humidityMaxStep = 0.15;
    private long maxStepTimeMs = 20000;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            temperature += (random.nextDouble() - 0.5) * 2 * temperatureMaxStep;
            humidity += (random.nextDouble() - 0.5) * 2 * humidityMaxStep;

            Utils.sleepRandomTime((long) (maxStepTimeMs * 0.5), maxStepTimeMs);
        }
    }
}
