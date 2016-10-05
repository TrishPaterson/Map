
package UnitTableView;
import LogWindow.RecordInput;
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
import javafx.stage.Stage;


public class CalculateDistance{
    private Stage stage = new Stage();
    private static List<String> listOnSceneCordon = new ArrayList<>(); 
    public void calculateDistance(String name, int dist){
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try{
                    Thread.sleep(dist);
                    listOnSceneCordon.add(name);
                } catch (InterruptedException ex) {} 
                
                Runnable task = new Runnable(){
                     public void run(){
                        Platform.runLater(new Runnable(){
                        @Override
                            public void run(){  
                                LoadNotification ln = new LoadNotification(name + " has arrived on scene.");
                                ln.start(ln.eventWindow); 
                            }
                        });                    
                    }
                };
                Thread backgroundThread = new Thread(task);
                backgroundThread.setDaemon(true);
                backgroundThread.start();
                
                try {
                    new JavaSoundAudioClip(new FileInputStream(new File("notify.wav"))).play();
                } catch (IOException ex) {
                    Logger.getLogger(RecordInput.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });      
        t1.start(); 
    }
    
    public int getSizeList(){
        return listOnSceneCordon.size();
    }

    public static List<String> getList() {
        return listOnSceneCordon;
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
