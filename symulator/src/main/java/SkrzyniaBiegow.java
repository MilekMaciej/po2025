public class SkrzyniaBiegow extends Komponent {
    private int bieg;
    private int iloscBiegow;
    private float aktualnePrzelozenie;
    private int waga;
    private String nazwa;
    private float cena;

    public SkrzyniaBiegow (int setIloscBiegow, int setWaga, String setNazwa, float setCena) {
        super(setWaga, setNazwa, setCena);

        this.iloscBiegow = setIloscBiegow;
        this.bieg = 0;
    }

    public void zwiekszBieg() {
        if(bieg < iloscBiegow) {
            this.bieg += 1;
        }
    }
    public void zmmiejszBieg() {
        if(bieg > 1) {
            this.bieg -= 1;
        }
    }
    public int getBieg() {
        return bieg;
    }
    public float getAktualnePrzelozenie() {
        return aktualnePrzelozenie;
    }
}
