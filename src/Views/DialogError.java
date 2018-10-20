import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


class DialogError
{
    public static void showDialogAndWait(String errMessage, String title)
    {
        Alert alertBox = new Alert(AlertType.ERROR, errMessage);
        alertBox.setTitle(title);
        alertBox.showAndWait();
    }
}

