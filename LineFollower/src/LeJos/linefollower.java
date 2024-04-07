//Code for the line follower 
package LeJos;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class linefollower extends Thread {
    private DataExchange DEObj;

    private static final int COLOR_INTENSITY_THRESHOLD = 17;

    private EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A); // Left motor
    private EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B); // Right motor

    public linefollower(DataExchange DE) {
        this.DEObj = DE;
    }

    @Override
    public void run() {
        System.out.println("Line Follower Started");
        Button.waitForAnyPress();
        
        //Play Song
        SoundPlayer song = new SoundPlayer();
        song.start();
        
        long startTime = System.currentTimeMillis();
        while (DEObj.getLooping() && Button.ESCAPE.isUp()) {
            int lineValue = DEObj.getLineValue();

            if (DEObj.isObstacleDetected()) {
                avoidObstacle();
                DEObj.setObstacleDetected(false); 
            } else {
                followLine(lineValue);
            }
        }
        
        song.stopPlaying();
        try {
            song.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long totalTime = System.currentTimeMillis() - startTime;
        LCD.clear();
        LCD.drawString("Total Time: " + (totalTime / 1000.0) + "s", 0, 4);
        System.out.println("Total Time: " + (totalTime / 1000.0) + "s");
        
        Button.waitForAnyPress();
        leftMotor.stop();
        rightMotor.stop();
        
        
    }

    private void followLine(int lineValue) {
        if (lineValue < COLOR_INTENSITY_THRESHOLD) {
            // Turn left
            leftMotor.setSpeed(100);
            rightMotor.setSpeed(200);
        } else {
            // Turn right
            leftMotor.setSpeed(200);
            rightMotor.setSpeed(100);
        }
        leftMotor.forward();
        rightMotor.forward();
        Delay.msDelay(100); 
    }

    private void avoidObstacle() {
        
        // Turn left 
        leftMotor.setSpeed(220); 
        rightMotor.setSpeed(-220); 
        leftMotor.forward();
        rightMotor.forward();
        Delay.msDelay(500);

        // Move forward a bit to clear the obstacle
        leftMotor.setSpeed(20);
        rightMotor.setSpeed(20);
        leftMotor.forward();
        rightMotor.forward();
        Delay.msDelay(500);

        // Right Turn
        leftMotor.setSpeed(-220);
        rightMotor.setSpeed(220); 
        leftMotor.backward(); 
        rightMotor.forward();
        Delay.msDelay(500);
        
         
    }

}
