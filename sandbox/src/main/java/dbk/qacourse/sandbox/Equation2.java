package dbk.qacourse.sandbox;

public class Equation2 {

    private double a;
    private double b;
    private double c;

    private int n;  // the number of zero places in the quadratic equation

    public Equation2(double a, double b, double c){

        this.a = a;
        this.b = b;
        this.c = c;

        double d = b*b - 4*a*c; // delta

        // optimization of the Equation1 code
        if (a != 0) {
            if (d > 0) {
                n = 2;              // a !== 0, d > 0; test2()
            } else if (d == 0) {
                n = 1;              // a !== 0, d == 0; test1()
            } else {
                n = 0;              // a !== 0, d < 0; no solution; test0()
            }

        } else if (b != 0) {
            n = 1;                  // a == 0, b !== 0, c !== 0; non-square equation! testLinear()

        } else if (c != 0){
            n = 0;                  // a == 0, b == 0, c !== 0; no solution; testConstant()

        } else {
            n = -1;                 // a == 0, b == 0, c == 0; infinitely many solutions
        }

    }




    public int rootNumber() {
        return n;
    }
}
