package LeJos;

import lejos.hardware.Button;
public class main {
    private static DataExchange DE = new DataExchange();
    private static linefollower car = new linefollower(DE);
    private static ColorSensor ref = new ColorSensor(DE);
    private static ObstacleAvoid obj = new ObstacleAvoid(DE);

    public static void main(String[] args) {
        car.start();
        ref.start();
        obj.start();

        
        while (Button.ESCAPE.isUp()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        DE.setLooping(false); 
    }
}
