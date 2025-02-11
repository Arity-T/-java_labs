package ru.spbstu.telematics.java;

/* 
 * Симулирует работу контроллера, установленного в комнате. Контроллер может снимать 
 * показания с сенсоров и управлять работой нагревателя и вентилятора, в соответствии
 * с указанными настройками. При этом сам контроллер не читает и не изменяет поля
 * комнаты напрямую.
 */
public class Controller implements Runnable {
    private Settings settings;
    private Sensor sensor;
    private Thread sensorThread;
    private Heater heater;
    private Thread heaterThread;
    private Fan fan;
    private Thread fanThread;

    public Controller(Room room, Settings settings) {
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

    @Override
    public void run() {
        sensorThread.start();
        heaterThread.start();
        fanThread.start();

        while (!Thread.interrupted()) {
            if (sensor.getTemperature() < settings.getTemperature() * (1 - tolerance)) {
                heater.turnOn();
            } else {
                heater.turnOff();
            }

            if (sensor.getHumidity() > settings.getHumidity() * (1 + tolerance)) {
                fan.turnOn();
            } else {
                fan.turnOff();
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
