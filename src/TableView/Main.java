package TableView;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main extends Application {
    private Stage Mapwindow;
    private Stage Listwindow;
    private TableView<Units> table;
    private static String uniqID;
    private static String callSign;
    private static String defLocation;
    private static String currEvent;
    private static String time;
    private static String type;
    private static String status;
    
    private int incr = 1;
    private static Units[] x = null;
    WebView browser = new WebView();
    WebEngine webEngine = browser.getEngine();
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)throws ParserConfigurationException, SAXException, IOException{       
        //Load Map
        //WebView browser = new WebView();
        //WebEngine webEngine = browser.getEngine();
        webEngine.load(getClass().getResource("mapHtmlFile.html").toString());

        Mapwindow = new Stage();
        Mapwindow.setTitle("Web Map");
        Mapwindow.setScene(new Scene(browser,1000,700, Color.web("#666970")));
        Mapwindow.show();
        
        //Load UnitTable
        Listwindow = primaryStage;
        Listwindow.setTitle("Unit Table");

        createTable();
        createContextMenu();
        
        //StackPane root = new StackPane();
        //root.getChildren().addAll(table);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);   
        Scene scene = new Scene(vBox);
        Listwindow.setScene(scene);
        Listwindow.show();
    }
    
    public void createContextMenu(){
        ObservableList<Units> unitSelected, allUnits;   
        allUnits = table.getItems();
        unitSelected = table.getSelectionModel().getSelectedItems(); 
     
        //ContextMenu
        ContextMenu contextMenu = new ContextMenu();
        
        //menu items
        MenuItem item1 = new MenuItem("ChangeLocation");
        MenuItem item2 = new MenuItem("Dispatch");
        
        //Add item to context menu
        contextMenu.getItems().addAll(item1, item2);
        
        //set context menu to table
        table.setContextMenu(contextMenu);
  
        //changeLocationFunction
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int i = 0;
                // reminder dont forget to change x[0] 
                //this line of code is not doing anything it just prints out the javascript command
                // in the future this will be change into 
                //executeScript(x[i].changeLocation(unitSelected.get(0).getId()));
                //String msg = (String)webEngine.executeScript("changeLocation()");
                
                //webEngine.executeScript("changeLocation(" + incr++ + ")");
                String st = unitSelected.get(0).getId();
                if( st.equals("U01") )
                    i = 1;
                else if( st.equals("U02") )
                    i = 2;
                else if( st.equals("U03") )
                    i = 3;
                webEngine.executeScript("changeLocation(" + i + ")");
                //webEngine.executeScript("changeLocation(" + st +")");
                //System.out.println(unitSelected.get(0).getId());
                //System.out.println(msg);
            }
        });
        
        //dispatchLocation function
        item2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("IM Batman");
            }
        });       
    }
    
    public void createTable()throws ParserConfigurationException, SAXException, IOException{
        //ID column
        TableColumn<Units, String> uniqIdCol = new TableColumn<>("ID");
        uniqIdCol.setMinWidth(100);
        uniqIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        //Location column
        TableColumn<Units, String> currLocationCol = new TableColumn<>("Location");
        currLocationCol.setMinWidth(300);
        currLocationCol.setCellValueFactory(new PropertyValueFactory<>("currLocation"));
        //Time column
        TableColumn<Units, String> timeCol = new TableColumn<>("Time");
        timeCol.setMinWidth(100);
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        //Type column
        TableColumn<Units, String> typeCol = new TableColumn<>("Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    
        //create Table
        table = new TableView<>();
        table.setItems(getUnits());
        table.getColumns().addAll(uniqIdCol,currLocationCol,timeCol,typeCol);        
    }
    
    public ObservableList<Units> getUnits()throws ParserConfigurationException, SAXException, IOException{
        
        ObservableList<Units> unit = FXCollections.observableArrayList();    
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("Test.xml");
        NodeList nList = doc.getElementsByTagName("unit");
        
        x = new Units[nList.getLength()];
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                uniqID = eElement.getAttribute("id");
                callSign = eElement.getElementsByTagName("callSign").item(0).getTextContent();
                defLocation = eElement.getElementsByTagName("defLocation").item(0).getTextContent();
                currEvent = eElement.getElementsByTagName("currEvent").item(0).getTextContent();
                time = eElement.getElementsByTagName("time").item(0).getTextContent();
                type = eElement.getElementsByTagName("type").item(0).getTextContent();
                status = eElement.getElementsByTagName("status").item(0).getTextContent();
                x[i] = new Units(uniqID, callSign, defLocation, currEvent, time, type, status);
                unit.add(x[i]);
            }
        }
        return unit;
    }
}