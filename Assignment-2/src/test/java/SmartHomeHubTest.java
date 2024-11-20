package src.test.java;
import static  org.junit.Assert.*;
import java.util.ArrayList;
import Classes.SmartSpeaker;
import Classes.SmartThermostat;
import Classes.SmartTv;
import Interfaces.PowerControl;
import org.junit.Test;
import Classes.SmartHomeHub;


public class SmartHomeHubTest {

    @Test
    public void testAddDevice() {
        SmartHomeHub hub = new SmartHomeHub();

        SmartThermostat thermostat = new SmartThermostat();
        SmartSpeaker speaker = new SmartSpeaker();
        SmartTv tv = new SmartTv();

        hub.addDevice(thermostat);
        hub.addDevice(speaker);
        hub.addDevice(tv);


        ArrayList<PowerControl> devices = hub.getDevices();
        assertEquals(3, devices.size());
        assertTrue("Hub should contain the thermostat.", devices.contains(thermostat));
        assertTrue(devices.contains(speaker));
        assertTrue("Hub should contain the TV.", devices.contains(tv));
    }

    @Test
    public void testTurnAllOn() {
        SmartHomeHub hub = new SmartHomeHub();

        SmartThermostat thermostat = new SmartThermostat();
        SmartSpeaker speaker = new SmartSpeaker();
        SmartTv tv = new SmartTv();


        hub.addDevice(thermostat);
        hub.addDevice(speaker);
        hub.addDevice(tv);

        hub.turnAllOn();


        assertTrue("Thermostat should be ON.", thermostat.isOn());
        assertTrue("Speaker should be ON.", speaker.isOn());
        assertTrue("TV should be ON.", tv.isOn());
    }

    @Test
    public void testTurnAllOff() {
        SmartHomeHub hub = new SmartHomeHub();

        SmartThermostat thermostat = new SmartThermostat();
        SmartSpeaker speaker = new SmartSpeaker();
        SmartTv tv = new SmartTv();

        hub.addDevice(thermostat);
        hub.addDevice(speaker);
        hub.addDevice(tv);

        hub.turnAllOn();
        hub.turnAllOff();

        // Validate all devices are OFF
        assertFalse("Thermostat should be OFF.", thermostat.isOn());
        assertFalse("Speaker should be OFF.", speaker.isOn());
        assertFalse("TV should be OFF.", tv.isOn());
    }

}
