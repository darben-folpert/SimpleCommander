import javafx.scene.control.Control;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.application.Platform;
import java.util.Enumeration;
import java.util.List;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import laglibjava.TextFileService;
import laglibjava.FileSystemService;

// refactor: can we get rid of these ?
//   -> deal with the real control in some other class ?
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;


public class SearchProcessor extends Task<Void>
{
    private Hashtable<String, Control> controls;
    private List<String> searchParams;
    private FileSystemService fsService;
    private TextFileService fileService;
    private TextArea theTextArea;
    private ArrayList<String> result;

    public SearchProcessor(List<String> searchParams, Hashtable<String, Control> uiControls)
    {
        this.searchParams = searchParams;
        this.controls = uiControls;
        fsService = new FileSystemService();
        fileService = new TextFileService();
    }

    public ArrayList<String> getResult()
    {
        return this.result;
    }

    @Override
    protected Void call() throws Exception
    {
        String folder = searchParams.get(0);
        String search = searchParams.get(1);
        String fileNamePattern = searchParams.get(2);
        theTextArea = (TextArea)controls.get("txtResult");
        ArrayList<String> myList = new ArrayList<String>();
        String myResult = "";

        updateMessage("Discovering files...");

        Platform.runLater(() ->
        {
            disableControls(true);
        });

        try
        {
            List<String> results;
            results = getListOfFilesMatchingSearch(folder, search, fileNamePattern);
            for (String oneLine : results)
                myResult += oneLine + "\n";
        }
        catch (Exception ex)
        {
            System.out.println("An error occurred while searching.\n" + ex.toString());
        }
        this.result = myList;

        final String myResult2 = myResult;
        Platform.runLater(() ->
        {
            theTextArea.setText(myResult2);
            disableControls(false);
            updateMessage("Search completed.");
        });
        return null;
    }

    protected void updateMessage(String message)
    {
        Label progress = (Label)controls.get("lblProgress");
        Platform.runLater(() ->
        {
            progress.setText(message);
        });
    }

    private void runSleep(int seconds)
    {
        try
        {
            System.out.println(">>> --- Going to sleep.");
            Thread.sleep(seconds*1000);
            System.out.println(">>> --- Waking up !");
        }
        catch (Exception e)
        {
            System.out.println("Error while trying to sleep");
        }
    }

    private List<String> getListOfFilesMatchingSearch
        (String folderPath, String whatToSearch, String filenamePattern)
    {
        List<String> result = new ArrayList<String>();

        try
        {
            List<String> files = new ArrayList<String>();
            files = fsService.getFilesIncludingSubFolders(folderPath, filenamePattern);
            int current = 1;
            for (String fileName : files)
            {
                String progressToDisplay = current++ + "/" + files.size();
                updateMessage(progressToDisplay);
                String filenameIfMatch = getFileNameIfMatches(fileName, whatToSearch);
                if (!filenameIfMatch.equals(""))
                    result.add(fileName);
            }
        }
        catch (FileNotFoundException ex)
        {
            String message = "Specified folder doesn't exist !\n";
            message += "Folder specified: " + folderPath;
            DialogError.showDialogAndWait(message, "Invalid folder");
        }
        return result;
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

    private void disableControls(boolean disable)
    {
        if (disable)
        {
            TextArea txtResult = (TextArea)controls.get("txtResult");
            txtResult.clear();
            txtResult.appendText("Searching, please wait...");
        }

        for (Enumeration<Control> e = controls.elements(); e.hasMoreElements();)
            e.nextElement().setDisable(disable);
    }
}

