package ru.spbstu.telematics.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTests {
    /*
     * Проверяет, что температура и влажность изменяются со временем.
     */
    @Test
    public void testTemperatureAndHumidityChange() throws InterruptedException {
        Room room = new Room();
        double initialTemperature = room.getTemperature();
        double initialHumidity = room.getHumidity();

        Thread thread = new Thread(room);
        thread.start();
        Thread.sleep(5000);

        assertNotEquals(initialTemperature, room.getTemperature());
        assertNotEquals(initialHumidity, room.getHumidity());

        thread.interrupt();
        thread.join();
    }
}
