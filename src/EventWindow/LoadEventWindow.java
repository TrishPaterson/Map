package EventWindow;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadEventWindow extends Application {

    private TableView table;
    public TextField ListingInput;
    private Text actionStatus;
    private static double xOffset = 0;
    private static double yOffset = 0;
    private Stage eventWindow;

    @Override
    public void start(Stage primaryStage) {    
        eventWindow = new Stage();
        eventWindow.initStyle(StageStyle.UNDECORATED);
        eventWindow.getIcons().add(new Image("/Images/NCP.PNG"));
        eventWindow.setTitle("Unit Table");

        Label label = new Label("Event");
        label.setTextFill(Color.LIGHTGRAY);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
        label.setTranslateX(-20);
        label.setTranslateY(-340);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);

        Label TypeE = new Label("Type of Event:");
        TypeE.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        TypeE.setTextFill(Color.DARKGRAY);
        grid.add(TypeE, 0, 1);
        TextField userTextField = new TextField();
        userTextField.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(userTextField, 2, 1);

        Label loc = new Label("Location:");
        loc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        loc.setTextFill(Color.DARKGRAY);
        grid.add(loc, 0, 2);
        TextField locBox = new TextField();
        locBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(locBox, 2, 2);

        Label NameInfo = new Label("Name of Information:");
        NameInfo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        NameInfo.setTextFill(Color.DARKGRAY);
        grid.add(NameInfo, 0, 3);
        TextField NameInfoBox = new TextField();
        NameInfoBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(NameInfoBox, 2, 3);

        Label Headline = new Label("HeadLine");
        Headline.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        Headline.setTextFill(Color.DARKGRAY);
        grid.add(Headline, 0, 4);
        TextField HeadlineBox = new TextField();
        HeadlineBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        HeadlineBox.setTranslateX(145);
        grid.add(HeadlineBox, 1, 4);

        Label Remarkz = new Label("Remarks");
        Remarkz.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        Remarkz.setTextFill(Color.DARKGRAY);
        grid.add(Remarkz, 0, 6);

        Image image1 = new Image("/Images/NCP.PNG");
        ImageView TIcon = new ImageView();
        TIcon.setImage(image1);
        TIcon.setFitWidth(45);
        TIcon.setPreserveRatio(true);
        TIcon.setSmooth(true);
        TIcon.setCache(true);
        TIcon.setTranslateX(-85);
        TIcon.setTranslateY(-308);

        ImageView Exit = new ImageView("/Images/ExitButton2.PNG");
        Exit.getStyleClass().add("ImageView");
        Exit.setFitHeight(18);
        Exit.setFitWidth(18);
        Exit.setTranslateX(488);
        Exit.setTranslateY(-276);
        
        Exit.setOnMouseClicked(new EventHandler<MouseEvent>() {        
        @Override
        public void handle(MouseEvent t) {
                System.exit(0);
            }
        });

        ImageView Min = new ImageView("/Images/minimizeButton1.PNG");
        Min.getStyleClass().add("ImageView");
        Min.setFitHeight(18);
        Min.setFitWidth(18);
        Min.setTranslateX(469);
        Min.setTranslateY(-294);

        Min.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                eventWindow.setIconified(true);
            }
        });
        
        table = new TableView<>();
        table.setItems(getRemarkList());

        TableColumn RemarksColumn = new TableColumn("");
        RemarksColumn.setCellValueFactory(new PropertyValueFactory("Listing"));

        //listing input
        ListingInput = new TextField();
        ListingInput.setPromptText("Add Remarks");
        ListingInput.setMinWidth(150);

        //Button to add Remarks
        Button addButton = new Button("Add");
        addButton.setPrefWidth(100);

        addButton.setOnAction(e -> addButtonClicked());

        Button deleteR = new Button("Delete");
        deleteR.setPrefWidth(100);
        deleteR.setOnAction(e -> deleteButtonClicked());

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(-200, 100, 100, 10));
        hbox.setSpacing(10);
        hbox.setTranslateX(-10);
        hbox.setTranslateY(120);
        hbox.getChildren().addAll(ListingInput, addButton, deleteR);

        table.getColumns().setAll(RemarksColumn);
        table.setPrefWidth(10);
        table.setPrefHeight(500);
        table.setTranslateX(0);
        table.setTranslateY(-100);

        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);

        Image image = new Image("/Images/EventBackG.jpg");
        VBox vbox = new VBox(0);
        vbox.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
        vbox.setPadding(new Insets(100, 120, 20, 90));
        vbox.getChildren().addAll(grid, Exit, Min, TIcon, actionStatus, label, table, hbox);
        Scene scene = new Scene(vbox, 600, 550); // w x h

        eventWindow.setScene(scene);
        eventWindow.show();
        
        //Positioning the window on the screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        eventWindow.setX((primScreenBounds.getWidth() - eventWindow.getWidth()) /100); 
        eventWindow.setY((primScreenBounds.getHeight() - eventWindow.getHeight()) / 100); 
        
        eventWindow.getScene().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
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
    }

    //add button clicked
    public void addButtonClicked(){
        System.out.println(ListingInput.getText());
        RemarkList remarkList = new RemarkList();
        remarkList.setListing(ListingInput.getText());
        table.getItems().add(remarkList);
        ListingInput.clear();
    }

    public void deleteButtonClicked() {
        ObservableList<RemarkList> remarkListSelected, allRemarkLists;
        allRemarkLists = table.getItems();
        remarkListSelected = table.getSelectionModel().getSelectedItems();
        remarkListSelected.forEach(allRemarkLists::remove);
    }

    public ObservableList<RemarkList> getRemarkList() {
        ObservableList<RemarkList> remarks = FXCollections.observableArrayList();
        remarks.add(new RemarkList("- Police initially said foul play was not suspected"));
        remarks.add(new RemarkList("- It’s not a murder case….it’s could be a suicide"));
        return remarks;
    }
}
