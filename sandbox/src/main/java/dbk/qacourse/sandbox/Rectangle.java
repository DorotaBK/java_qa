package dbk.qacourse.sandbox;

public class Rectangle {

    public double a;
    public double b;

    //konstruktor
    public Rectangle(double a, double b) {
        this.a = a; //wartość atrybutu a tego obiektu równa jest wartości argumentu a funkcji
        this.b = b;
    }

    public double area() {

        return this.a * this.b;
    }
}
