package samGUI;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import symulator.*;   // Samochod, Silnik, SkrzyniaBiegow, Sprzeglo, Pozycja, DataManager

public class CarAddController {

    @FXML private TextField tfModel;
    @FXML private TextField tfPlate;
    @FXML private TextField tfWeight;

    @FXML private ComboBox<Silnik>        engineBox;
    @FXML private ComboBox<SkrzyniaBiegow> gearboxBox;
    @FXML private ComboBox<Sprzeglo>      clutchBox;

    private MainController mainController;   // set from MainController

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void initialize() {
        // fill ComboBoxes from DataManager
        engineBox.setItems(FXCollections.observableArrayList(
                DataManager.getAvailableEngines()
        ));
        gearboxBox.setItems(FXCollections.observableArrayList(
                DataManager.getAvailableGearboxes()
        ));
        clutchBox.setItems(FXCollections.observableArrayList(
                DataManager.getAvailableClutches()
        ));

        // optionally select first items
        if (!engineBox.getItems().isEmpty())  engineBox.getSelectionModel().selectFirst();
        if (!gearboxBox.getItems().isEmpty()) gearboxBox.getSelectionModel().selectFirst();
        if (!clutchBox.getItems().isEmpty())  clutchBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void onSave() {
        // read data from fields + combos
        String model = tfModel.getText();
        String plate = tfPlate.getText();
        float weight = Float.parseFloat(tfWeight.getText());

        Silnik engine = engineBox.getValue();
        SkrzyniaBiegow gearbox = gearboxBox.getValue();
        Sprzeglo clutch = clutchBox.getValue();

        // simple start position – you can change this
        Pozycja p = new Pozycja(10, 10);

        Samochod newCar = new Samochod(model, weight, plate, p, engine, gearbox, clutch);

        // add to global data
        DataManager.addCar(newCar);

        // update main window list if we have a reference
        if (mainController != null) {
            mainController.addCarFromDialog(newCar);
        }

        // close this window
        Stage stage = (Stage) tfModel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) tfModel.getScene().getWindow();
        stage.close();
    }
}

