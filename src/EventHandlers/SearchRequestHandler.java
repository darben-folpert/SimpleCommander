import laglibjava.FileService;
import laglibjava.FileSystemService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


class SearchRequestHandler implements EventHandler<ActionEvent>
{
    private SimpleCommander mainWindow;

    public SearchRequestHandler(SimpleCommander mainWindow)
    {
        this.mainWindow = mainWindow;
    }

    public void handle(ActionEvent event)
    {
        System.out.println("calling SearchRequestHandler");
        String folderPath = mainWindow.getFieldValue(0);
        String searchedText = mainWindow.getFieldValue(1);
        String filenamePattern = mainWindow.getFieldValue(2);
        System.out.println("    folderPath: " + folderPath);
        System.out.println("    searchedText: " + searchedText);
        System.out.println("    filenamePattern: " + filenamePattern);
        //searchInFilesFromFolder
        //this.resultArea.appendText("This is the result from EventHandler");
    }

    /*private void searchInFilesFromFolder(String folderPath, String whatToSearch, String filenamePattern)
    {
    }*/
}

