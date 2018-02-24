package dbk.qacourse.homework;

public class MyHomework1 {

    public static void main(String[] args){

        System.out.println("Create points in the coordinate system:");

        Point p1 = new Point(7,5);
        Point p2 = new Point(3,9);
        Point p3 = new Point(2,8);

        System.out.println("A = " + p1.location());
        System.out.println("B = " + p2.location());
        System.out.println("C = " + p3.location());

        // metoda distance() na obiekcie p1, jej parametrem jest p2;
        // zwraca wartość double, którą należy przypisać do zmiennej
        double distPoint1 = p1.distance(p2);
        // i dopiero tej zmiennej użyć do wyświetlenia wyniku (nie metody distance()!!!)
        System.out.println("The distance between points A and B is " + distPoint1);

        double distPoint2 = p1.distance(p3);
        System.out.println("The distance between points A and C is " + distPoint2);

        double distPoint3 = p2.distance(p3);
        System.out.println("The distance between points B and C is " + distPoint3);

    }
}