/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventWindow;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javafx.application.Application.launch;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class LoadEventWindow extends Application {
    
    private static double xOffset = 0;
    private static double yOffset = 0;
    private Stage eventWindow;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        eventWindow = new Stage();
        eventWindow.setTitle("Event Window");
        eventWindow.initStyle(StageStyle.TRANSPARENT);
        eventWindow.getIcons().add(new Image("/Images/NCP.PNG"));
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(-20, 25, 25, 25));
        Image image = new Image("/Images/EventBackG.jpg");
        grid.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
        BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

          //CP Image
        Image image1 = new Image("/Images/NCP.PNG");
        ImageView TIcon = new ImageView();
        TIcon.setImage(image1);
        TIcon.setFitWidth(45);
        TIcon.setPreserveRatio(true);
        TIcon.setSmooth(true);
        TIcon.setCache(true);
        TIcon.setTranslateX(-16);
        TIcon.setTranslateY(-18);
        
        TIcon.setOnMouseDragged(e -> { 
            eventWindow.setWidth(e.getScreenX() - eventWindow.getX()); 
            eventWindow.setHeight(e.getScreenY() - eventWindow.getY()); 
        }); 

        //Exit Image
        ImageView Exit = new ImageView("/Images/ExitButton2.PNG");
        Exit.getStyleClass().add("ImageView");
        Exit.setFitHeight(18);
        Exit.setFitWidth(14);
        Exit.setTranslateX(450);
        Exit.setTranslateY(-30);
        Exit.setStyle(
            "-fx-font-family: monospace; -fx-font-size: 100px; -fx-text-fill: olive"
        );

        ImageView Minimize = new ImageView("/Images/minimizeButton1.PNG");
        Minimize.getStyleClass().add("ImageView");
        Minimize.setFitHeight(18);
        Minimize.setFitWidth(15);
        Minimize.setTranslateX(430);
        Minimize.setTranslateY(-30);

        Label scenetitle = new Label("Event");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        scenetitle.setTranslateX(60);
        scenetitle.setTranslateY(0);

        Label TypeE = new Label("Type of Event:");
        TypeE.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        TypeE.setTextFill(Color.DARKGRAY);
        grid.add(TypeE, 0, 1);
        TextField userTextField = new TextField();
        userTextField.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(userTextField, 1, 1);

        Label loc = new Label("Location:");
        loc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        loc.setTextFill(Color.DARKGRAY);
        grid.add(loc, 0, 2);
        TextField locBox = new TextField();
        locBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(locBox, 1, 2);

        Label NameInfo = new Label("Name of Information:");
        NameInfo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        NameInfo.setTextFill(Color.DARKGRAY);
        grid.add(NameInfo, 0, 3);
        TextField NameInfoBox = new TextField();
        NameInfoBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(NameInfoBox, 1, 3);

        Label Headline = new Label("HeadLine");
        Headline.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        Headline.setTextFill(Color.DARKGRAY);
        grid.add(Headline,0,4);
        TextField HeadlineBox = new TextField();
        HeadlineBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(HeadlineBox, 1, 4);

        Label Remarks = new Label("Remarks");
        Remarks.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        Remarks.setTextFill(Color.DARKGRAY);
        grid.add(Remarks,0,5);
        TextArea RemarkBox = new TextArea();
        RemarkBox.setPrefSize(260,140);
        RemarkBox.setTranslateX(160);
        RemarkBox.setTranslateY(300);
       
        Button btn = new Button("Load");   
        btn.setTranslateX(380);
        btn.setTranslateY(400);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);        
        grid.getChildren().addAll(btn,TIcon,Exit,Minimize,RemarkBox,scenetitle);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {        
                System.out.println("DO Testing or your DEAD!");
            }
        });
        
        Scene scene = new Scene(grid, 500, 550);
        eventWindow.setScene(scene);
        eventWindow.show();
        
        //Positioning the window on the screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        eventWindow.setX((primScreenBounds.getWidth() - eventWindow.getWidth()) /100); 
        eventWindow.setY((primScreenBounds.getHeight() - eventWindow.getHeight()) / 100);   
        
         eventWindow.getScene().setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                xOffset = eventWindow.getX() - event.getScreenX();
                yOffset = eventWindow.getY() - event.getScreenY();
            }
        });
        eventWindow.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent event) { 
                eventWindow.setX(event.getScreenX() + xOffset);
                eventWindow.setY(event.getScreenY() + yOffset);
            } 
        }); 
        
        Minimize.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                eventWindow.setIconified(true);
            }     
        });
        
        //Exit Button
        Exit.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent t){
              eventWindow.close();
           }       
        });           
    }
}

   
    