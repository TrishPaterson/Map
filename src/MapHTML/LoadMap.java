package MapHTML;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoadMap extends Application {
    private Stage Mapwindow;
    private static WebView browser = new WebView();
    private static WebEngine webMapEngine = browser.getEngine();
    
    @Override
    public void start(Stage primaryStage) {
        //Load Map
        webMapEngine.load(getClass().getResource("mapHtmlFile.html").toString());
        Mapwindow = new Stage();
        Mapwindow.setTitle("Web Map");
        Mapwindow.setScene(new Scene(browser,1300,1100, Color.web("#666970")));
        Mapwindow.getIcons().add(new Image("/Images/NCP.PNG"));
        Mapwindow.show();
        
        //Positioning the window on the screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        Mapwindow.setX((primScreenBounds.getWidth() - Mapwindow.getWidth()) /1); 
        Mapwindow.setY((primScreenBounds.getHeight() - Mapwindow.getHeight()) / 1);
    }
    
    public void setMarkerId(int id){
        webMapEngine.executeScript("setMarkerId(" + id + ")");  
    }
    
    public void createMarker(){
        webMapEngine.executeScript("createMarker()");
    }
    
    public void changeLocation(int id){
        System.out.println(webMapEngine.executeScript("changeLocation(" + id + ")"));
    }
    
    public void createExpanding(double lat, double lng, int rad){
        webMapEngine.executeScript("createExpanding(" + lat + ',' + lng + ',' + rad + ")"); //',' + currEvent 
    }
    
    public void createEvent(String eventName){
        webMapEngine.executeScript("createEvent(" + "'"+eventName +"'"+ ")");
    }
    
    public void setEvent(String eventName){
        webMapEngine.executeScript("setEvent(" + "'"+eventName +"'"+ ")");        
    }
}
