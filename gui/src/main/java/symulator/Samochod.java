package symulator;
public class Samochod {

    private boolean stanWlaczenia;
    private String model;
    private float maxPredkosc;
    private String nrRej;
    private int waga;

    private Silnik silnik;
    private SkrzyniaBiegow skrzyniaBiegow;
    private Pozycja pozycja;
    private Sprzeglo sprzeglo;

    public Samochod(String setModel, float setMaxPredkosc, String setNrRej, Pozycja setPozycja, Silnik setSilnik, SkrzyniaBiegow setSkrzyniaBiegow, Sprzeglo setSprzeglo) {
        this.stanWlaczenia = false;
        this.model = setModel;
        this.maxPredkosc = setMaxPredkosc;
        this.nrRej = setNrRej;
        this.silnik = setSilnik;
        this.pozycja = setPozycja;
        this.sprzeglo = setSprzeglo;
        this.skrzyniaBiegow = setSkrzyniaBiegow;
        this.waga = 1000 + this.getSilnik().getWaga() + this.getSkrzyniaBiegow().getWaga() + this.getSprzeglo().getWaga();
    }
public int  getWaga() { return waga; }

    public String getNrRej(){ return nrRej; }

    public Silnik getSilnik() {
        return silnik;
    }

    public String getModel() {
        return model;
    }

    public float getMaxPredkosc() {
        return maxPredkosc;
    }

    public SkrzyniaBiegow getSkrzyniaBiegow() {
        return skrzyniaBiegow;
    }

    public Pozycja getPozycja() {
        return pozycja;
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }

    public boolean StanWlaczenia() {
        return stanWlaczenia;
    }

    public void wlacz(){
        this.stanWlaczenia = true;
        this.silnik.jalowy();
    }

    public void wylacz(){
        this.stanWlaczenia = false;
        this.silnik.wylacz();
    }


}
