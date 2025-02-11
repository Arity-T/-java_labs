package ru.spbstu.telematics.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class HeaterTests {
    Room room;
    double initialTemperature;
    double initialHumidity;
    Heater heater;
    Thread heaterThread;

    @BeforeEach
    public void setUp() {
        room = new Room();
        initialTemperature = room.getTemperature();
        initialHumidity = room.getHumidity();
        heater = new Heater(room);
        heaterThread = new Thread(heater);
    }

    /* 
     * Проверяет, что включенный нагреватель увеличивает температуру в комнате 
     * и при этом не изменяет влажность.
     */
    @Test
    public void testHeaterOn() throws InterruptedException {
        heater.turnOn();
        heaterThread.start();

        Thread.sleep(5000);

        assertEquals(initialHumidity, room.getHumidity());
        assertTrue(initialTemperature < room.getTemperature());

        heaterThread.interrupt();
        heaterThread.join();
    }

    
    /* 
     * Проверяет, что выключенный нагреватель не изменяет температуру и влажность
     * в комнате. 
     */
    @Test
    public void testHeaterOff() throws InterruptedException {
        heaterThread.start();

        Thread.sleep(5000);

        assertEquals(initialHumidity, room.getHumidity());
        assertEquals(initialTemperature, room.getTemperature());

        heaterThread.interrupt();
        heaterThread.join();
    }
}
