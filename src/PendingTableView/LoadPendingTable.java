package PendingTableView;

import EventWindow.LoadEventWindow;
import LogWindow.RecordLog;
import MapHTML.LoadMap;
import UnitTableView.LoadUnitTable;
import UnitTableView.Unit;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
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

public class LoadPendingTable extends Application {
    private String priority;
    private String time;
    private String evtNumber;
    private String type;
    private String location;
    private String informantName;
    private String headline;
    private String remarks;
    
    private static Event evt = null;
    private static boolean isEventOn = false;
    private TableView table;
    private Text actionStatus;
    private static double xOffset = 0;
    private static double yOffset = 0;
    private Stage pendingWindow;
    private RecordLog log = new RecordLog();
    private LoadMap mapEngine = new LoadMap();  
    @Override
    public void start(Stage primaryStage) {
        createTable();
        
        pendingWindow = new Stage();
        pendingWindow.initStyle(StageStyle.UNDECORATED);
        pendingWindow.getIcons().add(new Image("/Images/NCP.PNG"));
        table.getStylesheets().add("PendingTableView/pending.css");
        //Window Title
        Label label = new Label("Event Pending");
        
        //FontStyle
        label.setTextFill(Color.LIGHTGRAY);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
        label.setTranslateX(52);
        label.setTranslateY(-18);
        
        //CP Image
        Image image = new Image("/Images/NCP.PNG");
        ImageView TIcon = new ImageView();
        TIcon.setImage(image);
        TIcon.setFitWidth(40);
        TIcon.setPreserveRatio(true);
        TIcon.setSmooth(true);
        TIcon.setCache(true);
        TIcon.setTranslateX(5);
        TIcon.setTranslateY(7);
        
        //Exit Image
        ImageView Exit = new ImageView("/Images/ExitButton2.PNG");
        Exit.getStyleClass().add("ImageView");
        Exit.setFitHeight(16);
        Exit.setFitWidth(16);
        Exit.setTranslateX(578);
        Exit.setTranslateY(10);
        
        //Minimize Image
        ImageView Min = new ImageView("/Images/minimizeButton1.PNG");
        Min.getStyleClass().add("ImageView");
        Min.setFitHeight(16);
        Min.setFitWidth(16);
        Min.setTranslateX(560);
        Min.setTranslateY(10);
        
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);  
   
        VBox vbox = new VBox(-16);   
        vbox.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setPadding(new Insets(-6, 0, -10, 0));

        vbox.getChildren().addAll(Exit,Min, TIcon, actionStatus,label,table );
        Scene scene = new Scene(vbox, 600,200); // w x h
         
        pendingWindow.setScene(scene);
        pendingWindow.show();
        
        //Positioning the window on the screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        pendingWindow.setX((primScreenBounds.getWidth() - pendingWindow.getWidth()) /100); 
        pendingWindow.setY((primScreenBounds.getHeight() - pendingWindow.getHeight()) / 1); 
        
        pendingWindow.getScene().setOnMousePressed((MouseEvent event) -> {
            xOffset = pendingWindow.getX() - event.getScreenX();
            yOffset = pendingWindow.getY() - event.getScreenY();
        });
        pendingWindow.getScene().setOnMouseDragged((MouseEvent event) -> {
            pendingWindow.setX(event.getScreenX() + xOffset);
            pendingWindow.setY(event.getScreenY() + yOffset); 
        }); 
        
        //Minimize Button
        Min.setOnMouseClicked((MouseEvent me) -> {
            pendingWindow.setIconified(true);     
        });
        
        //Exit Button
        Exit.setOnMouseClicked((MouseEvent t) -> {
            System.exit(0);       
        });         
    }
    
    public void createTable(){
        table = new TableView<>();                
        TableColumn prioityCol = new TableColumn("Prioity");
        prioityCol.setCellValueFactory(new PropertyValueFactory("priority"));
        //prioityCol.prefWidthProperty().bind(table.widthProperty().multiply(0.0));
        TableColumn timeCol = new TableColumn("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory("time"));
        //prioityCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        TableColumn eventNumberCol = new TableColumn("Event Number");
        eventNumberCol.setCellValueFactory(new PropertyValueFactory("evtNumber"));
        TableColumn TypeCol = new TableColumn("Type");
        TypeCol.setCellValueFactory(new PropertyValueFactory("type"));
        TypeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        TableColumn locationCol = new TableColumn("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory("location"));
        locationCol.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        
        table.setItems(getEvtList());
        table.getColumns().setAll(prioityCol,timeCol,eventNumberCol,TypeCol,locationCol);    
        table.setPrefWidth(450);
        table.setPrefHeight(225);
        table.setTranslateX(0);
        table.setTranslateY(24); 

        //double click function             
        table.setOnMouseClicked(event -> {
            ObservableList<Event> evtSelected,allEvents;
            allEvents = table.getItems();
            evtSelected = table.getSelectionModel().getSelectedItems(); 
            int nextEvent = 1;
            if (event.getClickCount() == 2 && (! evtSelected.isEmpty()) && !isEventOn ) {
                
                LoadEventWindow.populateFields(evtSelected.get(0));
                //System.out.println( LoadEventWindow.populateFields(evtSelected.get(0)));
                
                //Temporary while the geolocation is not implemented-------------------
                if(evtSelected.get(0).getLocation().equals("Navigation Dr")){
                    mapEngine.createExpanding(-41.113652, 174.906547, 1);
                    nextEvent = 2;
                }else {
                    mapEngine.createExpanding(-41.109542, 174.889214, 1); 
                }          
                //----------------------------------------------------------------------
                mapEngine.createEvent(evtSelected.get(0).getLocation());              
                mapEngine.setEvent(evtSelected.get(0).getLocation());
                log.writeLog(8, evtSelected.get(0).getLocation());
                
                evtSelected.forEach(allEvents::remove);//remove selected item
                isEventOn = true; //disable pendingevent while this event is finish
                table.getSelectionModel().clearSelection();
                
                //System.out.println( ""+populateFields(evtSelected.get(0)));
            }          
        });
    }  
    
    public void enAbleRow(){
        isEventOn = false;
    }
    
    public boolean getIsEventOn(){
        return isEventOn;
    }
    
    public ObservableList<Event> getEvtList(){       
        ObservableList<Event> listOfEvents = FXCollections.observableArrayList();    
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LoadUnitTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream("XMLFiles/EventInfo.xml");
        Document doc = null;
        try {
            doc = builder.parse(is);
        } catch (SAXException ex) {
            Logger.getLogger(LoadUnitTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadUnitTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList nList = doc.getElementsByTagName("event");
        
        //-------------------------------------------------------------------
        //code below uses xml, comment out if using database     
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                priority = eElement.getElementsByTagName("priority").item(0).getTextContent();
                time = eElement.getElementsByTagName("time").item(0).getTextContent();
                evtNumber = eElement.getElementsByTagName("evtNumber").item(0).getTextContent();
                type = eElement.getElementsByTagName("type").item(0).getTextContent();
                location = eElement.getElementsByTagName("location").item(0).getTextContent();
                informantName = eElement.getElementsByTagName("informantName").item(0).getTextContent();
                headline = eElement.getElementsByTagName("headline").item(0).getTextContent();
                remarks = eElement.getElementsByTagName("remarks").item(0).getTextContent();
                evt = new Event(priority, time, evtNumber, type, location, informantName, headline, remarks);
                listOfEvents.add(evt);
            }
        }      
        //-------------------------------------------------------------------
        //Code below reads database instead of hard code
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
            
            rs = stmt.executeQuery( "select * from event");
            
            ps = conn.prepareStatement(
                    "select event_type_name from event "
                    + "inner join event_type "
                    + "on event_type_fk = event_type_id "
                    + "where event_num = ?"
                    );
            while( rs.next() ) {
                priority = rs.getString("priority");
                time = rs.getString("event_time");
                evtNumber = rs.getString("event_num");
                ps.setString( 1, evtNumber );
                rs2 = ps.executeQuery();
                rs2.first();
                type = rs2.getString("event_type_name");
                location = rs.getString("event_location");
                evtList = new Event(priority, time, evtNumber, type, location);
                listOfEvents.add(evtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //-------------------------------------------------------------------
        
        //-------------------------------------------------------------------
        return listOfEvents;  
    }
}