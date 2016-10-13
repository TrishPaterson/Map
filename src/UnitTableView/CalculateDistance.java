
package UnitTableView;
import LogWindow.RecordLog;
import com.sun.media.sound.JavaSoundAudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import NotificationWindow.LoadNotification;
import javafx.application.Platform;


public class CalculateDistance{
    private RecordLog log = new RecordLog();
    private static int prevDist;
    private static List<String> listOnSceneCordon = new ArrayList<>(); 
    public void calculateDistance(String name, int delay){
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try{
                    Thread.sleep(delay);
                    listOnSceneCordon.add(name);
                } catch (InterruptedException ex) {} 
                
                Runnable task = new Runnable(){
                     public void run(){
                        Platform.runLater(new Runnable(){
                        @Override
                            public void run(){ 
                                log.writeLog(11, name);
                            }
                        });                    
                    }
                };
                Thread backgroundThread = new Thread(task);
                backgroundThread.setDaemon(true);
                backgroundThread.start();
                try {
                    backgroundThread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(CalculateDistance.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        });      
        t1.start(); 
    }
    
    public int getSizeList(){
        return listOnSceneCordon.size();
    }

    public boolean isOnScene(String name) {
        boolean isArrived = false;
        for (String temp : listOnSceneCordon) {
            if(name.equals(temp)){
                return isArrived = true;
            }
        }        
        return isArrived;
    }   
}
