package LogWindow;

import com.sun.media.sound.JavaSoundAudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecordInput{

    private static LoadLogWindow log = new LoadLogWindow();
    private static String currentEvent;
    public void writeLog(int logNum, String name){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");  
        switch(logNum){
    // ERROR HANDLING
            //Dispatch
            case 1: log.addLog("Warning! Dispatcher tried to dispatch " + name+ " without assigning cordon to an event." ,sdf.format(cal.getTime()));playErrorSound();break;
            case 2: log.addLog("Warning! Dispatcher tried to dispatch " + name+ " twice.",sdf.format(cal.getTime()));playErrorSound();break;
            //Onscene 
            case 3: log.addLog("Warning! Dispatcher tried to change the status of " + name+ " to On scene without assigning cordon to an event.",sdf.format(cal.getTime()));playErrorSound();break;
            case 4: log.addLog("Warning! Disptacher tried to change the status of " + name+ " to On scene twice",sdf.format(cal.getTime()));playErrorSound();break;
            case 5: log.addLog("Warning! Dispatcher tried to change the status of " + name+ " to On scene when the cordon has not arrived.",sdf.format(cal.getTime()));playErrorSound();break;
            //Change Location
            case 6: log.addLog("Warning! Dispatcher tried to change the location of " + name+ " without changing its current status.",sdf.format(cal.getTime()));playErrorSound();break;
            case 7: log.addLog("Warning! Dispatcher tried to change the location of " + name+ " without assigning cordon to an event.",sdf.format(cal.getTime()));playErrorSound();break;
            //K9
            case 14: log.addLog("Warning! Dispatcher tried to unassign " + name + "  without assigning cordon to an event",sdf.format(cal.getTime()));playErrorSound();break;
            case 15: log.addLog("Warning! Disptacher tried to unassign " + name + " when the offender has not been caught.",sdf.format(cal.getTime()));playErrorSound();break;
    //RECORDING DISPATCHER ACTIONS        
            //PendingEvent
            case 8: log.addLog(name + " has been initiated.",sdf.format(cal.getTime()));currentEvent = name;break;
            
            //Dispatch
            case 9: log.addLog("Dispatcher disptach " +name+ " to " + currentEvent +".",sdf.format(cal.getTime()));break;
            
            //Onscene 
            case 10: log.addLog("Dispatcher changed status of " +name+ " to on scene.",sdf.format(cal.getTime()));break;
            case 11: log.addLog(name + " has arrived on scene.",sdf.format(cal.getTime())); break;
                       
            //DogHandler
            case 12: log.addLog("Dog handler has caught the offender.",sdf.format(cal.getTime()));break;
            
            //K9
            case 13: log.addLog(name + " has been set to available.",sdf.format(cal.getTime())); break;    
            case 16: log.addLog("Dispatcher has successfully closed the event: " + currentEvent ,sdf.format(cal.getTime())); break;

            default: log.addLog("undefined",sdf.format(cal.getTime()));break;
        }
    }
    
    public void writeLog(int logNum, String name, String location){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");  
        //Change Location
        log.addLog("Dispatcher changed the location of " +name+ " to " + location,sdf.format(cal.getTime()));     
    }
    
    public void playErrorSound(){
        try {
            new JavaSoundAudioClip(new FileInputStream(new File("chord.wav"))).play();
        } catch (IOException ex) {
            Logger.getLogger(RecordInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
