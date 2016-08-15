/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine; 
import javafx.scene.web.WebView;

/**
 *
 * @author Tricia Paterson
 */
public class Map extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        // create web engine and view
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(getClass().getResource("mapHtmlFile.html").toString());
        // create scene
        primaryStage.setTitle("Web Map");
        Scene scene = new Scene(browser,1000,700, Color.web("#666970"));
        primaryStage.setScene(scene);
        // show stage
        primaryStage.show();
    }
 
    static { // use system proxy settings when standalone application
        System.setProperty("java.net.useSystemProxies", "true");
    }
 
    public static void main(String[] args){
        Application.launch(args);
    }
  }
  
