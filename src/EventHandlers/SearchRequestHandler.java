import laglibjava.FileService;
import laglibjava.FileSystemService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.io.FileNotFoundException;


class SearchRequestHandler implements EventHandler<ActionEvent>
{
    private SimpleCommander mainWindow;
    private FileSystemService fsService;
    private FileService fileService;

    public SearchRequestHandler(SimpleCommander mainWindow)
    {
        this.mainWindow = mainWindow;
        fsService = new FileSystemService();
        fileService = new FileService();
    }

    public void handle(ActionEvent event)
    {
        System.out.println("calling SearchRequestHandler");
        String folderPath = mainWindow.getFieldValue(0);
        String searchedText = mainWindow.getFieldValue(1);
        String filenamePattern = mainWindow.getFieldValue(2);

        ArrayList<String> files =
            getListOfFilesMatchingSearch(folderPath, searchedText, filenamePattern);

        fillResultArea(files);
    }

    private ArrayList<String> getListOfFilesMatchingSearch
        (String folderPath, String whatToSearch, String filenamePattern)
    {
        ArrayList<String> result = new ArrayList<String>();
        try
        {
            ArrayList<String> files = new ArrayList<String>();
            files = fsService.getFilesIncludingSubFolders(folderPath, filenamePattern);
            for (String filename : files)
            {
                String filenameIfMatch = getFileNameIfMatches(filename, whatToSearch);
                if (!filenameIfMatch.equals(""))
                    result.add(filename);
            }
        }
        catch (FileNotFoundException ex)
        {
            String message = "Specified folder doesn't exist !\nFolder specified: " + folderPath;
            DialogError.showDialogAndWait(message, "Invalid folder");
        }
        return result;
    }

    private void fillResultArea(ArrayList<String> filenames)
    {
        mainWindow.setResult(filenames);
    }

    private String getFileNameIfMatches(String filename, String whatToSearch)
    {
        try
        {
            Boolean containsText = fileService.textFileContains(filename, whatToSearch);
            if (containsText)
                return filename;
        }
        catch (Exception ex)
        {
            // TODO: log this error somewhere
            String message = "Error while reading file: " + filename;
            System.out.println(message);
        }
        return "";
    }
}

