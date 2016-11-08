
package UnitTableView;
import LogWindow.RecordLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import javafx.application.Platform;


public class TimeArrival{
    private RecordLog log = new RecordLog();
    private static int prevDist;
    private static List<String> listOnSceneCordon = new ArrayList<>(); 
    private static Integer[] arr = {2000, 6000, 9000, 13000, 16800};
    
    TimeArrival(){
        Collections.shuffle(Arrays.asList(arr));    
    }
    
    public void calculateDistance(String name, int i){
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try{
                    Thread.sleep(arr[i]);
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
                    Logger.getLogger(TimeArrival.class.getName()).log(Level.SEVERE, null, ex);
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
