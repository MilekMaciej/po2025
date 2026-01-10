package samGUI;

import javafx.scene.image.Image;
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
    @FXML private TextField tfMaxSpeed;

    @FXML private javafx.scene.image.ImageView iconPreview;
    @FXML private ComboBox<String> iconBox;
    @FXML private ComboBox<Silnik>        engineBox;
    @FXML private ComboBox<SkrzyniaBiegow> gearboxBox;
    @FXML private ComboBox<Sprzeglo>      clutchBox;

    private MainController mainController;   // set from MainController

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        gearboxBox.setItems(FXCollections.observableArrayList(DataManager.getAvailableGearboxes()));
        engineBox.setItems(FXCollections.observableArrayList(DataManager.getAvailableEngines()));
        clutchBox.setItems(FXCollections.observableArrayList(DataManager.getAvailableClutches()));

        // wybór domyślny (żeby nie było null)
        gearboxBox.getSelectionModel().selectFirst();
        engineBox.getSelectionModel().selectFirst();
        clutchBox.getSelectionModel().selectFirst();

        // ikony – patrz punkt 2
        iconBox.setItems(FXCollections.observableArrayList(DataManager.getAvailableIcons()));
        iconBox.getSelectionModel().selectFirst();
        updateIconPreview();
        iconBox.setOnAction(e -> updateIconPreview());
    }

    @FXML
    private void onSave() {
        String model = tfModel.getText().trim();
        String plate = tfPlate.getText().trim();
        float maxSpeed = Float.parseFloat(tfMaxSpeed.getText().trim());

        Silnik engineTemplate = engineBox.getValue();
        SkrzyniaBiegow gearboxTemplate = gearboxBox.getValue();
        Sprzeglo clutchTemplate = clutchBox.getValue();

        String iconPath = iconBox.getValue();

        // startowa pozycja
        Pozycja p = new Pozycja(100, 100);

        // WAŻNE: kopie części, żeby auta nie dzieliły stanu
        Silnik engine = engineTemplate.copy();
        SkrzyniaBiegow gearbox = gearboxTemplate.copy();
        Sprzeglo clutch = clutchTemplate.copy();

        Samochod newCar = new Samochod(model, maxSpeed, plate, p, engine, gearbox, clutch);
        newCar.setIconPath(iconPath);

        DataManager.addCar(newCar);

        if (mainController != null) {
            mainController.addCarFromDialog(newCar);
        }

        newCar.setIconPath(iconBox.getValue());

        ((Stage) tfModel.getScene().getWindow()).close();
    }


    @FXML
    private void onCancel() {
        Stage stage = (Stage) tfModel.getScene().getWindow();
        stage.close();
    }

    private void updateIconPreview() {
        String path = iconBox.getValue();
        if (path == null) return;
        iconPreview.setImage(new Image(getClass().getResourceAsStream(path)));
    }

}

