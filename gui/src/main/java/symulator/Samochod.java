package symulator;
public class Samochod extends Thread{

    private boolean stanWlaczenia;
    private String model;
    private float maxPredkosc;
    private String nrRej;
    private int waga;

    private double predkoscAktualna = 0.0;     // px/s – faktyczna prędkość jazdy
    private static final double HAMOWANIE_SPRZEGLO = 2.0; // px/s^2 (opcjonalnie)
    private static final double PRZYSPIESZENIE = 10.0;     // px/s^2 (jak szybko dochodzi do docelowej)


    private Silnik silnik;
    private SkrzyniaBiegow skrzyniaBiegow;
    private Pozycja pozycja;
    private Sprzeglo sprzeglo;

    private String iconPath;

    private volatile Pozycja cel = null;
    private volatile boolean running = true;

    private final java.util.List<Listener> listeners = new java.util.ArrayList<>();

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
        start();
    }

    @Override
    public String toString() {
        return model + " [" + nrRej + "]";
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

    public String getIconPath() { return iconPath; }
    public void setIconPath(String iconPath) { this.iconPath = iconPath; }

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

    public double getPredkoscDocelowa() {
        if (!stanWlaczenia) return 0;

        int bieg = skrzyniaBiegow.getBieg();
        if (bieg <= 0) return 0;

        double rpmFactor = Math.max(0.0, Math.min(1.0, silnik.getObroty() / 6000.0));

        // to jest dokładnie "prędkość liczona po staremu"
        return 50.0 * bieg * rpmFactor;
    }


    public void addListener(Listener l) { listeners.add(l); }
    public void removeListener(Listener l) { listeners.remove(l); }

    private void notifyListeners() {
        for (Listener l : listeners) l.update();
    }

    public void jedzDo(Pozycja nowaPozycja) {
        this.cel = nowaPozycja;
    }

    public void zwiekszBiegBezpiecznie() {
        if (!sprzeglo.czyWcisniete()) {
            throw new IllegalStateException("Nie można zmienić biegu bez wciśniętego sprzęgła.");
        }
        skrzyniaBiegow.zwiekszBieg();
    }

    public void zmniejszBiegBezpiecznie() {
        if (!sprzeglo.czyWcisniete()) {
            throw new IllegalStateException("Nie można zmienić biegu bez wciśniętego sprzęgła.");
        }
        skrzyniaBiegow.zmniejszBieg();
    }

    public double getPredkosc() {
        if (!stanWlaczenia) return 0;
        if (sprzeglo.czyWcisniete()) return 0;

        int bieg = skrzyniaBiegow.getBieg();
        if (bieg <= 0) return 0;

        // prosty przelicznik
        double rpmFactor = Math.max(0, Math.min(1.0, silnik.getObroty() / 6000.0));
        return 50 * bieg * rpmFactor; // px/s (dobierz jak chcesz)
    }

    public void run() {
        double deltaT = 0.1; // 100 ms

        while (running) {
            try { Thread.sleep(100); }
            catch (InterruptedException e) { break; }

            if (cel == null) continue;

            int x = pozycja.getX();
            int y = pozycja.getY();

            int cx = cel.getX();
            int cy = cel.getY();

            double dx = cx - x;
            double dy = cy - y;
            double dist = Math.sqrt(dx*dx + dy*dy);

            if (dist < 2) {
                cel = null;       // dojechał
                notifyListeners();
                continue;
            }

            double dt = deltaT;

            if (!stanWlaczenia) {
                predkoscAktualna = Math.max(0, predkoscAktualna - HAMOWANIE_SPRZEGLO * dt);
            } else if (sprzeglo.czyWcisniete()) {
                // sprzęgło wciśnięte: utrzymaj prędkość (albo wytracaj powoli)
                predkoscAktualna = Math.max(0, predkoscAktualna - HAMOWANIE_SPRZEGLO * dt);
            } else {
                // sprzęgło puszczone: dopasuj prędkość do docelowej
                double vDoc = getPredkoscDocelowa();

                // płynne dążenie do docelowej (ograniczone przyspieszenie)
                double diff = vDoc - predkoscAktualna;
                double maxChange = PRZYSPIESZENIE * dt;

                if (Math.abs(diff) <= maxChange) {
                    predkoscAktualna = vDoc;
                } else {
                    predkoscAktualna += Math.signum(diff) * maxChange;
                }
            }

            double v = predkoscAktualna;
            if (v <= 0) {
                notifyListeners();
                continue;
            }

            double stepX = v * deltaT * dx / dist;
            double stepY = v * deltaT * dy / dist;

            // Pozycja masz na intach, więc zaokrąglamy:
            pozycja.przesun((int)Math.round(stepX), (int)Math.round(stepY));

            notifyListeners();
        }
    }

    public double getPredkoscAktualna() {
        return predkoscAktualna;
    }


}
