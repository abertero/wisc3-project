package cl.wisc3.enums;

import org.junit.Assert;
import org.junit.Test;

public class IQTest {

    @Test
    public void testQualifyIQ() {
        Assert.assertEquals(IQ.qualifyIQ(10), IQ.INTELLECTUALLY_DEFICIENT);
        Assert.assertEquals(IQ.qualifyIQ(69), IQ.INTELLECTUALLY_DEFICIENT);
        Assert.assertEquals(IQ.qualifyIQ(70), IQ.BOUNDARY);
        Assert.assertEquals(IQ.qualifyIQ(75), IQ.BOUNDARY);
        Assert.assertEquals(IQ.qualifyIQ(79), IQ.BOUNDARY);
        Assert.assertEquals(IQ.qualifyIQ(80), IQ.LOW);
        Assert.assertEquals(IQ.qualifyIQ(85), IQ.LOW);
        Assert.assertEquals(IQ.qualifyIQ(89), IQ.LOW);
        Assert.assertEquals(IQ.qualifyIQ(90), IQ.AVERAGE);
        Assert.assertEquals(IQ.qualifyIQ(100), IQ.AVERAGE);
        Assert.assertEquals(IQ.qualifyIQ(109), IQ.AVERAGE);
        Assert.assertEquals(IQ.qualifyIQ(110), IQ.HIGH);
        Assert.assertEquals(IQ.qualifyIQ(115), IQ.HIGH);
        Assert.assertEquals(IQ.qualifyIQ(119), IQ.HIGH);
        Assert.assertEquals(IQ.qualifyIQ(120), IQ.SUPERIOR);
        Assert.assertEquals(IQ.qualifyIQ(125), IQ.SUPERIOR);
        Assert.assertEquals(IQ.qualifyIQ(129), IQ.SUPERIOR);
        Assert.assertEquals(IQ.qualifyIQ(130), IQ.VERY_SUPERIOR);
        Assert.assertEquals(IQ.qualifyIQ(160), IQ.VERY_SUPERIOR);
    }
}
