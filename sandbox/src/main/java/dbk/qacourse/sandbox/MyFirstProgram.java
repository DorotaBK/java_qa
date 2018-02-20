package dbk.qacourse.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		hello();
		helloHello("user");
		helloHello("Dorota");
		helloHello("everybody");
		helloHello("women");

		double x = 5;
        System.out.println("Pole kwadratu A o boku " + x + " = " + area(x));

        double a = 4;
        double b = 6;
        System.out.println("Pole prostokąta A o bokach " + a + " i " + b + " = " + area(a, b));

        // w wersji obiektowej:
        Square s = new Square(5); // nowy obiekt klasy Square posiadajacej konstruktor
        System.out.println("Pole kwadratu B o boku " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(6,3);
        System.out.println("Pole prostokąta B o bokach " + r.a + " i " + r.b + " = " + r.area());

    }

    // zmienna deklarowana wewn. metody -> metoda nie potrzebuje argumentów
	public static void hello() {
	    String somebody = "world";
		System.out.println("Hello, " + somebody + "!");
	}

    // zmienna jako parametr/argument metody, w jej wywołaniu potrzebny parametr
	public static void helloHello(String somebodyElse) {
	    System.out.println("Hello hello, " + somebodyElse + "!");
    }

    public static double area(double x) {
	    return x * x;
    }

    public static double area(double a, double b) {
	    return a * b;
    }
}