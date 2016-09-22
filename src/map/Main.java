package map;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import MapHTML.LoadMap;
import UnitTableView.LoadUnitTable;

public class Main extends Application { 
    private LoadMap map = new LoadMap();
    private LoadUnitTable unitTable = new LoadUnitTable();
    
    public static void main(String[] args) {
        System.out.println("Hello CordonProjectTeam!");
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)throws ParserConfigurationException, SAXException, IOException{       
        map.start(primaryStage);
        unitTable.start(primaryStage);
    }
}
  
