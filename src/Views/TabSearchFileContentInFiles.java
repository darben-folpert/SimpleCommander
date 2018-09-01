import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class TabSearchFileContentInFiles extends Tab
{
    public TabSearchFileContentInFiles(String tabTitle)
    {
        super(tabTitle);
        VBox vbox = new VBox();
        createControls(vbox);
        this.setContent(vbox);
    }

    private void createControls(Pane parent)
    {
        final String tabTitle = "Search in file content from a specified list of files";
        Label lblTabTitle = new Label(tabTitle);
        TextArea txtFilesList = new TextArea();
        parent.getChildren().addAll(lblTabTitle, txtFilesList);
    }


}

