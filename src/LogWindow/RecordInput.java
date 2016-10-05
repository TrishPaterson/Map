package LogWindow;

import com.sun.media.sound.JavaSoundAudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecordInput{
    private LoadLogWindow log = new LoadLogWindow();
    public void writeLog(int logNum, String cordonName){
        switch(logNum){
            //Dispatch
            case 1: System.out.println("Warning! Dispatcher tried to dispatch " + cordonName + " without assigning cordon to an event.");playErrorSound();break;
            case 2: System.out.println("Warning! Dispatcher tried to dispatch " + cordonName + " twice.");playErrorSound();break;
            //Onscene 
            case 3: System.out.println("Warning! Dispatcher tried to change the status of " + cordonName + " to On scene without assigning cordon to an event.");playErrorSound();break;
            case 4: System.out.println("Warning! Disptacher tried to change the status of " + cordonName + " to On scene twice");playErrorSound();break;
            case 5: System.out.println("Warning! Dispatcher tried to change the status of " + cordonName + " to On scene when the cordon has not arrived.");playErrorSound();break;
            //Change Location
            case 6: System.out.println("Warning! Dispatcher tried to change the location of " + cordonName + " without changing its current status.");playErrorSound();break;
            case 7: System.out.println("Warning! Dispatcher tried to change the location of " + cordonName + " without assigning cordon to an event.");playErrorSound();break;
            default: System.out.println("undefined");break;
        }
    }
    public void playErrorSound(){
        try {
            new JavaSoundAudioClip(new FileInputStream(new File("chord.wav"))).play();
        } catch (IOException ex) {
            Logger.getLogger(RecordInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
