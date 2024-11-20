package Classes;


import Interfaces.AudioControl;
import Interfaces.NetworkConnected;
import Interfaces.PowerControl;
import Interfaces.TemperatureControl;

public class SmartTv implements PowerControl, NetworkConnected, TemperatureControl, AudioControl {
    private boolean powerStatus;
    private boolean connected;
    private int temperature;
    private int volume;

    @Override
    public boolean turnOn() {
        powerStatus = true;
        System.out.println("SmartTV is now ON.");
        return false;
    }

    @Override
    public void turnOff() {
        powerStatus = false;
        System.out.println("SmartTV is now OFF.");
    }

    @Override
    public boolean isOn() {
        return powerStatus;
    }

    @Override
    public void connectToWiFi(String networkName) {
        connected = true;
        System.out.println("SmartTV connected to WiFi: " + networkName);
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("SmartTV temperature set to: " + temperature);
    }

    @Override
    public int getTemperature() {
        return temperature;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("SmartTV volume set to: " + volume);
    }

    @Override
    public void mute() {
        this.volume = 0;
        System.out.println("SmartTV is muted.");
    }

    @Override
    public int getVolume() {
        return volume;
    }
}
