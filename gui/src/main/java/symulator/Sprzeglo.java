package symulator;

public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;

    public Sprzeglo(int setWaga, String setNazwa, float setCena) {
        super(setWaga, setNazwa, setCena);
        stanSprzegla = false;
    }

    public void wcisnij() {
        stanSprzegla = true;
    }

    public void zwolnij() {
        stanSprzegla = false;
    }

    public boolean czyWcisniete() {
        return stanSprzegla;
    }

    @Override
    public String toString() {
        return getNazwa(); // jeśli Komponent ma getNazwa()
        // albo: return super.getNazwa();
    }
    public Sprzeglo copy() {
        return new Sprzeglo(getWaga(), getNazwa(), getCena());
    }
}
