package dbk.qacourse.sandbox;

public class Equality {

    public static void main(String[] args) {
        String s1 = "firefox1";
        String s2 = s1;
        String s3 = "firefox3";
        String s4 = new String(s3);

        System.out.println(s1 == s2);           // true
        System.out.println(s1.equals(s2));      // true

        System.out.println(s3 == s4);           // false
        System.out.println(s4.equals(s3));      // true
    }
}