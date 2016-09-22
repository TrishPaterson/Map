package MapHTML;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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
        Mapwindow.setScene(new Scene(browser,1000,700, Color.web("#666970")));
        Mapwindow.getIcons().add(new Image("/MapHTML/NCP.PNG"));
        Mapwindow.show();
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
}
