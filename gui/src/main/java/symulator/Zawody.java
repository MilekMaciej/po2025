package symulator;
public class Zawody {
    private String nazwa;
    private int data;

    public Zawody (String setnazwa, int setdata){
        this.nazwa = setnazwa;
        this.data = setdata;
    }
    public String getNazwa(){
        return this.nazwa;
    }
    public int getData(){
        return this.data;
    }

}
