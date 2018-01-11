import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SimpleCommander extends Application
{
    private final String appVersion = "0.1";
    private Stage window;
    private Scene scene1;
    private Scene scene2;

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
        window.setTitle("FileSystemXP v" + this.appVersion);

        createScenes();

        window.setScene(scene1);
        window.setOnCloseRequest(new ClosingEventHandler());
        window.show();
    }

    private void createScenes()
    {
        scene1 = createScene1();
        scene2 = createScene2();
    }

    private Button createExitButton()
    {
        Button exitButton = new Button("Quit");
        exitButton.setOnAction(new ClickExitButtonEventHandler());
        return exitButton;
    }

    private Scene createScene1()
    {
        VBox layout1 = new VBox(30);
        Label label1 = new Label("First scene here");
        Button button1 = new Button("Button 1");
        button1.setOnAction(new ChangeSceneEventHandler(2));
        Button exitButton = createExitButton();

        layout1.getChildren().add(label1);
        layout1.getChildren().add(button1);
        layout1.getChildren().add(exitButton);
        Scene scene = new Scene(layout1, 640, 480);
        return scene;
    }

    private Scene createScene2()
    {
        VBox layout2 = new VBox(30);
        Label label2 = new Label("Second scene here");
        Button button2 = new Button("Button 2");
        button2.setOnAction(new ChangeSceneEventHandler(1));
        Button exitButton = createExitButton();

        layout2.getChildren().add(label2);
        layout2.getChildren().add(button2);
        layout2.getChildren().add(exitButton);
        Scene scene = new Scene(layout2, 640, 480);
        //scene.setFill(javafx.scene.paint.Color.TRANSPARENT); -> this has no effect
        return scene;
    }

    private void closingWindow()
    {
        System.out.println("I'm gonna close that shit");
    }


// ===== Other classes, mainly event handlers
    private class ClickExitButtonEventHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            closingWindow();
            window.close();
        }
    }

    private class ChangeSceneEventHandler implements EventHandler<ActionEvent>
    {
        private int newScene;

        public ChangeSceneEventHandler(int scene)
        {
            this.newScene = scene;
        }

        public void handle(ActionEvent event)
        {
            switch (newScene)
            {
                case 1:
                    window.setScene(scene1);
                    break;
                case 2:
                    window.setScene(scene2);
                    break;
            }
        }
    }

    private class ClosingEventHandler implements EventHandler<WindowEvent>
    {
        public void handle(WindowEvent event)
        {
            closingWindow();
        }

    }
}

