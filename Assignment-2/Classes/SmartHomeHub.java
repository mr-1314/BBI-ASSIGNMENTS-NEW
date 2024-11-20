package Classes;
import Interfaces.PowerControl;

import java.util.ArrayList;


public class SmartHomeHub {
    private final ArrayList<PowerControl> devices = new ArrayList<>();

    public void addDevice(PowerControl device) {
        devices.add(device);
    }

    public void turnAllOn() {
        for (PowerControl device : devices) {
            device.turnOn();
        }
    }

    public void turnAllOff() {
        for (PowerControl device : devices) {
            device.turnOff();
        }
    }
    public ArrayList<PowerControl> getDevices(){
        return devices;
    }
}
