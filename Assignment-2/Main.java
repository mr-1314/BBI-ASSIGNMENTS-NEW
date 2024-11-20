import Classes.SmartHomeHub;
import Classes.SmartSpeaker;
import Classes.SmartTv;
import Classes.SmartThermostat;


public class Main {

	public static void main(String[] args) {
		SmartThermostat thermostat = new SmartThermostat();
		SmartSpeaker speaker = new SmartSpeaker();
		SmartTv tv = new SmartTv();

		SmartHomeHub hub = new SmartHomeHub();

		hub.addDevice(thermostat);
		hub.addDevice(speaker);
		hub.addDevice(tv);

		hub.turnAllOn();
		System.out.println();
		hub.turnAllOff();
	    System.out.println();

		thermostat.turnOn();
		boolean chkThermostatIsOn=thermostat.isOn();
		System.out.println("chkThermostatIsOn:"+chkThermostatIsOn);
		thermostat.connectToWiFi("Abhishek's WiFi.");
		boolean chkThermostatIsConToWifi=thermostat.isConnected();
		System.out.println("chkThermostatIsConToWifi:"+ chkThermostatIsConToWifi);
		thermostat.setTemperature(100000000);
		int currTemp=thermostat.getTemperature();
		System.out.println("currTemp:"+currTemp);
		thermostat.turnOff();
		System.out.println();

		speaker.turnOn();
		boolean chkSpeakerIsOn=speaker.isOn();
		System.out.println("chkSpeakerIsOn:"+chkSpeakerIsOn);
		speaker.connectToWiFi("Abhishek's WiFi.");
		boolean chkSpeakerIsConToWifi=thermostat.isConnected();
		System.out.println("chkSpeakerIsConToWifi:"+ chkSpeakerIsConToWifi);
		speaker.setVolume(50);
		int currVol=speaker.getVolume();
		System.out.println("currVol:"+currVol);
		speaker.mute();
		currVol=speaker.getVolume();
		System.out.println("currVol:"+currVol);
		speaker.turnOff();
		System.out.println();

		tv.turnOn();
		boolean chkTvIsOn=tv.isOn();
		System.out.println("chkTvIsOn:"+chkTvIsOn);
		tv.connectToWiFi("Abhishek's WiFi.");
		boolean chkTvIsConToWifi=tv.isConnected();
		System.out.println("chkTvIsConToWifi:"+ chkTvIsConToWifi);
		tv.setVolume(50);
		int currTvVol=tv.getVolume();
		System.out.println("currTvVol:"+currVol);
		tv.mute();
		tv.setTemperature(55);
		int currTvTemp=tv.getTemperature();
		System.out.println("currTemp:"+currTvTemp);
		tv.turnOff();
		System.out.println();

	}
}









