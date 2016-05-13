package cl.wisc3.calculators;

import cl.wisc3.beans.AgeDetails;

import java.util.Date;

public enum AgeCalculator {
    INSTANCE;

    public AgeDetails calculate(Date dateOfTest, Date dateOfBirth) {
        return calculate(new AgeDetails(dateOfTest), new AgeDetails(dateOfBirth));
    }

    public AgeDetails calculate(AgeDetails dateOfTestDetail, AgeDetails dateOfBirthDetail) {
        return (dateOfTestDetail).subtract(dateOfBirthDetail);
    }
}