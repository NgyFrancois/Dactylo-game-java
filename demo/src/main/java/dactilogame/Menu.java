package dactilogame;

import java.io.IOException;
import javafx.fxml.FXML;

public class Menu {

    @FXML
    private void goSolo() throws IOException {
        App.setRoot("solo");
    }
}
