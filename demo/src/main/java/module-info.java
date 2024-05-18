module dactilogame {
    requires javafx.controls;
    requires javafx.fxml;

    opens dactilogame to javafx.fxml;
    exports dactilogame;
}
