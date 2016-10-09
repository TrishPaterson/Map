package NotificationWindow;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.util.Duration;

public class LoadNotification extends Application {
    private Text actionStatus;
    public Stage notificationWindow;
    private String NotificationMsg;
    public LoadNotification(String msg){
        this.NotificationMsg = msg;
    }

    @Override
    public void start(Stage primaryStage) {
        notificationWindow = new Stage();
        notificationWindow.initStyle(StageStyle.UNDECORATED);
        notificationWindow.getIcons().add(new Image("/Images/NCP.PNG"));
        notificationWindow.setTitle("Unit Table");

        Label msg = new Label(NotificationMsg);
        msg.setTextFill(Color.LIGHTGRAY);
        msg.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
        msg.setTranslateX(80); //W
        msg.setTranslateY(-100); //H
        
        Label title = new Label("Notification");
        title.setTextFill(Color.LIGHTGRAY);
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
        title.setTranslateX(-10); //W
        title.setTranslateY(-170); //H

        Image image1 = new Image("/Images/NCP.PNG");
        ImageView TIcon = new ImageView();
        TIcon.setImage(image1);
        TIcon.setFitWidth(45);
        TIcon.setPreserveRatio(true);
        TIcon.setSmooth(true);
        TIcon.setCache(true);
        TIcon.setTranslateX(-10); //W
        TIcon.setTranslateY(-50); //H

        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);

        Image image = new Image("/Images/EventBackG.jpg");
        VBox vbox = new VBox(0);
        vbox.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        vbox.setPadding(new Insets(100, 20, -70, 20));
        vbox.getChildren().addAll(TIcon, actionStatus, msg, title);
        Scene scene = new Scene(vbox, 400, 150); //W H

        notificationWindow.setScene(scene);  
        notificationWindow.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        notificationWindow.setX((primScreenBounds.getWidth() - notificationWindow.getWidth()) / 1);
        notificationWindow.setY((primScreenBounds.getHeight() - notificationWindow.getHeight()) / -5);  
        
        runNotification();       
        closeFadeOut();
    }
    
    public void closeFadeOut(){
        Runnable task = new Runnable(){
            public void run(){
                try{
                    Thread.sleep(3000); 
                    Platform.runLater(new Runnable(){ //int pos = -5;
                        @Override
                        public void run(){                           
                            Timeline timeline = new Timeline();
                             KeyFrame key = new KeyFrame(Duration.millis(2000),
                                            new KeyValue (notificationWindow.getScene().getRoot().opacityProperty(), 0)); 
                            timeline.getKeyFrames().add(key);   
                            timeline.setOnFinished((ae) -> notificationWindow.close()); 
                            timeline.play();                                                
                        }    
                    });  
                }catch(InterruptedException e){} 
            }
        };
        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();        
   }
    
    private int pos = -5;
    public void runNotification(){
        //Positioning the window on the screen
        Runnable task = new Runnable(){
            public void run(){
                try{
                    for(int i = 0; i < 20; i++){
                        Thread.sleep(40); 
                        Platform.runLater(new Runnable(){ //int pos = -5;
                            @Override
                            
                            public void run(){                           
                                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                                notificationWindow.setX((primScreenBounds.getWidth() - notificationWindow.getWidth()) / 1);
                                notificationWindow.setY((primScreenBounds.getHeight() - notificationWindow.getHeight()) / (pos += -5));                                                
                            }    
                        });  
                    }                  
                }catch(InterruptedException e){} 
            }
        };
        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();        
    }
}
