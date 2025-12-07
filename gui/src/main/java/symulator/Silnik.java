package symulator;
public class Silnik extends Komponent{
    private int obroty;
    private int maxObroty;


    public Silnik(int setMaxObroty, int setWaga, String setNazwa, float setCena) {
        super(setWaga, setNazwa, setCena);

        this.obroty = 0;
        this.maxObroty = setMaxObroty;
    }

    public int getObroty() {
        return this.obroty;
    }

    public void jalowy() {
            this.obroty = 1000;
    }

    public void wylacz() {
        this.obroty = 0;
    }

    public void zwiekszObroty(){
        if((obroty + 100) < maxObroty){
            this.obroty += 500;
        }
        else{
            this.obroty = maxObroty;
        }
    }
    public void zmniejszObroty() {
        if ((obroty - 100) <= 0) {
            this.obroty -= 500;
        }
        else{
            this.obroty = 0;
        }
    }


}
