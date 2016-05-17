package cl.wisc3.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class AgeDetails implements Serializable {

    public static final int DAYS_IN_MONTH = 30;
    public static final int MONTHS_IN_YEAR = 12;
    private int days;
    private int months;
    private int years;

    public AgeDetails(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        days = Math.min(DAYS_IN_MONTH, calendar.get(Calendar.DATE));
        months = calendar.get(Calendar.MONTH) + 1;
        years = calendar.get(Calendar.YEAR);
    }

    public AgeDetails(int days, int months, int years) {
        this.days = Math.min(DAYS_IN_MONTH, days);
        this.months = months;
        this.years = years;
    }

    public int getDays() {
        return days;
    }

    public int getMonths() {
        return months;
    }

    public int getYears() {
        return years;
    }

    @Override
    public String toString() {
        return String.format("%d años, %d meses y %d días", years, months, days);
    }

    /**
     * Subtracts a certain age to this one. Returns a new AgeDetails with
     * the specific years, months and years.
     *
     * @param ageToSubtract the age to subtract.
     * @return a new ageDetails.
     */
    public AgeDetails subtract(AgeDetails ageToSubtract) {
        int nDays = days - ageToSubtract.days;
        int nMonths = months - ageToSubtract.months;
        int nYears = years - ageToSubtract.years;
        if (nDays < 0) {
            nDays += DAYS_IN_MONTH;
            --nMonths;
        }
        if (nMonths < 0) {
            nMonths += MONTHS_IN_YEAR;
            --nYears;
        }
        return new AgeDetails(nDays, nMonths, nYears);
    }
}
