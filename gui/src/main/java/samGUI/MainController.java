package samGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

// importy z modułu symulator:
import symulator.Samochod;
import symulator.Silnik;
import symulator.SkrzyniaBiegow;
import symulator.Sprzeglo;
import symulator.Pozycja;

public class MainController {

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

        // Przykładowy samochód z użyciem Twoich klas z symulatora:
        Sprzeglo sprzeglo = new Sprzeglo(20, "Standard", 1500);
        Silnik silnik = new Silnik(900, 5000, "1.9 TDI", 150, sprzeglo);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow(5, 80, "Manual", 3000);
        Pozycja poz = new Pozycja(40, 40);

        Samochod s1 = new Samochod("Golf", 180, "WX 12345", poz, silnik, skrzynia, sprzeglo);

        cars.add(s1);

        carComboBox.setItems(cars);
        carComboBox.getSelectionModel().selectFirst();

        carComboBox.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, now) -> selectCar(now)
        );

        selectCar(s1);
    }

    private void selectCar(Samochod car) {
        if (car == null) return;

        currentCar = car;

        tfModel.setText(car.getModel());
        tfPlate.setText(car.getNrRej());
        tfSpeed.setText("0");

        int totalWeight = car.getSilnik().getWaga()
                + car.getSkrzyniaBiegow().getWaga()
                + car.getSprzeglo().getWaga();

        tfWeight.setText(String.valueOf(totalWeight));

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
        tfClutchState.setText("Zwolnione");

        // mapa
        carImage.setLayoutX(car.getPozycja().getX());
        carImage.setLayoutY(car.getPozycja().getY());
    }

    // --- AKCJE GUI ---

    @FXML
    private void onCarStart() {
        if (currentCar == null) return;
        currentCar.wlacz();
        tfSpeed.setText("10");
    }

    @FXML
    private void onCarStop() {
        if (currentCar == null) return;
        currentCar.wylacz();
        tfSpeed.setText("0");
    }

    @FXML
    private void onGearUp() {
        if (currentCar == null) return;
        currentCar.getSkrzyniaBiegow().zwiekszBieg();
        tfGear.setText(String.valueOf(currentCar.getSkrzyniaBiegow().getBieg()));
    }

    @FXML
    private void onGearDown() {
        if (currentCar == null) return;
        currentCar.getSkrzyniaBiegow().zmniejszBieg();
        tfGear.setText(String.valueOf(currentCar.getSkrzyniaBiegow().getBieg()));
    }

    @FXML
    private void onThrottleUp() {
        if (currentCar == null) return;
        currentCar.getSilnik().zwiekszObroty();
        tfEngineRpm.setText(String.valueOf(currentCar.getSilnik().getObroty()));
        moveCar(5, 0);
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
}
