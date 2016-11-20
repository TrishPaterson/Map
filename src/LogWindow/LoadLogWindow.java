package LogWindow;

import MapHTML.LoadMap;
import PendingTableView.LoadPendingTable;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadLogWindow extends Application {
    private ObservableList<Message> log = FXCollections.observableArrayList();;
    private static TableView table;
    public TextField ListingInput;
    private Text actionStatus;
    private static double xOffset = 0;
    private static double yOffset = 0;
    private static Stage logWindow;
    private TableColumn<Message, String> timeColumn;
    private TableColumn<Message, String> msgCol;
    
    private LoadMap mapEngine = new LoadMap();
    private static ArrayList<ArrayList<String>> events = new ArrayList<>();
    private static ArrayList<Double>radius = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>>markerId = new ArrayList<>();
    private ArrayList<String> markers = new ArrayList<>();
    private static ComboBox<String> comboBox;
    private Button button;
    private Button button2;
    private static boolean isReplayOn = false;
    private LoadPendingTable lpt = new LoadPendingTable();
    
    @Override
    public void start(Stage primaryStage) {
        logWindow = new Stage();
        logWindow.initStyle(StageStyle.UNDECORATED);
        logWindow.getIcons().add(new Image("/Images/NCP.PNG"));
        logWindow.setTitle("Unit Table");

        Label label = new Label("User Log");
        label.setTextFill(Color.LIGHTGRAY);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
        label.setTranslateX(55);
        label.setTranslateY(-147);

        Image image1 = new Image("/Images/NCP.PNG");
        ImageView TIcon = new ImageView();
        TIcon.setImage(image1);
        TIcon.setFitWidth(45);
        TIcon.setPreserveRatio(true);
        TIcon.setSmooth(true);
        TIcon.setCache(true);
        TIcon.setTranslateX(0);
        TIcon.setTranslateY(-110);

        ImageView Min = new ImageView("/Images/minimizeButton1.PNG");
        Min.getStyleClass().add("ImageView");
        Min.setFitHeight(18);
        Min.setFitWidth(18);
        Min.setTranslateX(600);
        Min.setTranslateY(-94);

        Min.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                logWindow.setIconified(true);
            }
        });
        
        table = new TableView<>();
        timeColumn = new TableColumn<>("Time");  
        timeColumn.setCellValueFactory(new PropertyValueFactory("time"));
        //timeColumn.prefWidthProperty().bind(table.widthProperty().multiply(1.0));
        msgCol = new TableColumn<>("Log");  
        msgCol.setCellValueFactory(new PropertyValueFactory("msgLog"));
        msgCol.prefWidthProperty().bind(table.widthProperty().multiply(1.0));
        

        table.getColumns().addAll(timeColumn,msgCol);
        table.setPrefWidth(10);
        table.setPrefHeight(550);
        table.setTranslateX(0);
        table.setTranslateY(-160);

        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);

        button = new Button("Generate");
        button.setTranslateX(260); //w
        button.setTranslateY(305); //h
        
        button2 = new Button("Clear");
        button2.setTranslateX(340); //w
        button2.setTranslateY(280); //h
        
        comboBox = new ComboBox<>();
        comboBox.setPromptText("Choose event");
        comboBox.setPrefWidth(250);
        comboBox.setTranslateX(0);
        comboBox.setTranslateY(-140);
        
        Image image = new Image("/Images/EventBackG.jpg");
        VBox vbox = new VBox(0);
        vbox.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        vbox.setPadding(new Insets(100, 20, -70, 20));
        vbox.getChildren().addAll(Min, TIcon, actionStatus, label, button, button2, table, comboBox);
        Scene scene = new Scene(vbox, 650,600); 

        logWindow.setScene(scene);
        logWindow.show();

        //Positioning the window on the screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        logWindow.setX((primScreenBounds.getWidth() - logWindow.getWidth()) / 2.5);
        logWindow.setY((primScreenBounds.getHeight() - logWindow.getHeight()) / 100);

        logWindow.getScene().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = logWindow.getX() - event.getScreenX();
                yOffset = logWindow.getY() - event.getScreenY();
            }
        });

        logWindow.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logWindow.setX(event.getScreenX() + xOffset);
                logWindow.setY(event.getScreenY() + yOffset);
            }
        });
        button.setOnAction(e -> runPlayBackFeature());
        button2.setOnAction(e -> clearMap());
    }
    
    private void clearMap(){
        if(!lpt.getIsEventOn()){
            mapEngine.refreshMap();
            isReplayOn = false;
        }
    }
    
    public void addMarkerToEventArr(ArrayList<String> markers){
        events.add(markers);
    }
    
    private void runPlayBackFeature(){   
        int index = comboBox.getSelectionModel().getSelectedIndex();
        if(index != -1 && !lpt.getIsEventOn()){
            isReplayOn = true;
            for(int i = 0; i < events.get(index).size(); i++){
                String string = events.get(index).get(i);
                String[] parts = string.split(",");
                String lat = parts[0]; 
                String lng = parts[1];   

                mapEngine.addMarkerToArray( Float.parseFloat(lat), Float.parseFloat(lng), markerId.get(index).get(i));
            }
            
            if("Bolton Street, Petone".equals(comboBox.getSelectionModel().getSelectedItem().toString()))
                mapEngine.placeMarkers((float)-41.227346,(float)174.8830833, radius.get(index));
            else
                mapEngine.placeMarkers((float)-41.3146835,(float)174.7806215, radius.get(index));
        }
    }
    
    public void deleteRadius(){
        radius.clear();
    }
    
    public void deleteEvents(){
        events.clear();
    }
    
    public void deleteMarkerId(){
        markerId.clear();
    }
    
    public void addMarkerId(ArrayList<Integer> id){
        markerId.add(id);
    }
    
    public void setPlayBackOff(){
        isReplayOn = false;
    }
    
    public boolean isPlayBackOn(){
        return isReplayOn;
    }
    
    public void addRadius(double radius){
        this.radius.add(radius);
    }
    
    public void addItemToComboBox(String eventName){
        comboBox.getItems().add(eventName);
    }
    
    public ArrayList<String> getArrayList(){
        return markers;
    }
    
    public void addMarkers(String marker){
        this.markers.add(marker);
    }
    
    public String getMarkers(int makerId){
        return this.markers.get(makerId);
    }
    
    public int getMarkerSize(){
        return markers.size();
    }
    
    public Stage getStage(){
        return logWindow;
    }
    
    public void clearTable(){
        table.getItems().clear();
    }
    
    public void addLog(String msg, String time){
        log.add(new Message(msg,time));
        table.setItems(log);
        //table.getItems().add(log);  
    }
}
