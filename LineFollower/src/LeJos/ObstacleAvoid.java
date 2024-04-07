//Code for the obstacle avoidance 

package LeJos;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;

public class ObstacleAvoid extends Thread {
    private DataExchange DEObj;
    private EV3UltrasonicSensor ultrasonicSensor;
    private SampleProvider distanceProvider;
    private float[] sample;
    private int securityDistance = 10;

    public ObstacleAvoid(DataExchange DE) {
        this.DEObj = DE;
        ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S2);
        distanceProvider = ultrasonicSensor.getDistanceMode();
        sample = new float[distanceProvider.sampleSize()];
    }

    @Override
    public void run() {
        while (DEObj.getLooping()) {
            distanceProvider.fetchSample(sample, 0);
            float distance = sample[0] * 100;
            int distanceInt = (int) distance; 

          
            LCD.drawString("Distance: " + distanceInt + " cm", 0, 3);

            if (distance < securityDistance) { 
                DEObj.setObstacleDetected(true);
            } else {
                DEObj.setObstacleDetected(false);
            }

            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
