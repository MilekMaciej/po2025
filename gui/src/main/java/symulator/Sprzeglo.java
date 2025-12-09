package symulator;
public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;
    private String nazwa;

    public Sprzeglo( int setWaga, String setNazwa, float setCena) {
        super(setWaga, setNazwa, setCena);
        stanSprzegla = false;
    }
    public void wcisnij()
    {
        stanSprzegla = true;
    }
    public void zwolnij()
    {
        stanSprzegla = false;
    }

    @Override
    public String toString() {
        return nazwa;   // or getNazwa()
    }


    public Sprzeglo getSprzeglo() {
        return Sprzeglo.this;
    }
}
