package dbk.qacourse.homework;

public class Point {

    public double x;
    public double y;

    // konstruktor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // metoda zwracająca współrzędne punktu (punkt jest obiektem TEJ klasy)
    public String location() {
        return "(" + this.x + ", " + this.y + ")";
    }

    // metoda zwracająca odległość między dwoma punktami (dwoma obiektami TEJ klasy)
    public static double distance(Point p1, Point p2){
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;

        return Math.sqrt((dx * dx) + (dy * dy));
    }

}