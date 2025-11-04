public class Samochod {

    private boolean stanWlaczenia;
    private String model;
    private float maxPredkosc;
    private String nrRej;

    private Silnik silnik;
    private SkrzyniaBiegow skrzyniaBiegow;
    private Pozycja pozycja;
    private Sprzeglo sprzeglo;

    public Samochod(String setModel, float setMaxPredkosc, String setNrRej, Pozycja setPozycja, Silnik setSilnik, SkrzyniaBiegow setSkrzyniaBiegow, Sprzeglo setSprzeglo) {
        this.stanWlaczenia = false;
        this.model = setModel;
        this.maxPredkosc = maxPredkosc;
        this.nrRej = setNrRej;
        this.silnik = setSilnik;
        this.pozycja = setPozycja;
        this.sprzeglo = setSprzeglo;
    }

    public void wlacz(){
        this.stanWlaczenia = true;
    }

    public void wylacz(){
        this.stanWlaczenia = false;
    }

    public float getAktPredkosc(){
        return this.maxPredkosc;
    }

}
