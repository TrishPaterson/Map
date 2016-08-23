import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(getClass().getResource("HelloWorld.html").toString());
        
        
        Button btn = new Button();
        btn.setText("Click me!!!");
        btn.setOnAction(new EventHandler<ActionEvent>() {        
            @Override
            public void handle(ActionEvent event) {
                String msg = (String)webEngine.executeScript("hello()");
                System.out.println(msg);
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
