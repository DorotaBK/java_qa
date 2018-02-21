package dbk.qacourse.homework;

public class MyHomework1 {

    public static void main(String[] args){

        System.out.println("Zadanie 1: Przedstaw w układzie współrzędnych punkty:");

        Point p1 = new Point(7,5);
        System.out.println("A = " + p1.location());

        Point p2 = new Point(3,9);
        System.out.println("B = " + p2.location());

        System.out.println("Zadanie 2:");

        // metoda distance() na obiektach p1 i p2 zwraca wartość typu double, należy ją przypisać do zmiennej
        double distPoint = Point.distance(p1, p2);

        // i dopiero tej zmiennej użyć tutaj (nie metody distance()!!!)
        System.out.println("Odległość punktu A od punktu B wynosi " + distPoint);
    }
}