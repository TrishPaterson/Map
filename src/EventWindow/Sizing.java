/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventWindow;

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.Label; 
import javafx.scene.layout.AnchorPane; 
import javafx.stage.Stage; 
import javafx.stage.StageStyle; 

public class Sizing extends Application { 
    public void start(Stage primaryStage) throws Exception { 
primaryStage.initStyle(StageStyle.UNDECORATED); 

Label label = new Label("Drag me to resize"); 
AnchorPane.setRightAnchor(label, 0.0); 
AnchorPane.setBottomAnchor(label, 0.0); 
label.setOnMouseDragged(e -> { 
primaryStage.setWidth(e.getScreenX() - primaryStage.getX()); 
primaryStage.setHeight(e.getScreenY() - primaryStage.getY()); 
}); 

primaryStage.setScene(new Scene(new AnchorPane(label))); 
primaryStage.setWidth(400); 
primaryStage.setHeight(300); 
primaryStage.show(); 
    } 

    public static void main(String[] args) { 
launch(args); 
    } 
} 
