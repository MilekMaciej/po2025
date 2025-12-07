package samGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import symulator.SkrzyniaBiegow;
import symulator.Silnik;
import symulator.Sprzeglo;
import symulator.DataManager;

public class CarAddController {

    @FXML
    private ComboBox<SkrzyniaBiegow> gearboxBox;

    @FXML
    private ComboBox<Silnik> engineBox;

    @FXML
    private ComboBox<Sprzeglo> clutchBox;

    // lists of available parts, set from MainController
    private ObservableList<SkrzyniaBiegow> availableGearboxes =
            FXCollections.observableArrayList();
    private ObservableList<Silnik> availableEngines =
            FXCollections.observableArrayList();
    private ObservableList<Sprzeglo> availableClutches =
            FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        gearboxBox.setItems(availableGearboxes);
        engineBox.setItems(availableEngines);
        clutchBox.setItems(availableClutches);
    }

    // called from MainController after loading FXML
    public void setAvailableParts(
            ObservableList<SkrzyniaBiegow> gearboxes,
            ObservableList<Silnik> engines,
            ObservableList<Sprzeglo> clutches
    ) {
        availableGearboxes.setAll(gearboxes);
        availableEngines.setAll(engines);
        availableClutches.setAll(clutches);
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveAndClose(ActionEvent event) {
        SkrzyniaBiegow selectedGearbox = gearboxBox.getValue();
        Silnik selectedEngine = engineBox.getValue();
        Sprzeglo selectedClutch = clutchBox.getValue();

        // TODO: tutaj zrób co chcesz – np. utwórz nowy Samochod albo
        // przekaż wybrane części z powrotem do MainController (patrz dalej)

        closeWindow(event);
    }
}
