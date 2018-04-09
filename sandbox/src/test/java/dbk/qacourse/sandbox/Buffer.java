package dbk.qacourse.sandbox;

import org.testng.annotations.Test;

public class Buffer {

    @Test
    public void test0() {
        StringBuffer newString = new StringBuffer("Ala ");
        System.out.println(newString);
        System.out.println(newString.length());
        newString.append("kota");
        newString.insert(4, "ma ");
        newString.delete(4, 7);
        System.out.println(newString.substring(0, 3));
    }
}