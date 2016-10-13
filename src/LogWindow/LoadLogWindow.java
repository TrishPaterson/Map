package LogWindow;

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
    private Stage logWindow;
    private TableColumn<Message, String> timeColumn;
    private TableColumn<Message, String> msgCol;
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
        TIcon.setTranslateX(5);
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
        msgCol = new TableColumn<>("MsgLog");  
        msgCol.setCellValueFactory(new PropertyValueFactory("msgLog"));
        msgCol.prefWidthProperty().bind(table.widthProperty().multiply(1.0));
        

        table.getColumns().addAll(timeColumn,msgCol);
        table.setPrefWidth(10);
        table.setPrefHeight(500);
        table.setTranslateX(0);
        table.setTranslateY(-130);

        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);

        Image image = new Image("/Images/EventBackG.jpg");
        VBox vbox = new VBox(0);
        vbox.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        vbox.setPadding(new Insets(100, 20, -70, 20));
        vbox.getChildren().addAll(Min, TIcon, actionStatus, label, table);
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
    }

    //mouse clicked from placed marker should get the string 
    /*public void addMouseClicked() {
        //System.out.println(ListingInput.getText());
        getTime timeList = new getTime();
        timeList.setListing(ListingInput.getText());//change listinInput to your marker method
        table.getItems().add(timeList);
        //ListingInput.clear();
    }*/

    public void addLog(String msg, String time){
        log.add(new Message(msg,time));
        table.setItems(log);
        //table.getItems().add(log);  
    }
    
    /*public ObservableList<RemarkList> getRemarkList() {
        ObservableList<RemarkList> remarks = FXCollections.observableArrayList();
        remarks.add(new RemarkList(""));
        return remarks;
    }*/
}
