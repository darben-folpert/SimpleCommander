import java.util.Optional;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

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
        //TODO: logging of message "application starting..."
    }

    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle(this.appName + " v" + this.appVersion);

        createScenes();

        window.setScene(scene1);
        window.setMinHeight(80);
        window.setMinWidth(120);
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
        return new Scene(anchorPane, 640, 480);
    }

    private AnchorPane createAnchorPane()
    {
        GridPane grid = createGrid();
        Button exitButton = createExitButton();

        AnchorPane anchorPane = new AnchorPane();
        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(exitButton);
        anchorPane.getChildren().addAll(grid, hb);

        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);
        AnchorPane.setTopAnchor(grid, 10.0);
        return anchorPane;
    }

    private Button createExitButton()
    {
        Button exitButton = new Button("Quit");
        exitButton.setOnAction(new ClickExitButtonEventHandler());
        return exitButton;
    }

    private GridPane createGrid()
    {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setGridLinesVisible(true);
        grid.setPadding(new Insets(10, 10, 10, 10));
        return grid;
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

