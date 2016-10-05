package map;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import MapHTML.LoadMap;
import UnitTableView.LoadUnitTable;
import PendingTableView.LoadPendingTable;
import EventWindow.LoadEventWindow;
import LogWindow.LoadLogWindow;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;

public class Main extends Application { 
    private LoadMap map = new LoadMap();
    private LoadUnitTable unitTable = new LoadUnitTable();
    private LoadPendingTable pendingTable = new LoadPendingTable();
    private LoadEventWindow eventWindow = new LoadEventWindow();
    private LoadLogWindow logWindow = new LoadLogWindow();

    public static void main(String[] args) {
        System.out.println("Ahoy CordonProjectTeam we be almost finished, by Davy Jones' locker! Don't forget t' do testin' or ye will walk th' plank!");
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)throws ParserConfigurationException, SAXException, IOException{       
        map.start(primaryStage);
        logWindow.start(primaryStage);
        eventWindow.start(primaryStage);
        unitTable.start(primaryStage);
        pendingTable.start(primaryStage);        
    }
}
  
