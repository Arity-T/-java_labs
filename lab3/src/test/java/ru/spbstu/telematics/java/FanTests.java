package ru.spbstu.telematics.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class FanTests {
    Room room;
    double initialTemperature;
    double initialHumidity;
    Fan fan;
    Thread fanThread;

    @BeforeEach
    public void setUp() {
        room = new Room();
        initialTemperature = room.getTemperature();
        initialHumidity = room.getHumidity();
        fan = new Fan(room);
        fanThread = new Thread(fan);
    }

    /*
     * Проверяет, что включенный вентилятор уменьшает влажность в комнате
     * и при этом не изменяет температуру.
     */
    @Test
    public void testFanOn() throws InterruptedException {
        fan.turnOn();
        fanThread.start();

        Thread.sleep(5000);

        assertEquals(initialTemperature, room.getTemperature());
        assertTrue(initialHumidity > room.getHumidity());

        fanThread.interrupt();
        fanThread.join();
    }

    /*
     * Проверяет, что выключенный вентилятор не изменяет температуру и влажность
     * в комнате.
     */
    @Test
    public void testFanOff() throws InterruptedException {
        fanThread.start();

        Thread.sleep(5000);

        assertEquals(initialHumidity, room.getHumidity());
        assertEquals(initialTemperature, room.getTemperature());

        fanThread.interrupt();
        fanThread.join();
    }
}
