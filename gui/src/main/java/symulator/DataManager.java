package symulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataManager {

    // --- fields holding all available parts ---

    private static final List<SkrzyniaBiegow> AVAILABLE_GEARBOXES = new ArrayList<>();
    private static final List<Silnik>         AVAILABLE_ENGINES   = new ArrayList<>();
    private static final List<Sprzeglo>       AVAILABLE_CLUTCHES  = new ArrayList<>();
    private static final List<Samochod>       AVAILABLE_CARS      = new ArrayList<>();

    // --- static initializer: fill your data ONCE ---

    static {
        // TODO: replace these with your real constructors
        Pozycja pozycja_startowa = new Pozycja(100,100);

        Sprzeglo zwykle_sprzeglo = new Sprzeglo(30,  "zwykle", 3000);
        Sprzeglo dwumasa_sprzeglo = new Sprzeglo(20,     "dwumasa", 8000);
        AVAILABLE_CLUTCHES.add(zwykle_sprzeglo);
        AVAILABLE_CLUTCHES.add(dwumasa_sprzeglo);

        Silnik silnik_D4D = new Silnik(6000,   370, "1.4 D4D", 5000);
        Silnik silnik_M13A = new Silnik(9000,  300, "1.3 M13A", 4000);
        AVAILABLE_ENGINES.add(silnik_M13A);
        AVAILABLE_ENGINES.add(silnik_D4D);

        SkrzyniaBiegow manual_5 = new SkrzyniaBiegow(5, 150, "manual 5 biegow", 2000);
        SkrzyniaBiegow manual_6 = new SkrzyniaBiegow(6, 180, "manual 6 biegow", 3000);
        AVAILABLE_GEARBOXES.add(manual_5);
        AVAILABLE_GEARBOXES.add(manual_6);

        Samochod Toyota = new Samochod("Toyota Auris", 175, "KR12345", pozycja_startowa, silnik_D4D, manual_5, dwumasa_sprzeglo);
        Samochod Subaru = new Samochod("subaru Justy", 165, "KR54321", pozycja_startowa, silnik_M13A, manual_5, zwykle_sprzeglo);
        AVAILABLE_CARS.add(Toyota);
        AVAILABLE_CARS.add(Subaru);

    }

    // --- public getters (read-only lists) ---

    public static List<SkrzyniaBiegow> getAvailableGearboxes() {
        return Collections.unmodifiableList(AVAILABLE_GEARBOXES);
    }

    public static List<Silnik> getAvailableEngines() {
        return Collections.unmodifiableList(AVAILABLE_ENGINES);
    }

    public static List<Sprzeglo> getAvailableClutches() {
        return Collections.unmodifiableList(AVAILABLE_CLUTCHES);
    }

    public static List<Samochod> getAvailableCars() {
        return Collections.unmodifiableList(AVAILABLE_CARS);
    }

    // --- optional: methods to add/remove at runtime ---

    public static void addCar(Samochod car) {
        AVAILABLE_CARS.add(car);
    }

    // you can add similar methods for parts if needed
}
