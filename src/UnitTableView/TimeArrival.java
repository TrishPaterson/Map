
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
import MapHTML.LoadMap;

public class TimeArrival{
    private RecordLog log = new RecordLog();
    private static int prevDist;
    private static List<String> listOnSceneCordon; 
    private static Integer[] arr = {3000, 4000, 6000, 5000, 4500};
    private static int onRotCordons = 0;
    private LoadMap mapEngine = new LoadMap();
    
    public TimeArrival(){    
        Collections.shuffle(Arrays.asList(arr));    
    }
    
    public void delay(String name, int i){
        //listOnSceneCordon = new ArrayList<>();   
        Thread t1 = new Thread(new Runnable(){
            public void run(){  
                try{
                    Thread.sleep(arr[i-1]);
                    onRotCordons -= 1;
                } catch (InterruptedException ex) {}    
                
                Runnable task = new Runnable(){
                     public void run(){
                        Platform.runLater(new Runnable(){
                        @Override
                            public void run(){                                
                                log.writeLog(11, name);
                                if(name.equals("UND1")){
                                    mapEngine.startTracking();
                                    onRotCordons++;
                                }
                                if(onRotCordons == 0)
                                    mapEngine.createContainmentField();
                                if(onRotCordons != 0)//WTF
                                    mapEngine.removeContainmentField();
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
    
    public void removeOnSceneCordons(){
        onRotCordons++;
    }
    
    public void eraseList(){
        listOnSceneCordon.clear();
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
