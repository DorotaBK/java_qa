package dbk.qacourse.homework;

public class MyHomework1 {

    public static void main(String[] args){

        System.out.println("Zadanie 1: Przedstaw w układzie współrzędnych punkty:");

        Point p1 = new Point(1, 2);
        System.out.println("A = " + p1.location());

        Point p2 = new Point(5, 4);
        System.out.println("B = " + p2.location());

        System.out.println("Zadanie 2: Oblicz odległość punktu A od punktu B:");

        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;

        double distPoint = Math.sqrt((dx * dx) + (dy * dy));
        System.out.println("Odległość punktu A od punktu B wynosi " + distPoint);
    }
}
