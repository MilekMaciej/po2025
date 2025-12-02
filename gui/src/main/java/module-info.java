module samGUI {
    requires javafx.controls;
    requires javafx.fxml;


    opens samGUI to javafx.fxml;
    exports samGUI;
}
