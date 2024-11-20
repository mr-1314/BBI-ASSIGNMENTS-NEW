package src.test.java;
import static  org.junit.Assert.*;
import Classes.SmartThermostat;
import org.junit.Test;


public class SmartThermostatTest {
    @Test
    public void testThermostatOnOff() {
        SmartThermostat thermostat = new SmartThermostat();

        thermostat.turnOn();
        assertTrue("Thermostat should be ON after calling turnOn.", thermostat.isOn());

        thermostat.turnOff();
        assertFalse("Thermostat should be OFF after calling turnOff.", thermostat.isOn());
    }

    @Test
    public void testThermostatWiFiConnection() {
        SmartThermostat thermostat = new SmartThermostat();
        thermostat.turnOn();

        thermostat.connectToWiFi("Abhilasha's WiFi");
        assertTrue("Thermostat should be connected to WiFi.", thermostat.isConnected());
    }

    @Test
    public void testThermostatTemperatureSetting() {
        SmartThermostat thermostat = new SmartThermostat();
        thermostat.turnOn(); // Ensure the thermostat is ON for setting temperature


        thermostat.setTemperature(25);
        int temp=thermostat.getTemperature();

        assertEquals("Temperature should be set to 25.",25, temp);


        thermostat.setTemperature(10);
        int currTemp = thermostat.getTemperature();
        assertEquals("Temperature should not exceed the upper limit (assume 100°C).", true, currTemp <= 100);

        // Attempt to set an excessively low temperature
        thermostat.setTemperature(10);
        currTemp = thermostat.getTemperature();
        assertTrue("Temperature should not go below the lower limit (assume 0°C).", currTemp >= 0);
    }


}
