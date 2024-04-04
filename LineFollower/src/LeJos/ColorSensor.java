package LeJos;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.lcd.LCD;

public class ColorSensor extends Thread {
    private DataExchange DEObj;
    private EV3ColorSensor sensor;
    private SampleProvider colorProvider;
    private float[] sample;

    public ColorSensor(DataExchange DE) {
        this.DEObj = DE;
        this.sensor = new EV3ColorSensor(SensorPort.S1);
        this.colorProvider = sensor.getRedMode();
        this.sample = new float[colorProvider.sampleSize()];
    }

    @Override
    public void run() {
        while (DEObj.getLooping()) {
            colorProvider.fetchSample(sample, 0);
            int intensity = (int) (sample[0] * 100);
            DEObj.setLineValue(intensity);
 
            LCD.drawString("Intensity: " + intensity + "  ", 0, 2);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
