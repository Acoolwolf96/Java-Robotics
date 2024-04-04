package LeJos;

import java.io.File;
import lejos.hardware.Sound;

public class SoundPlayer extends Thread {
    private volatile boolean running = true;

    public void run() {
        File soundFile = new File("Mortal Kom.wav"); 
        while (running) {
            if (soundFile.exists()) {
                Sound.playSample(soundFile, Sound.VOL_MAX);
            } else {
                System.out.println("File not found");
                break;
            }
        }
    }

    // Method to stop the sound
    public void stopPlaying() {
        running = false;
    }
}
