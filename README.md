# Java-Robotics

In this project we demonstrated a line-following robot implemented using Java with LeJOS (Java for LEGO Mindstorms EV3). The robot uses an EV3 color sensor to detect a line on the surface and adjust its movement to follow the line. Additionally, it incorporates an ultrasonic sensor to detect obstacles and avoid collisions while following the line.


# Features
- Utilizes LeJOS library for controlling LEGO EV3 motors and sensors.
- Implements a simple line-following algorithm based on color sensor readings.
- Uses an ultrasonic sensor for obstacle detection and avoidance.
- Adjustable parameters for motor speeds, color intensity threshold, turn duration, and obstacle detection distance.

# Usage
Run the Main class on the LeJos Enviroment.
The robot will start following the line based on the color sensor readings.
If an obstacle is detected within the specified distance, the robot will adjust its path to avoid collision.
Press the ESCAPE button on the EV3 brick to stop the program.
