import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;


class SearchRequestHandler implements EventHandler<ActionEvent>
{
    private SimpleCommander mainWindow;
    private Hashtable<String, Control> controls;

    public SearchRequestHandler(SimpleCommander mainWindow, Hashtable<String, Control> uiControls)
    {
        this.mainWindow = mainWindow;
        controls = uiControls;
    }

    public void handle(ActionEvent event)
    {
        List<String> params = new ArrayList<String>();
        params.add(mainWindow.getFieldValue(0));
        params.add(mainWindow.getFieldValue(1));
        params.add(mainWindow.getFieldValue(2));

        String folderPath = mainWindow.getFieldValue(0);
        String searchedText = mainWindow.getFieldValue(1);
        String filenamePattern = mainWindow.getFieldValue(2);

        SearchProcessor job = new SearchProcessor(params, controls);
        Thread processSearchThread = new Thread(job, "Process search");
        processSearchThread.start();
    }
}

