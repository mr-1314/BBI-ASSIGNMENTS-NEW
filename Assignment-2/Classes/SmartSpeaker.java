package Classes;
import Interfaces.AudioControl;
import Interfaces.NetworkConnected;
import Interfaces.PowerControl;

public class SmartSpeaker implements PowerControl, NetworkConnected, AudioControl {
    private boolean powerStatus;
    private boolean connected;
    private int volume;

    @Override
    public boolean turnOn() {
        powerStatus = true;
        System.out.println("SmartSpeaker is now ON.");
        return powerStatus;
    }

    @Override
    public void turnOff() {
        powerStatus = false;
        System.out.println("SmartSpeaker is now OFF.");
    }

    @Override
    public boolean isOn() {
        return powerStatus;
    }

    @Override
    public void connectToWiFi(String networkName) {
        connected = true;
        System.out.println("SmartSpeaker connected to WiFi: " + networkName);
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Volume set to: " + volume);
    }

    @Override
    public void mute() {
        this.volume = 0;
        System.out.println("Speaker muted.");
    }

    @Override
    public int getVolume() {
        return volume;
    }
}