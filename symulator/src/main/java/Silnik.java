public class Silnik extends Komponent{
    private int obroty;
    private int maxObroty;
    private Sprzeglo sprzeglo;


    public Silnik(int setObroty, int setMaxObroty, int setWaga, String setNazwa, float setCena, Sprzeglo setSprzeglo) {
        super(setWaga, setNazwa, setCena);

        this.obroty = setObroty;
        this.maxObroty = setMaxObroty;
        this.sprzeglo = setSprzeglo;
    }

    public void zwiekszObroty(int gaz){
        if((obroty + gaz) < maxObroty){
            this.obroty += gaz;
        }
        else{
            this.obroty = maxObroty;
        }
    }
    public void zmniejszObroty(int gaz) {
        if ((obroty - gaz) <= 0) {
            this.obroty -= gaz;
        }
        else{
            this.obroty = 0;
        }
    }


}
