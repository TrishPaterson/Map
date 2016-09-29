/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PendingTableView;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.security.pkcs11.P11Util;

/**
 *
 * @author Sakina Pan
 */
public class LoadPendingTableTest {
    String priority;
    String time;
    String evtNumber;
    String type;
    String location;
    
    Event e;
    private LoadPendingTable pendingTable;
    
    public LoadPendingTableTest() {
    }
    
    @Before
    public void setUp() {
        pendingTable = new LoadPendingTable();
        priority = "P1";
        time = "15:45:57";
        evtNumber = "P12345678";
        type  = "shop lifting";
        location = "Lower Hutt";
        
        e = new Event(priority,time,evtNumber,type,location);
    }
    
    @After
    public void tearDown() {
        pendingTable = null;
    }

    /**
     * Test of populateFields method, of class LoadPendingTable.
     */
    @Test
    public void testPopulateFields() {
        
        try {
            String expectedPriority = ("Event number: " + "P12345678"
                            + "\nEvent time: " + "15:45:57"
                            + "\nEvent priority: " + "P1"
                            + "\nEvent type: " + "shop lifting"
                            + "\nEvent location: " + "Lower Hutt");
            assertEquals(expectedPriority, pendingTable.populateFields( e ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
