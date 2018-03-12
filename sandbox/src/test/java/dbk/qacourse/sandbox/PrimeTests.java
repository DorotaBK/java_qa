package dbk.qacourse.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

    @Test
    public void testPrimeInt() {
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));       // Integer.MAX_VALUE is the prime number, >2bln
    }

    @Test
    public void testPrimeIntFast() {
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));   // each divisor of the number N is smaller than N/2
    }

    @Test
    public void testPrimeIntFastSrqt() {
        Assert.assertTrue(Primes.isPrimeFastSqrt(Integer.MAX_VALUE));
    }

    @Test (enabled = false)
    public void testPrimeLong() {
        long n = Integer.MAX_VALUE;                                 // Integer.MAX_VALUE converted to long type
        Assert.assertTrue(Primes.isPrime(n));
    }

    @Test
    public void testNonPrime() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }
}
