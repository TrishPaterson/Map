package UnitTableView;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import MapHTML.LoadMap;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

public class LoadUnitTable extends Application {
    private int id;
    private static Unit x = null;
    private static String uniqID;
    private static String callSign;
    private static String defLocation;
    private static String currEvent;
    private static String time;
    private static String type;
    private static String status;
    
    private ObservableList<Unit> unitSelected; 
    private TableView<Unit> table;    
    private Stage unitWindow;
   
    private Text actionStatus;
    private static double xOffset = 0;
    private static double yOffset = 0;   
   
    private LoadMap mapEngine = new LoadMap();
 
    @Override
    public void start(Stage primaryStage)throws ParserConfigurationException, SAXException, IOException{
        //Load UnitTable   
        createTable();
        createContextMenu();
        
        unitWindow = primaryStage;
        unitWindow.initStyle(StageStyle.UNDECORATED);
        unitWindow.getIcons().add(new Image("/Images/NCP.PNG"));
        unitWindow.setTitle("Unit Table");
       table.getStylesheets().add("UnitTableView/UnitTable.css");
        
        Label label = new Label("Unit Table");
        
        //FontStyle
        label.setTextFill(Color.LIGHTGRAY);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
        label.setTranslateX(55);
        label.setTranslateY(-6);
        
        //CP Image
        Image image = new Image("/Images/NCP.PNG");
        ImageView TIcon = new ImageView();
        TIcon.setImage(image);
        TIcon.setFitWidth(45);
        TIcon.setPreserveRatio(true);
        TIcon.setSmooth(true);
        TIcon.setCache(true);
        TIcon.setTranslateX(5);
        TIcon.setTranslateY(-18);
        
        //Exit Image
        ImageView Exit = new ImageView("/Images/ExitButton2.PNG");
        Exit.setFitHeight(18);
        Exit.setFitWidth(18);
        Exit.setTranslateX(575);
        Exit.setTranslateY(-10);
        
        
        ImageView Min = new ImageView("/Images/minimizeButton1.PNG");
        Min.getStyleClass().add("ImageView");
        Min.setFitHeight(18);
        Min.setFitWidth(18);
        Min.setTranslateX(556);
        Min.setTranslateY(-13);
        
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);  
        
        VBox vbox = new VBox(-15);   
        vbox.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setPadding(new Insets(12, 0, 15, 0));
        vbox.getChildren().addAll(label,Exit,Min,TIcon,table, actionStatus);
        Scene scene = new Scene(vbox, 600,450); // w x h 
        unitWindow.setScene(scene);
        unitWindow.show();
        
        //Positioning the window on the screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        unitWindow.setX((primScreenBounds.getWidth() - unitWindow.getWidth()) /100); 
        unitWindow.setY((primScreenBounds.getHeight() - unitWindow.getHeight()) / 1.9);      
        
        //Exit Button
        Exit.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                System.exit(0);
            }
        });    
        
        Min.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                unitWindow.setIconified(true);
            }     
        });
       
        unitWindow.getScene().setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                xOffset = unitWindow.getX() - event.getScreenX();
                yOffset = unitWindow.getY() - event.getScreenY();
            }
        });
        unitWindow.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent event) { 
                unitWindow.setX(event.getScreenX() + xOffset);
                unitWindow.setY(event.getScreenY() + yOffset);
            }            
        });     
    }
    
    public void createContextMenu(){
        //Multiple selection
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //Get Selected Item
        unitSelected = table.getSelectionModel().getSelectedItems(); 
     
        //ContextMenu
        ContextMenu contextMenu = new ContextMenu();
        
        //menu items
        MenuItem item1 = new MenuItem("ChangeLocation");
        MenuItem item2 = new MenuItem("Dispatch");
        
        //Add item to context menu
        contextMenu.getItems().addAll(item1, item2);
        
        //set context menu to table
        table.setContextMenu(contextMenu);
        
        //changeLocationFunction
        item1.setOnAction(e -> changeLocation());
        
        //dispatchLocation function
        item2.setOnAction(e -> dispatchCordon());            
    }

    public void changeLocation(){
        unitSelected.sorted();
        id = checkId(unitSelected.get(0).getId());  
        mapEngine.changeLocation(id); 
        table.getSelectionModel().clearSelection();
    }
    
    public void dispatchCordon(){
        unitSelected.sorted();
        for(int x = 0; x < unitSelected.size(); x++){
            id = checkId(unitSelected.get(x).getId());
            mapEngine.setMarkerId(id);  
        }    
        mapEngine.createMarker();
        table.getSelectionModel().clearSelection();
    }    
    
    public int checkId(String id){
        switch(id){
           case "UNI1": return 1;
           case "UNS1": return 2; 
           case "UNQ1": return 3; 
           case "UNT1": return 4; 
           case "UND1": return 5; 
           default: return -1;
        }      
    }
    
    public void createTable()throws ParserConfigurationException, SAXException, IOException{
        //ID column
        TableColumn<Unit, String> uniqIdCol = new TableColumn<>("ID");
        uniqIdCol.setMinWidth(100);
        uniqIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        //Location column
        TableColumn<Unit, String> currLocationCol = new TableColumn<>("Location");
        currLocationCol.setMinWidth(300);
        currLocationCol.setCellValueFactory(new PropertyValueFactory<>("currLocation"));
        //Time column
        TableColumn<Unit, String> timeCol = new TableColumn<>("Time");
        timeCol.setMinWidth(100);
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        //Type column
        TableColumn<Unit, String> typeCol = new TableColumn<>("Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    
        //create Table
        table = new TableView<>();
        table.setItems(getUnits());
        table.getColumns().addAll(uniqIdCol,currLocationCol,timeCol,typeCol);        
    }
    
    public ObservableList<Unit> getUnits()throws ParserConfigurationException, SAXException, IOException{       
        ObservableList<Unit> unit = FXCollections.observableArrayList();    
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("UnitInfo.xml");
        NodeList nList = doc.getElementsByTagName("unit");
        
        //-------------------------------------------------------------------
        //code below uses xml, comment out if using database
        
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                uniqID = eElement.getAttribute("id");
                callSign = eElement.getElementsByTagName("callSign").item(0).getTextContent();
                defLocation = eElement.getElementsByTagName("defLocation").item(0).getTextContent();
                currEvent = eElement.getElementsByTagName("currEvent").item(0).getTextContent();
                time = eElement.getElementsByTagName("time").item(0).getTextContent();
                type = eElement.getElementsByTagName("type").item(0).getTextContent();
                status = eElement.getElementsByTagName("status").item(0).getTextContent();
                x = new Unit(uniqID, callSign, defLocation, currEvent, time, type, status);
                unit.add(x);
            }
        }
        //--------------------------------------------------------------------
        //--------------------------------------------------------------------
        //Code below reads database instead of XML file
        /*
        Connection conn = null;
        DataBaseConn dbConn = null;
        PreparedStatement ps;
        ResultSet rs, rs2;
        Statement stmt;
        try {
            dbConn = new DataBaseConn();
            conn = dbConn.getConnection();
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery("select * from unit");
            
            ps = conn.prepareStatement(
                    "select unit_type_name from unit "
                    + "inner join unit_type "
                    + "on unit_type_fk = unit_type_id "
                    + "where unit_name = ?"
                    );
            while( rs.next() ) {
                uniqID = rs.getString("unit_name");
                callSign = rs.getString("unit_name");
                defLocation = "locUndefined";
                currEvent = "currUndefined";
                time = "timeUndefined";
                status = "avail";
                currEvent = "evtUndefined";
                ps.setString( 1, callSign );
                rs2 = ps.executeQuery();
                rs2.first();
                type = rs2.getString("unit_type_name");
                System.out.println(type);
                x = new Unit(uniqID, callSign, defLocation, currEvent, time, type, status);
                unit.add(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        //--------------------------------------------------------------------
        return unit;
    }
}
