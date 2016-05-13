package cl.wisc3.web.beans;

import cl.wisc3.beans.AgeDetails;

import java.io.Serializable;

public class AgeCalculatorHelper implements Serializable {
    private Integer testYear;
    private Integer testMonth;
    private Integer testDay;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;

    public Integer getTestYear() {
        return testYear;
    }

    public Integer getTestMonth() {
        return testMonth;
    }

    public Integer getTestDay() {
        return testDay;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public Integer getBirthDay() {
        return birthDay;
    }

    public void setTestYear(Integer testYear) {
        this.testYear = testYear;
    }

    public void setTestMonth(Integer testMonth) {
        this.testMonth = testMonth;
    }

    public void setTestDay(Integer testDay) {
        this.testDay = testDay;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
    }

    public AgeDetails getBirthDateDetail() {
        return new AgeDetails(birthDay, birthMonth, birthYear);
    }

    public AgeDetails getTestDateDetail() {
        return new AgeDetails(testDay, testMonth, testYear);
    }

    public boolean hasErrors() {
        return birthDay == null || birthMonth == null || birthYear == null || testDay == null || testMonth == null || testYear == null;
    }
}
