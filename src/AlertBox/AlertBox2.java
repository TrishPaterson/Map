/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlertBox;

import EventWindow.LoadEventWindow;
import LogWindow.LoadLogWindow;
import MainMenu.LoadMainMenu;
import MapHTML.LoadMap;
import PendingTableView.LoadPendingTable;
import UnitTableView.LoadUnitTable;
import UnitTableView.TimeArrival;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import map.Main;

public class AlertBox2 extends Application {
    private LoadUnitTable unitTable = new LoadUnitTable();
    private LoadEventWindow evtWindow = new LoadEventWindow();
    private LoadMap mapWindow = new LoadMap();
    private LoadPendingTable pendingTable = new LoadPendingTable();
    private LoadLogWindow logWindow = new LoadLogWindow();
    private Main main = new Main();
    private LoadMainMenu menu = new LoadMainMenu();
    private TimeArrival cd = new TimeArrival();
    
    private Stage alertWindow;
    private Text actionStatus;
    private static double xOffset = 0;
    private static double yOffset = 0;
    
    @Override
    public void start(Stage primaryStage) {
        alertWindow = new Stage();
        alertWindow.initStyle(StageStyle.TRANSPARENT);
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("Cordon Training Tool");
        
        Label label = new Label("ERROR! You must clear the map before starting the event.");
        label.setTextFill(Color.LIGHTGRAY);
        label.setFont(Font.font("Calibri", 16));
        label.setTranslateX(60); //w
        label.setTranslateY(20); //h

        Label label2 = new Label("The clear button is located on the User Log Window");
        label2.setTextFill(Color.LIGHTGRAY);
        label2.setFont(Font.font("Calibri", 16));
        label2.setTranslateX(60); //w
        label2.setTranslateY(40); //h
        
        //CP Image
        Image image = new Image("/Images/NCP.PNG");
        ImageView TIcon = new ImageView();
        TIcon.setImage(image);
        TIcon.setFitWidth(45);
        TIcon.setPreserveRatio(true);
        TIcon.setSmooth(true);
        TIcon.setCache(true);
        TIcon.setTranslateX(5);
        TIcon.setTranslateY(-28);
        
        //Exit Image
        ImageView Exit = new ImageView("/Images/ExitButton2.PNG");
        Exit.setFitHeight(18);
        Exit.setFitWidth(18);
        Exit.setTranslateX(425);
        Exit.setTranslateY(-25);
        
        Button btn = new Button();
        btn.setText("   OK   ");
        btn.setTranslateX(200); //w
        btn.setTranslateY(100); //h      
        btn.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                alertWindow.close();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);    
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);  
        
        Image background = new Image("/Images/EventBackG.jpg");
        VBox vbox = new VBox(-15);   
        vbox.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        vbox.setPadding(new Insets(12, 0, 15, 0));
        vbox.getChildren().addAll(btn, label,label2,Exit,TIcon, actionStatus);
        Scene scene = new Scene(vbox, 450,150); // w x h 
        alertWindow.setScene(scene);
        alertWindow.show();
        
        //Positioning the window on the screen
        //Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        //alertWindow.setX((primScreenBounds.getWidth() - alertWindow.getWidth()) /5); 
        //alertWindow.setY((primScreenBounds.getHeight() - alertWindow.getHeight()) /5);      
        
        //Exit Button
        Exit.setOnMouseClicked((MouseEvent t) -> {
            alertWindow.close();
        });    
        
        alertWindow.getScene().setOnMousePressed((MouseEvent event) -> {
            xOffset = alertWindow.getX() - event.getScreenX();
            yOffset = alertWindow.getY() - event.getScreenY();
        });
        alertWindow.getScene().setOnMouseDragged((MouseEvent event) -> {
            alertWindow.setX(event.getScreenX() + xOffset);
            alertWindow.setY(event.getScreenY() + yOffset);            
        });         
    }
}
