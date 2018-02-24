package dbk.qacourse.homework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testPointAB(){
        Point p1 = new Point(7,5);
        Point p2 = new Point(3,9);
        Assert.assertEquals(p1.distance(p2), 5.656854249492381);
    }

    @Test
    public void testPointBC(){
        Point p1 = new Point(3,9);
        Point p2 = new Point(2,8);
        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    }
}
