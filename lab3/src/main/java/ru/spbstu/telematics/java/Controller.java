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
    private Heater heater;
    private Fan fan;

	@Override
	public void run() {

	}
}
