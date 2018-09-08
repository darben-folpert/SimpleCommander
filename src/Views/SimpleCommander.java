import java.util.Optional;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.geometry.Insets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SimpleCommander extends Application
{
    private final String appVersion = "0.1";
    private final String appName = "SimpleCommander";
    private Stage window;
    private Scene scene1;

    public static void main(String[] args) throws Exception
    {
        System.out.println("===> Starting application");
        launch(args);
    }

    public void init()
    {
        //TODO: Add some logging of messages (warnings, errors)
    }

    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle(this.appName + " v" + this.appVersion);

        createScenes();

        window.setScene(scene1);
        window.setMinHeight(240);
        window.setMinWidth(320);
        window.setOnCloseRequest(new ClosingEventHandler());
        window.show();
    }

    private void createScenes()
    {
        scene1 = createMainScene();
    }

    private Scene createMainScene()
    {
        AnchorPane anchorPane = createAnchorPane();
        return new Scene(anchorPane, 640, 680);
    }

    private AnchorPane createAnchorPane()
    {
        VBox vbox = createVbox();
        createSearchControls(vbox);
        createTabs(vbox);
        createResultArea(vbox);

        AnchorPane anchorPane = new AnchorPane();
        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        Button exitButton = createExitButton();
        hb.getChildren().addAll(exitButton);
        anchorPane.getChildren().addAll(vbox, hb);

        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);
        AnchorPane.setTopAnchor(vbox, 10.0);
        AnchorPane.setLeftAnchor(vbox, 10.0);
        return anchorPane;
    }

    private Button createExitButton()
    {
        Button exitButton = new Button("Quit");
        exitButton.setOnAction(new ClickExitButtonEventHandler());
        return exitButton;
    }

    private VBox createVbox()
    {
        VBox vbox = new VBox();
        vbox.setSpacing(30);
        return vbox;
    }

    private void createSearchControls(Pane parent)
    {
        HBox hb = new HBox();
        hb.setSpacing(10);

        Label labelSearch = new Label("Find text");
        TextField txtSearch = new TextField();
        txtSearch.setPromptText("Enter the text to find");

        hb.getChildren().addAll(labelSearch, txtSearch);
        //hb.setMaxWidth(Double.MAX_VALUE);
        parent.getChildren().add(hb);
    }

    private void createTabs(Pane parent)
    {
        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-background-color: lightblue");
        Tab tabSearchInFolder = new TabSearchFileContentInFolder("Search in folder");
        Tab tabSearchInFiles = new TabSearchFileContentInFiles("Search in list of files");
        tabPane.getTabs().add(tabSearchInFolder);
        tabPane.getTabs().add(tabSearchInFiles);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        parent.getChildren().add(tabPane);
    }

    private void createResultArea(Pane parent)
    {
        Label lblResult = new Label("Search result:");
        TextArea txtResult = new TextArea();
        parent.getChildren().addAll(lblResult, txtResult);
    }

    private void closingWindow()
    {
        Alert alertBox = new Alert(AlertType.CONFIRMATION, "Do you really want to quit ?");
        alertBox.setTitle("Quit");
        Optional<ButtonType> userChoice = alertBox.showAndWait();
        if (userChoice.isPresent() && userChoice.get() == ButtonType.OK)
            window.close();
    }

    // Event handler: clic on the Exit button
    private class ClickExitButtonEventHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            closingWindow();
        }
    }

    // Event handler: closing the main window (this view)
    private class ClosingEventHandler implements EventHandler<WindowEvent>
    {
        public void handle(WindowEvent event)
        {
            event.consume();
            closingWindow();
        }
    }
}

