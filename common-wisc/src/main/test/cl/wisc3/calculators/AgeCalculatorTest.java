package cl.wisc3.calculators;

import cl.wisc3.beans.AgeDetails;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class AgeCalculatorTest {

    private Calendar calendar = Calendar.getInstance();
    private AgeCalculator ageCalculator = AgeCalculator.INSTANCE;

    @Test
    public void testCalculate1() {
        calendar.set(2005, 7, 19);
        Date dateOfTest = calendar.getTime();
        calendar.set(1996, 2, 10);
        Date dateOfBirth = calendar.getTime();
        AgeDetails result = ageCalculator.calculate(dateOfTest, dateOfBirth);
        Assert.assertEquals(result.getYears(), 9);
        Assert.assertEquals(result.getMonths(), 5);
        Assert.assertEquals(result.getDays(), 9);
    }

    @Test
    public void testCalculate2() {
        calendar.set(2005, 8, 19);
        Date dateOfTest = calendar.getTime();
        calendar.set(1990, 5, 29);
        Date dateOfBirth = calendar.getTime();
        AgeDetails result = ageCalculator.calculate(dateOfTest, dateOfBirth);
        Assert.assertEquals(result.getYears(), 15);
        Assert.assertEquals(result.getMonths(), 2);
        Assert.assertEquals(result.getDays(), 20);
    }

    @Test
    public void testCalculate3() {
        calendar.set(2005, 7, 26);
        Date dateOfTest = calendar.getTime();
        calendar.set(1991, 10, 25);
        Date dateOfBirth = calendar.getTime();
        AgeDetails result = ageCalculator.calculate(dateOfTest, dateOfBirth);
        Assert.assertEquals(result.getYears(), 13);
        Assert.assertEquals(result.getMonths(), 9);
        Assert.assertEquals(result.getDays(), 1);
    }

    @Test
    public void testCalculate4() {
        calendar.set(2005, 8, 20);
        Date dateOfTest = calendar.getTime();
        calendar.set(1994, 9, 28);
        Date dateOfBirth = calendar.getTime();
        AgeDetails result = ageCalculator.calculate(dateOfTest, dateOfBirth);
        Assert.assertEquals(result.getYears(), 10);
        Assert.assertEquals(result.getMonths(), 10);
        Assert.assertEquals(result.getDays(), 22);
    }
}
