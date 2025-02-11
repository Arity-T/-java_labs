package ru.spbstu.telematics.java;

/* 
 * Симулирует работу контроллера, установленного в комнате. Контроллер может снимать 
 * показания с сенсоров и управлять работой нагревателя и вентилятора, в соответствии
 * с указанными настройками. При этом сам контроллер не читает и не изменяет поля
 * комнаты напрямую.
 */
public class Controller implements Runnable {
    private Room room;
    private Settings settings;
    private Sensor sensor;
    private Thread sensorThread;
    private Heater heater;
    private Thread heaterThread;
    private Fan fan;
    private Thread fanThread;

    public Controller(Room room, Settings settings) {
        this.room = room;
        this.settings = settings;

        sensor = new Sensor(room);
        sensorThread = new Thread(sensor);

        heater = new Heater(room);
        heaterThread = new Thread(heater);

        fan = new Fan(room);
        fanThread = new Thread(fan);
    }

    private long updateIntervalMs = 500;
    private double tolerance = 0.01;

    private void log(String string) {
        System.out.printf("[Controller in room %s] %s\n", room.name, string);
    }

    @Override
    public void run() {
        sensorThread.start();
        heaterThread.start();
        fanThread.start();
        log("Started sensor, heater and fan threads");

        while (!Thread.interrupted()) {
            double currentTemperature = sensor.getTemperature();
            double desiredTemperature = settings.getTemperature();

            if (currentTemperature < desiredTemperature * (1 - tolerance)) {
                if (!heater.isOn()) {
                    log(String.format(
                            "Turning heater ON (current - %.2fC°, desired %.2fC°)",
                            currentTemperature,
                            desiredTemperature));
                    heater.turnOn();
                }
            } else {
                if (heater.isOn()) {
                    log(String.format(
                            "Turning heater OFF (current - %.2fC°, desired %.2fC°)",
                            currentTemperature,
                            desiredTemperature));
                    heater.turnOff();
                }
            }

            double currentHumidity = sensor.getHumidity();
            double desiredHumidity = settings.getHumidity();
            if (currentHumidity > desiredHumidity * (1 + tolerance)) {
                if (!fan.isOn()) {
                    log(String.format(
                            "Turning fan ON (current - %.2f%%, desired %.2f%%)",
                            currentHumidity * 100,
                            desiredHumidity * 100));
                    fan.turnOn();
                }
            } else {
                if (fan.isOn()) {
                    log(String.format(
                            "Turning fan OFF (current - %.2f%%, desired %.2f%%)",
                            currentHumidity * 100,
                            desiredHumidity * 100));
                    fan.turnOff();
                }
            }

            Utils.sleep(updateIntervalMs);
        }

        sensorThread.interrupt();
        heaterThread.interrupt();
        fanThread.interrupt();

        try {
            sensorThread.join();
            heaterThread.join();
            fanThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
