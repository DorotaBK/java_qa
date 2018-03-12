package dbk.qacourse.sandbox;

public class Primes {

    // FOR is more often used than WHILE
    public static boolean isPrime(int n) {
        for(int i = 2; i < n; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeFast(int n) {
        // each divisor of the number N is smaller than N/2
        for(int i = 2; i < n/2; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeFastSqrt(int n) {
        int m = (int) Math.sqrt(n);    // square from n is converted to a long type
        // n = m * i --- 'm' and 'i' are divisors of 'n'; at least one divisor must be smaller than the square of 'n'
        for(int i = 2; i < m; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeWhile(int n) {
        int i = 2;
        while(i < n && n % i != 0){
            i++;
        }
        return i == n;
    }

    public static boolean isPrime(long n) {
        for(int i = 2; i < n; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
