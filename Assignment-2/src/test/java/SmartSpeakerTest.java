package src.test.java;
import static  org.junit.Assert.*;
import Classes.SmartSpeaker;
import org.junit.Test;

public class SmartSpeakerTest {

    @Test
    public void testSpeakerOnOff() {
        SmartSpeaker speaker = new SmartSpeaker();

        speaker.turnOn();
        assertTrue("Speaker should be ON after calling turnOn.", speaker.isOn());

        speaker.turnOff();
        assertFalse("Speaker should be OFF after calling turnOff.", speaker.isOn());
    }

    @Test
    public void testSpeakerVolumeControl() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.turnOn();

        speaker.setVolume(50);
        assertEquals(50, speaker.getVolume());

        speaker.mute();
        assertEquals(0, speaker.getVolume());
    }
}