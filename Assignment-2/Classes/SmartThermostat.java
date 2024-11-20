package Classes;

import Interfaces.NetworkConnected;
import Interfaces.PowerControl;
import Interfaces.TemperatureControl;

public class SmartThermostat implements PowerControl, NetworkConnected, TemperatureControl {
    private boolean powerStatus;
    private boolean connected;
    private int temperature;

    @Override
    public boolean turnOn(){
        powerStatus = true;
        System.out.println("SmartThermostat is now ON.");
        return false;
    }
    @Override
    public void turnOff() {
        powerStatus = false;
        System.out.println("SmartThermostat is now OFF.");
    }

    @Override
    public boolean isOn() {
        return powerStatus;
    }

    @Override
    public void connectToWiFi(String networkName) {
        connected = true;
        System.out.println("SmartThermostat connected to WiFi: " + networkName);
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Temperature set to: " + temperature);

    }

    @Override
    public int getTemperature() {
        return temperature;
    }
}