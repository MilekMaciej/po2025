package symulator;   // popraw, jeśli masz inny package

public class Pozycja {
    private int x;
    private int y;

    public Pozycja(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // GETTERY
    public int getX() { return x; }
    public int getY() { return y; }

    // SETTERY (przydadzą się do przesuwania samochodu po mapie)
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    // pomocnicza metoda – przesunięcie o wektor
    public void przesun(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
