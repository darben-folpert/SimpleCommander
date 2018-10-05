import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


class CloseWindowConfirmationDialog
{
    private static Boolean closeConfirmed;

    public static Boolean getCloseWindowConfirmation()
    {
        return closeConfirmed;
    }

    public static void showDialogAndWait()
    {
        closeConfirmed = false;
        Alert alertBox = new Alert(AlertType.CONFIRMATION, "Do you really want to quit ?");
        alertBox.setTitle("Quit");
        Optional<ButtonType> userChoice = alertBox.showAndWait();
        if (userChoice.isPresent() && userChoice.get() == ButtonType.OK)
            closeConfirmed = true;
    }
}

