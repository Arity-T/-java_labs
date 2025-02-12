package ru.spbstu.telematics.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class AppTest {

    /**
     * Тест проверяет, что при параллельном запуске всех потоков
     * не возникает взаимной блокировки (deadlock).
     */
    @Test
    void testNoDeadlock() throws InterruptedException {
        Room room = new Room();
        Settings settings = new Settings(room, 28.0, 0.4);
        Controller controller = new Controller(room, settings);
        Thread controllerThread = new Thread(controller);
        Thread roomThread = new Thread(room);

        controllerThread.start();
        roomThread.start();

        // Будем проверять наличие deadlock несколько раз с периодом 1 сек
        // Общая длительность теста 10 секунд
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);

            long[] threadIds = threadBean.findDeadlockedThreads();
            // Если возвращается не null, значит обнаружен deadlock
            assertNull(threadIds, "Обнаружен deadlock.");
        }

        controllerThread.interrupt();
        roomThread.interrupt();

        controllerThread.join();
        roomThread.join();
    }

    /**
     * Тест проверяет отсутствие гонок (race conditions),
     * когда значения становятся очевидно некорректными или программа "падает".
     */
    @Test
    void testNoRaceCondition() throws InterruptedException {
        Room room = new Room();
        Settings settings = new Settings(room, 25.0, 0.4);
        Controller controller = new Controller(room, settings);
        Thread controllerThread = new Thread(controller, "Controller-Thread");
        Thread roomThread = new Thread(room, "Room-Thread");

        controllerThread.start();
        roomThread.start();

        long startTime = System.currentTimeMillis();
        long testDuration = 10000;

        while (System.currentTimeMillis() - startTime < testDuration) {
            double t = room.getTemperature();
            double h = room.getHumidity();

            assertTrue(t > -50 && t < 100, "Температура вышла за пределы допустимых значений, возможна гонка");
            assertTrue(h >= -0.5 && h <= 1.5, "Влажность вышла за пределы допустимых значений, возможна гонка");

            Thread.sleep(200);
        }

        controllerThread.interrupt();
        roomThread.interrupt();

        controllerThread.join();
        roomThread.join();
    }
}
