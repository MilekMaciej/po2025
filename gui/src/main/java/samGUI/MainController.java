package samGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

// importy z modułu symulator:
import symulator.*;
import javafx.application.Platform;


public class MainController implements Listener {


    @FXML private ComboBox<Samochod> carComboBox;

    @FXML private TextField tfModel;
    @FXML private TextField tfPlate;
    @FXML private TextField tfWeight;
    @FXML private TextField tfSpeed;

    @FXML private TextField tfGearboxName;
    @FXML private TextField tfGearboxPrice;
    @FXML private TextField tfGearboxWeight;
    @FXML private TextField tfGear;

    @FXML private TextField tfEngineName;
    @FXML private TextField tfEnginePrice;
    @FXML private TextField tfEngineWeight;
    @FXML private TextField tfEngineRpm;

    @FXML private TextField tfClutchName;
    @FXML private TextField tfClutchPrice;
    @FXML private TextField tfClutchWeight;
    @FXML private TextField tfClutchState;

    @FXML private AnchorPane mapPane;
    @FXML private ImageView carImage;

    private final ObservableList<Samochod> cars = FXCollections.observableArrayList();
    private Samochod currentCar;

    @FXML
    public void initialize() {

        // 1. Load cars from DataManager
        cars.setAll(DataManager.getAvailableCars());

        // 2. Put them into ComboBox
        carComboBox.setItems(cars);

        // 3. Automatically select the first car
        if (!cars.isEmpty()) {
            carComboBox.getSelectionModel().selectFirst();
            selectCar(cars.get(0));
        }

        // 4. Listener for user selection
        carComboBox.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldCar, newCar) -> selectCar(newCar));

        mapPane.setOnMouseClicked(event -> {
            if (currentCar == null) return;
            Pozycja nowa = new Pozycja((int)event.getX(), (int)event.getY());
            currentCar.jedzDo(nowa);
        });
    }


    public void CarAddWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CarAddView.fxml"));
            Parent root = loader.load();

            CarAddController controller = loader.getController();
            controller.setMainController(this);   // <-- important

            Stage stage = new Stage();
            stage.setTitle("Dodaj samochód");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCarFromDialog(Samochod car) {
        cars.add(car);
        carComboBox.getSelectionModel().select(car);
    }

    private void selectCar(Samochod car) {
        if (car == null) return;

        currentCar = car;

        if (currentCar != null) {
            currentCar.removeListener(this);
        }
        currentCar = car;
        currentCar.addListener(this);

        // Stan włączenia silnika
        boolean wlaczony = car.StanWlaczenia();   // or getStanWlaczenia()
        engineToggle.setSelected(wlaczony);
        engineToggle.setText(wlaczony ? "ON" : "OFF");


        tfModel.setText(car.getModel());
        tfPlate.setText(car.getNrRej());
        tfSpeed.setText("0");


        tfWeight.setText(String.valueOf(car.getWaga()));

        // Skrzynia
        tfGearboxName.setText(car.getSkrzyniaBiegow().getNazwa());
        tfGearboxPrice.setText(String.valueOf(car.getSkrzyniaBiegow().getCena()));
        tfGearboxWeight.setText(String.valueOf(car.getSkrzyniaBiegow().getWaga()));
        tfGear.setText(String.valueOf(car.getSkrzyniaBiegow().getBieg()));

        // Silnik
        tfEngineName.setText(car.getSilnik().getNazwa());
        tfEnginePrice.setText(String.valueOf(car.getSilnik().getCena()));
        tfEngineWeight.setText(String.valueOf(car.getSilnik().getWaga()));
        tfEngineRpm.setText(String.valueOf(car.getSilnik().getObroty()));

        // Sprzęgło
        tfClutchName.setText(car.getSprzeglo().getNazwa());
        tfClutchPrice.setText(String.valueOf(car.getSprzeglo().getCena()));
        tfClutchWeight.setText(String.valueOf(car.getSprzeglo().getWaga()));
        // mapa
        carImage.setLayoutX(car.getPozycja().getX());
        carImage.setLayoutY(car.getPozycja().getY());

        tfEngineRpm.setText(String.valueOf(car.getSilnik().getObroty()));

        String path = currentCar.getIconPath();
        if (path != null) {
            carImage.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream(path)));
        }


    }

    // --- AKCJE GUI ---

    @FXML private ToggleButton engineToggle;

    @FXML
    private void onEngineToggle() {
        if (currentCar == null) return;

        boolean nowOn = engineToggle.isSelected();

        if (nowOn) {
            currentCar.wlacz();
            engineToggle.setText("ON");
            tfSpeed.setText("10");
        } else {
            currentCar.wylacz();
            engineToggle.setText("OFF");
            tfSpeed.setText("0");
        }
        tfEngineRpm.setText(String.valueOf(currentCar.getSilnik().getObroty()));
    }

    @FXML private ToggleButton clutchToggle;

    @FXML
    private void onClutchToggle() {
        if (currentCar == null) return;

        boolean nowOn = clutchToggle.isSelected();

        if (nowOn) {
            currentCar.getSprzeglo().wcisnij();
            clutchToggle.setText("Wcisniete");
        } else {
            currentCar.getSprzeglo().zwolnij();
            clutchToggle.setText("Zwolnione");
        }
    }

    @FXML
    private void onGearUp() {
        if (currentCar == null) return;
        try {
            currentCar.zwiekszBiegBezpiecznie();
            tfGear.setText(String.valueOf(currentCar.getSkrzyniaBiegow().getBieg()));
        }
        catch (IllegalStateException e) {
            pokazBlad(e.getMessage());
        }
    }

    @FXML
    private void onGearDown() {
        if (currentCar == null) return;
        try {
            currentCar.zmniejszBiegBezpiecznie();
            tfGear.setText(String.valueOf(currentCar.getSkrzyniaBiegow().getBieg()));
        }
        catch (IllegalStateException e) {
            pokazBlad(e.getMessage());
        }
    }

    @FXML
    private void onThrottleUp() {
        if (currentCar == null) return;
        currentCar.getSilnik().zwiekszObroty();
        tfEngineRpm.setText(String.valueOf(currentCar.getSilnik().getObroty()));
        //moveCar(5, 0);
    }

    @FXML
    private void onThrottleDown() {
        if (currentCar == null) return;
        currentCar.getSilnik().zmniejszObroty();
        tfEngineRpm.setText(String.valueOf(currentCar.getSilnik().getObroty()));
    }

    @FXML
    private void onClutchPress() {
        if (currentCar == null) return;
        currentCar.getSprzeglo().wcisnij();
        tfClutchState.setText("Wciśnięte");
    }

    @FXML
    private void onClutchRelease() {
        if (currentCar == null) return;
        currentCar.getSprzeglo().zwolnij();
        tfClutchState.setText("Zwolnione");
    }

    private void moveCar(int dx, int dy) {
        Pozycja p = currentCar.getPozycja();
        p.przesun(dx, dy);

        carImage.setLayoutX(p.getX());
        carImage.setLayoutY(p.getY());
    }

    @Override
    public void update() {
        Platform.runLater(this::refresh);
    }

    private void refresh() {
        if (currentCar == null) return;

        carImage.setLayoutX(currentCar.getPozycja().getX());
        carImage.setLayoutY(currentCar.getPozycja().getY());

        // opcjonalnie: odśwież też pola prędkości/obrotów/biegu
        tfGear.setText(String.valueOf(currentCar.getSkrzyniaBiegow().getBieg()));
        tfEngineRpm.setText(String.valueOf(currentCar.getSilnik().getObroty()));
        tfSpeed.setText(String.valueOf((int)Math.round(currentCar.getPredkoscAktualna())));
    }

    public void DeleteSelectedCar() {
        if (currentCar == null) return;
        else {
            cars.remove(currentCar);
            carComboBox.getSelectionModel().selectFirst();
        }
    }
    public void pokazBlad(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }

}
