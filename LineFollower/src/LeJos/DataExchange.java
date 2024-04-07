//Code for the dataExchange
package LeJos;

public class DataExchange {
    private int lineValue;
    private boolean looping = true;
    private boolean obstacleDetected = false;
    
    
    //Line Follower
    public void setLineValue(int value) {
        this.lineValue = value;
    }

    public int getLineValue() {
        return this.lineValue;
    }
    // Getters and Setters for Obstacle
    public void setObstacleDetected(boolean detected) {
    	this.obstacleDetected = detected;
    }
    
    public boolean isObstacleDetected() {
    	return obstacleDetected;
    }
    // boolean condition for robot program
    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public boolean getLooping() {
        return this.looping;
    }
}
