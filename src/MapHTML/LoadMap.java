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
    private static Stage Mapwindow;
    private static WebView browser;
    private static WebEngine webMapEngine;
    
    @Override
    public void start(Stage primaryStage) {
        //Load Map
        browser = new WebView();
        webMapEngine = browser.getEngine();
        webMapEngine.load(getClass().getResource("mapHtmlFile.html").toString());
        Mapwindow = new Stage();
        Mapwindow.setTitle("Web Map");
        Mapwindow.setScene(new Scene(browser,700,800, Color.web("#666970")));
        Mapwindow.getIcons().add(new Image("/Images/NCP.PNG"));
        Mapwindow.show();
        
        //Positioning the window on the screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        Mapwindow.setX((primScreenBounds.getWidth() - Mapwindow.getWidth()) /1); 
        Mapwindow.setY((primScreenBounds.getHeight() - Mapwindow.getHeight()) / 1);
    }
    
    public Stage getStage(){
        return Mapwindow;
    }
    
    public void setMarkerId(int id){
        webMapEngine.executeScript("setMarkerId(" + id + ")");  
    }
    
    public void createMarker(){
        webMapEngine.executeScript("createMarker()");
    }
    
    public void changeLocation(int id){
        webMapEngine.executeScript("changeLocation(" + id + ")");
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
    public void startTracking(){
        webMapEngine.executeScript("startTracking()");      
    }
    
    public String getEvent(){
        return (String)webMapEngine.executeScript("getEvent()");        
    }
    
    public String getCordonCurrLocation(int id){
        return (String)webMapEngine.executeScript("getCordonCurrLocation(" + id + ")");
    }   
    
    public boolean getDogHandlerStatus(){
        return (boolean)webMapEngine.executeScript("getDogHandlerStatus()");
    }
    
    public void refreshMap(){
        webMapEngine.executeScript("refreshMap()");
    }
    
    public boolean getCountaintmentFieldStatus(){
        return (boolean)webMapEngine.executeScript("getCountaintmentFieldStatus()");
    }
    
    public void createContainmentField(){
        webMapEngine.executeScript("createContainmentField()");
    }
    
    public void removeContainmentField(){
        webMapEngine.executeScript("removeContainmentField()");
    }
    
    public void addMarkerToArray(float lat, float lng, int id){
        webMapEngine.executeScript("addMarkerToArray(" + lat + "," + lng + "," + id +")");
    }
    
    public void placeMarkers(float lat, float lng, double rad){
        webMapEngine.executeScript("placeMarkers(" + lat + "," + lng + "," + rad + ")");
    }
    
    public double getRadius(){
        return(double)webMapEngine.executeScript("getRad()");
    }
}
