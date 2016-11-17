/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tricia Paterson
 */
public class DataBaseConnTest {
    private String URL, URL2;
    private String USERNAME;
    private String PASSWORD;
    private Connection conn, conn2;
    DataBaseConn database;
    
    public DataBaseConnTest() {
    }
    
    @Before
    public void setUp()  throws SQLException {
        database = new DataBaseConn();
        URL = "jdbc:mysql://localhost:3306/cordon_training_tool";
        USERNAME = "Tricia";
        PASSWORD = "newzealand16";
        conn = null;
        conn2 = null;
        URL2 = "jdbc:mysql://localhost:3306/cordon_training_tool?USERNAME=Tricia&PASSWORD=newzealand16";
   }
    
    @After
    public void tearDown() {
        /*try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  */   }

    /**
     * Test of getConnection method, of class DataBaseConn.
     */
    @Test
    public void testGetConnection() {
        System.out.println("Testing getConnection()");
        
        DataBaseConn instance = new DataBaseConn();
        Connection expResult = null; //= DriverManager.getConnection( URL, USERNAME, PASSWORD );
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
       /* try {
           Class.forName("com.mysql.jdbc.Driver");

            // connect way #1
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (conn != null) {
                System.out.println("Connected to the database test1");
            }

            // connect way #2
            conn2 = DriverManager.getConnection(URL2);
            if (conn2 != null) {
                System.out.println("Connected to the database test2");
            }

        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }
       /* try{
            String myConnectionString = "jdbc:mysql://localhost:3306/cordon_training_tool" + "useUnicode=yes&characterEncoding=UTF-8";
            conn = DriverManager.getConnection(myConnectionString);
            Statement stmt = conn.createStatement();
            stmt.execute("SHOW TABLES");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        /*DataBaseConn instance = new DataBaseConn();
        Connection expResult = null; //= DriverManager.getConnection( URL, USERNAME, PASSWORD );
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }
    
}
