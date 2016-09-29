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
import static javafx.application.Application.launch;

public class Main extends Application { 
    private LoadMap map = new LoadMap();
    private LoadUnitTable unitTable = new LoadUnitTable();
    private LoadPendingTable pendingTable = new LoadPendingTable();
    private LoadEventWindow eventWindow = new LoadEventWindow();
    
    public static void main(String[] args) {
        System.out.println("Almost Done CordonProjectTeam!");
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)throws ParserConfigurationException, SAXException, IOException{       
        map.start(primaryStage);
        pendingTable.start(primaryStage);
        eventWindow.start(primaryStage);
        unitTable.start(primaryStage);
    }
}
  
