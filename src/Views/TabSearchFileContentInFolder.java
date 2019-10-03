import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;

public class TabSearchFileContentInFolder extends Tab
{
    TextField txtFolderPath;
    TextField txtFileNamePattern;

    public TabSearchFileContentInFolder(String tabTitle)
    {
        super(tabTitle);
        VBox vbox = new VBox();
        vbox.setSpacing(30);
        createControls(vbox);
        this.setContent(vbox);
    }

    public String getFolderPath()
    {
        return txtFolderPath.getText();
    }

    public String getFileNamePattern()
    {
        return txtFileNamePattern.getText();
    }

    private void createControls(Pane parent)
    {
        final String tabTitle = "Search file content from a specified folder";
        Label lblTabTitle = new Label(tabTitle);
        parent.getChildren().add(lblTabTitle);
        createFolderPathControls(parent);
        createFilenamePatternControls(parent);
    }

    private void createFolderPathControls(Pane parent)
    {
        HBox hb = new HBox();
        hb.setSpacing(20);

        Label label = new Label("Folder path");
        txtFolderPath = new TextField();
        txtFolderPath.setMaxWidth(Double.MAX_VALUE);

        hb.getChildren().addAll(label, txtFolderPath);
        parent.getChildren().add(hb);
    }

    private void createFilenamePatternControls(Pane parent)
    {
        HBox hb = new HBox();
        hb.setSpacing(20);

        Label label = new Label("Field name pattern");
        txtFileNamePattern = new TextField();
        txtFileNamePattern.setPromptText("e.g: *.txt");
        txtFileNamePattern.setMaxWidth(Double.MAX_VALUE);

        hb.getChildren().addAll(label, txtFileNamePattern);
        parent.getChildren().add(hb);
    }
}

