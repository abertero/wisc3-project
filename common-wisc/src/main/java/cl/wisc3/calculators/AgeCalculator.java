package cl.wisc3.calculators;

import cl.wisc3.beans.AgeDetails;

import java.util.Date;

public enum AgeCalculator {
    INSTANCE;

    public AgeDetails calculate(Date dateOfTest, Date dateOfBirth) {
        return (new AgeDetails(dateOfTest)).subtract(new AgeDetails(dateOfBirth));
    }
}