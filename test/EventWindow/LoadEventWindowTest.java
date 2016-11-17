/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventWindow;

import PendingTableView.Event;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Paterson
 */
public class LoadEventWindowTest {
    private LoadEventWindow eventWindow;
    String priority;
    String time;
    String evtNumber;
    String type;
    String location;
    String informantName;
    String headline;
    String remark;
     
    Event evt;
    
    
    public LoadEventWindowTest() {
    }
    
    @Before
    public void setUp() {
        
        eventWindow = new LoadEventWindow();
        priority = "1";
        time = "15:45:57";
        evtNumber = "";
        type = "";
        location = "";
        informantName = "";
        headline = "";
        remark = "";
        
        evt = new Event(priority, time, evtNumber, type, location, informantName, headline, remark );
        
    }
    
    @After
    public void tearDown() {
        eventWindow = null;
    }

    /**
     * Test of setRemarksList method, of class LoadEventWindow.
     */
    /*@Test
    public void testSetRemarksList() {
        System.out.println("setRemarksList");
        String remark = "";
        LoadEventWindow.setRemarksList(remark);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateFields method, of class LoadEventWindow.
     */
    @Test
    public void testPopulateFields() {
        System.out.println("populateFields");
       // Event evt = e;
        LoadEventWindow.populateFields(evt);
        System.out.println(evt);
        //String expectResult = "High" + "12:00" + "Assault" + "Gotham" + "Joe Bloggs" + "Bane is on a rampage" +"- Police initially said foul play was not suspected";
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    /*"High", "12:00", "B1204", "Assault", "Gotham",
                            "Joe Bloggs", "Bane is on a rampage", 
                            "- Police initially said foul play was not suspected");*/
}
