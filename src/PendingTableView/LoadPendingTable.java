package PendingTableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import map.DataBaseConn;

public class LoadPendingTable extends Application {
    private static String priority;
    private static String time;
    private static String evtNumber;
    private static String type;
    private static String location;
     private static Event evtList = null;
     
    private TableView table;
    private Text actionStatus;
    private static double xOffset = 0;
    private static double yOffset = 0;
    private Stage pendingWindow;

    @Override
    public void start(Stage primaryStage) {
        createTable();
        
        pendingWindow = new Stage();
        pendingWindow.initStyle(StageStyle.UNDECORATED);
        pendingWindow.getIcons().add(new Image("/Images/NCP.PNG"));
        
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
        Scene scene = new Scene(vbox, 600,300); // w x h
         
        pendingWindow.setScene(scene);
        pendingWindow.show();
        
        //Positioning the window on the screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        pendingWindow.setX((primScreenBounds.getWidth() - pendingWindow.getWidth()) /100); 
        pendingWindow.setY((primScreenBounds.getHeight() - pendingWindow.getHeight()) / 1); 
        
        pendingWindow.getScene().setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                xOffset = pendingWindow.getX() - event.getScreenX();
                yOffset = pendingWindow.getY() - event.getScreenY();
            }
        });
        pendingWindow.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent event) { 
                pendingWindow.setX(event.getScreenX() + xOffset);
                pendingWindow.setY(event.getScreenY() + yOffset);
            } 
        }); 
        
        //Minimize Button
        Min.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                pendingWindow.setIconified(true);
            }     
        });
        
        //Exit Button
        Exit.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent t){
               System.exit(0);
           }       
        });  
    }
    
    public void createTable(){
        table = new TableView<>();                
        TableColumn prioityCol = new TableColumn("Prioity");
        prioityCol.setCellValueFactory(new PropertyValueFactory("priority"));
        TableColumn timeCol = new TableColumn("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory("time"));
        TableColumn eventNumberCol = new TableColumn("Event Number");
        eventNumberCol.setCellValueFactory(new PropertyValueFactory("evtNumber"));
        TableColumn TypeCol = new TableColumn("Type");
        TypeCol.setCellValueFactory(new PropertyValueFactory("type"));
        TableColumn locationCol = new TableColumn("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory("location"));
        
        table.setItems(getEvtList());
        table.getColumns().setAll(prioityCol,timeCol,eventNumberCol,TypeCol,locationCol);    
        table.setPrefWidth(450);
        table.setPrefHeight(225);
        table.setTranslateX(0);
        table.setTranslateY(24);    
        
        
        //double click function       
        //Either of the double click function works I just dont know which one is better.

        table.setOnMouseClicked(event -> {
            ObservableList<Event> evtSelected;
            evtSelected = table.getSelectionModel().getSelectedItems(); 
            if (event.getClickCount() == 2 && (! evtSelected.isEmpty()) ) {
                //Temporary
                evtList = evtSelected.get(0);
                System.out.println( populateFields( evtList ) );
                //System.out.println(evtSelected.get(0).getEvtNumber());
            }
        });
        
        //Alternative
        /*table.setRowFactory( tv -> {
            TableRow<EventList> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Event rowData = row.getItem();
                    //Temporary
                    System.out.println(rowData.getEvtNumber());
                }
            });
            return row ;
        });*/
    }
    
    public ObservableList<Event> getEvtList(){       
        ObservableList<Event> listOfEvents = FXCollections.observableArrayList();    
        
        //-------------------------------------------------------------------
        //code below is a hardcoded event, comment out if using database
        evtList = new Event("High", "12:00", "B1204", "Assault", "Gotham");
        listOfEvents.add(evtList);
        //-------------------------------------------------------------------
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
    //this method will eventually go in the LoadEventTable.java
    public String populateFields( Event el ) {
        //this method just prints things in the mean time.
        //String headline;
        //String informantName;
        //String remarks;
        //String remarksField;
        priority = evtList.getPriority();
        time = evtList.getTime();
        evtNumber = evtList.getEvtNumber();
        type = evtList.getType();
        location = evtList.getLocation();
        
        String output;
        output = ("Event number: " + evtNumber
                        + "\nEvent time: " + time
                        + "\nEvent priority: " + priority
                        + "\nEvent type: " + type
                        + "\nEvent location: " + location);
        return output;
    }
}