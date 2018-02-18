package dbk.qacourse.sandbox;

// publiczna klasa o nazwie MyFirstProgram z metodą main()
// public - modyfikator dostępu
// static - tzn. metoda jest statyczna

public class MyFirstProgram {

	public static void main(String[] args) {
		hello();
		helloHello("user");
		helloHello("Dorota");
		helloHello("everybody");

		double x = 5;
        System.out.println("Pole kwadratu o boku " + x + " = " + area(x));

        double a = 4;
        double b = 6;
        System.out.println("Pole prostokąta o bokach " + a + " i " + b + " = " + area(a, b));
	}

    // zmienna somebody deklarowana wewn. metody; nie potrzebuje argumentów z zewn.
	public static void hello() {
	    String somebody = "world";
		System.out.println("Hello, " + somebody + "!");
	}

    // zmienna somebodyElse jako parametr/argument funkcji, w jej wywołaniu potrzebny parametr
    // main() zwraca się do helloHello() i przekazuje jej string "world"
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