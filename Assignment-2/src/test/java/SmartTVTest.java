package src.test.java;
import static  org.junit.Assert.*;

import Classes.SmartTv;
import org.junit.Test;


public class SmartTVTest {
    @Test
    public void testTvOnOff() {
        SmartTv tv = new SmartTv();


        tv.turnOn();
        assertTrue("TV should be ON after calling turnOn.", tv.isOn());


        tv.turnOff();
        assertFalse("TV should be OFF after calling turnOff.", tv.isOn());
    }

    @Test
    public void testTvWiFiConnection() {
        SmartTv tv = new SmartTv();
        tv.turnOn();


        tv.connectToWiFi("Abhilasha's WiFi");
        assertTrue("TV should be connected to WiFi.", tv.isConnected());
    }

    @Test
    public void testTvVolumeControl() {
        SmartTv tv = new SmartTv();
        tv.turnOn();


        tv.setVolume(50);
        int x=tv.getVolume();
        assertEquals(50,x);


        tv.mute();
        x=tv.getVolume();
        assertEquals(0,x);
    }

    @Test
    public void testTvTemperatureControl() {
        SmartTv tv = new SmartTv();
        tv.turnOn();
        tv.setTemperature(55);
        int x=tv.getTemperature();
        assertEquals(55, x);
    }

}
