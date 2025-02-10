package ru.spbstu.telematics.java;

import java.util.Random;

/* 
 * Симулирует работу сенсоров, установленных в комнате.
 */
public class Sensor implements Runnable {
    private Room room;

    public Sensor(Room room) {
        this.room = room;
    }
    
    // Температура
    private volatile double temperature;

    public double getTemperature() {
		return temperature;
	}

    // Влажность
	private volatile double humidity;

    public double getHumidity() {
		return humidity;
	}

	// Частота считывания значений с сенсоров
    private long updateIntervalMs = 1000;

    // Параметры произвольной ошибки измерений сенсоров
    private Random random = new Random();
    private double maxTemperatureError = 0.5;
    private double maxHumidityError = 0.02;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            temperature = room.getTemperature() + (random.nextDouble() - 0.5) * 2 * maxTemperatureError;
            humidity = room.getHumidity() + (random.nextDouble() - 0.5) * 2 * maxHumidityError;

            try {
                Thread.sleep(updateIntervalMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
