public abstract class Komponent {
    private int waga;
    private String nazwa;
    private float cena;

    public Komponent(int waga, String nazwa, float cena) {
        this.waga = waga;
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getWaga() {
        return waga;
    }

    public float getCena() {
        return cena;
    }
}
