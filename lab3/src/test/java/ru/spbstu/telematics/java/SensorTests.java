package ru.spbstu.telematics.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SensorTests {
    /* 
     * Моковый класс комнаты для упрощения тестирования сенсоров.
     */
    private class MockRoom extends Room {
        double temperature;
        double humidity;

        public MockRoom(double temperature, double humidity) {
			this.temperature = temperature;
			this.humidity = humidity;
		}

		@Override
        public double getTemperature() {
            return temperature;
        }

        @Override
        public double getHumidity() {
            return humidity;
        }
    }

    /* 
     * Проверяет, что сенсоры выдают реальную температуру и влажность комнаты в пределах 
     * некоторой погрешности.
     */
    @Test
    public void testSensor() throws InterruptedException {
        double initialTemperature = 30.0;
        double initialHumidity = 0.8;
        Room room = new MockRoom(initialTemperature, initialHumidity);

        Sensor sensor = new Sensor(room);
        Thread thread = new Thread(sensor);
        thread.start();
        
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            assertTrue(Math.abs(initialTemperature - sensor.getTemperature()) <= 1);
            assertTrue(Math.abs(initialHumidity - sensor.getHumidity()) <= 0.05);
        }

        thread.interrupt();
        thread.join();
    }
}
