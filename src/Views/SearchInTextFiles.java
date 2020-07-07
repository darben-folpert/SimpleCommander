import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

// ===== JavaFX imports - BEGIN
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Control;

import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.geometry.Insets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
// ===== JavaFX imports - END


public class SearchInTextFiles extends Application
{
    private final String appVersion = "0.3";
    private final String appName = "SearchInTxtFiles";
    private Stage window;
    private Scene scene1;

    TextField txtSearch;
    TabSearchFileContentInFolder tabSearchInFolder;
    TextArea txtResult;
    Hashtable<String, Control> uiControls;

    public static void main(String[] args) throws Exception
    {
        System.out.println("===> Starting application");
        launch(args);
    }

    public void init()
    {
        System.out.println("Calling method SimpleCommander.init");
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        System.out.println("---> inside start");
        window = primaryStage;
        window.setTitle(this.appName + " v" + this.appVersion);
        uiControls = new Hashtable<String, Control>();

        createScenes();

        window.setScene(scene1);
        window.setMinHeight(240);
        window.setMinWidth(320);
        window.setOnCloseRequest(new ClosingEventHandler());
        window.show();
    }

    @Override
    public void stop()
    {
        System.out.println("===> Closing application");
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
        uiControls.put("btnExit", exitButton);
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
        txtSearch = new TextField();
        txtSearch.setPromptText("Enter the text to find");

        hb.getChildren().addAll(labelSearch, txtSearch);
        parent.getChildren().add(hb);
        uiControls.put("txtSearch", txtSearch);
    }

    private void createTabs(Pane parent)
    {
        TabPane tabPane = new TabPane();
        uiControls.put("tabPane", tabPane);
        tabPane.setStyle("-fx-background-color: lightblue");
        tabSearchInFolder = new TabSearchFileContentInFolder("Search files content from folder");
        Tab tabSearchInFiles = new TabSearchFileContentFromFiles("Search files content from list of files");
        tabPane.getTabs().add(tabSearchInFolder);
        tabPane.getTabs().add(tabSearchInFiles);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        parent.getChildren().add(tabPane);
    }

    private void createResultArea(Pane parent)
    {
        Button searchBtn = new Button("Search");
        Label lblResult = new Label("Search result:");
        txtResult = new TextArea();
        searchBtn.setOnAction(new SearchRequestHandler(this, uiControls));
        Label lblProgress = new Label("");
        parent.getChildren().addAll(searchBtn, lblResult, txtResult, lblProgress);

        uiControls.put("btnSearch", searchBtn);
        uiControls.put("txtResult", txtResult);
        uiControls.put("lblProgress", lblProgress);
    }

    private void closingWindow()
    {
        DialogCloseWindowConfirmation confirmationDialog = new DialogCloseWindowConfirmation();
        confirmationDialog.showDialogAndWait();
        if (confirmationDialog.getCloseWindowConfirmation())
            window.close();
    }

    public String getFieldValue(int fieldIndex)
    {
        String fieldValue = "";
        if ((fieldIndex > 2) || (fieldIndex < 0))
            return fieldValue;

        switch (fieldIndex)
        {
            case 0: // folder path
                return tabSearchInFolder.getFolderPath();

            case 1: // text we search for
                return txtSearch.getText();

            case 2: // file name pattern
                return tabSearchInFolder.getFileNamePattern();

            default:
                return "";
        }
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

