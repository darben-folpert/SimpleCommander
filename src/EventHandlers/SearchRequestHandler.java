import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;


class SearchRequestHandler implements EventHandler<ActionEvent>
{
    private TextArea resultArea;

    public SearchRequestHandler(TextArea resultArea)
    {
        this.resultArea = resultArea;
    }

    public void handle(ActionEvent event)
    {
        System.out.println("calling SearchRequestHandler");
        this.resultArea.appendText("This is the result from EventHandler");
    }
}

