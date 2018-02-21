package dbk.qacourse.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
        hello();
        helloHello("Dorota");

        /*
        double x = 5;
        System.out.println("Pole kwadratu A o boku " + x + " = " + area(x));

        double a = 4;
        double b = 6;
        System.out.println("Pole prostokąta A o bokach " + a + " i " + b + " = " + area(a, b));
        */

        Square s = new Square(5);
        System.out.println("Pole kwadratu o boku " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(6, 3);
        System.out.println("Pole prostokąta o bokach " + r.a + " i " + r.b + " = " + r.area());

        System.out.println("Zadanie 1: Przedstaw w układzie współrzędnych punkty:");

        Point p1 = new Point(1, 2);
        System.out.println("A = " + p1.location());

        Point p2 = new Point(5, 4);
        System.out.println("B = " + p2.location());

        System.out.println("Zadanie 2: Oblicz odległość punktu A od punktu B:");

        public static double distance(Point p1, Point p2){
            public double getX(){
                return x;
            }

            public double getY() {
                return y;
            }
			
			distance(){
				return Math.sqrt(((p1.getX - p2.getX)*(p1.getX - p2.getX)) + ((p1.getY - p2.getY)*(p1.getY - p2.getY)));				
			}

        }

        System.out.println("Odległość punktu A od punktu B wynosi " + distance());
/*


        public double powerX(){
            return Math.pow(p1.x - p2.x);
        }

        public double powerY(){
            return Math.pow(p1.y - p2.y);
        }


        System.out.println("Odległość punktu A od punktu B wynosi " + Point.distance(p1, p2));
*/
    }

    // zmienna deklarowana wewn. metody -> metoda nie potrzebuje argumentów
	public static void hello() {
	    String somebody = "world";
		System.out.println("Hello, " + somebody + "!");
	}

    // zmienna jako parametr/argument metody
	public static void helloHello(String somebodyElse) {
	    System.out.println("Hello hello, " + somebodyElse + "!");
    }


    /*
    public static double area(double x) {
	    return x * x;
    }

    public static double area(double a, double b) {
        return a * b;
    }
    */
}