package ru.spbstu.telematics.java;

import java.util.Random;

/* 
 * Симулирует переодическое изменение настроек пользователем. 
 */
public class Settings implements Runnable {
    private Room room;

    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    private double humidity;

    public double getHumidity() {
        return humidity;
    }

    public Settings(Room room, double temperature, double humidity) {
        this.room = room;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // Параметры произвольного изменения настроек температуры и влажности в комнате
    private Random random = new Random();
    private double temperatureMaxStep = 6;
    private double humidityMaxStep = 0.10;
    private long maxStepTimeMs = 30000;

    private void log(String string) {
        System.out.printf("[Settings in room %s] %s\n", room.name, string);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Utils.sleepRandomTime((long) (maxStepTimeMs * 0.5), maxStepTimeMs);

            temperature += (random.nextDouble() - 0.5) * 2 * temperatureMaxStep;
            humidity += (random.nextDouble() - 0.5) * 2 * humidityMaxStep;

            log(String.format(
                    "Changed to temperature %.2fC°, humidity %.2f%%",
                    temperature, humidity));
        }
    }
}
