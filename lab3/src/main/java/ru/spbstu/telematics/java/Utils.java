package ru.spbstu.telematics.java;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    static public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static public void sleepRandomTime(long from, long to) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(from, to));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
