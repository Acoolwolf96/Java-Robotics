import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;


public class RobotDataHandler {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/robotdb";
    private static final String USER = "username";
    private static final String PASS = "password";

    public static void main(String[] args) {
        RobotDataHandler handler = new RobotDataHandler();
        LejosData data = handler.retrieveDataFromDatabase();
        if (data != null) {
            handler.sendDataToRobot(data);
        }
    }



    private LejosData retrieveDataFromDatabase() {
        String query = "SELECT leftMotor, rightMotor, securityDistance, lineColor FROM lejos_data ORDER BY id DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new LejosData(rs.getInt("leftMotor"), rs.getInt("rightMotor"),
                        rs.getInt("securityDistance"), rs.getInt("lineColor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    }



}
