package dbk.qacourse.sandbox;

public class Equation {

    private double a;
    private double b;
    private double c;

    private int n;  // the number of zero places in the quadratic equation

    public Equation(double a, double b, double c){

        this.a = a;
        this.b = b;
        this.c = c;

        double d = b*b - 4*a*c; // delta

        if (a == 0) {
            if (b == 0) {
                if (c == 0){
                    n = -1;         // a == 0, b == 0, c == 0; infinitely many solutions
                } else {
                    n = 0;          // a == 0, b == 0, c !== 0; no solution; testConstant()
                }
            } else {
                n = 1;              // a == 0, b !== 0, c !== 0; non-square equation! testLinear()
            }
        } else {
            if (d > 0) {
                n = 2;              // a !== 0, d > 0; test2()
            } else if (d == 0) {
                n = 1;              // a !== 0, d == 0; test1()
            } else {
                n = 0;              // a !== 0, d < 0; no solution; test0()
            }
        }

    }

    public int rootNumber() {
        return n;
    }
}
