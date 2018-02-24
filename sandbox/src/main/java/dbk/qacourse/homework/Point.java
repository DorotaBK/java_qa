package dbk.qacourse.homework;

public class Point {

    // pola
    public double x;
    public double y;

    // konstruktor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // metoda zwracająca współrzędne punktów
    public String location() {
        return "(" + this.x + ", " + this.y + ")";
    }

    // metoda zwracająca odległość między punktami
    // argumentem jest obiekt klasy Point
    // dopiero w wywołaniu funkcji wskażę, który dokładnie obiekt ma być parametrem i podstawię go w miejsce nextPoint
    public double distance(Point nextPoint){
        double dx = nextPoint.x - x;
        double dy = nextPoint.y - y;
        double distPoint = Math.sqrt((dx * dx) + (dy * dy));
        return distPoint;
    }

}