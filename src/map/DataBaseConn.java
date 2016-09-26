package map;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConn {
    
    private static final String URL = "jdbc:mysql://localhost:3306/cordon_training_tool";
    private static final String USERNAME = ""; //Your user name goes here
    private static final String PASSWORD = ""; //Your password goes here
    private Connection conn = null; //make this static connection?
    
    public Connection getConnection() {
        if ( conn != null )
            return conn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection( URL, USERNAME, PASSWORD );
            System.out.println("Established connection to database");
           
        } catch (Exception e) {
            System.out.println("Could not establish connection to database");
            e.printStackTrace();
        } /*finally {
            if( conn != null ) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/
        return conn;
    }
}
