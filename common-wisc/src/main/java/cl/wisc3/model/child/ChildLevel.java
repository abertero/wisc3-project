package cl.wisc3.model.child;

import cl.wisc3.beans.AgeDetails;
import cl.wisc3.config.JPA;
import cl.wisc3.model.base.NamedBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class ChildLevel extends NamedBaseEntity {
    private int yearsStart;
    private int yearsEnd;
    private int monthsStart;
    private int monthsEnd;
    private int daysStart;
    private int daysEnd;
    @Transient
    private AgeDetails startDetails;
    @Transient
    private AgeDetails endDetails;

    public AgeDetails getStartDetails() {
        if (startDetails == null) {
            startDetails = new AgeDetails(daysStart, monthsStart, yearsStart);
        }
        return startDetails;
    }

    public AgeDetails getEndDetails() {
        if (endDetails == null) {
            endDetails = new AgeDetails(daysEnd, monthsEnd, yearsEnd);
        }
        return endDetails;
    }

    public int getYearsStart() {
        return yearsStart;
    }

    public void setYearsStart(int yearsStart) {
        this.yearsStart = yearsStart;
        this.startDetails = null;
    }

    public int getYearsEnd() {
        return yearsEnd;
    }

    public void setYearsEnd(int yearsEnd) {
        this.yearsEnd = yearsEnd;
        this.endDetails = null;
    }

    public int getMonthsStart() {
        return monthsStart;
    }

    public void setMonthsStart(int monthsStart) {
        this.monthsStart = monthsStart;
        this.startDetails = null;
    }

    public int getMonthsEnd() {
        return monthsEnd;
    }

    public void setMonthsEnd(int monthsEnd) {
        this.monthsEnd = monthsEnd;
        this.endDetails = null;
    }

    public int getDaysStart() {
        return daysStart;
    }

    public void setDaysStart(int daysStart) {
        this.daysStart = daysStart;
        this.startDetails = null;
    }

    public int getDaysEnd() {
        return daysEnd;
    }

    public void setDaysEnd(int daysEnd) {
        this.daysEnd = daysEnd;
        this.endDetails = null;
    }

    public static ChildLevel findByAltKey(String altKey) {
        return JPA.findByAltKey(ChildLevel.class, altKey);
    }

    public static List<ChildLevel> findAll() {
        return JPA.findAll(ChildLevel.class, ORDER);
    }

    public static ChildLevel findByChildAge(AgeDetails ageDetails) {
        List<ChildLevel> byYear = findByYear(ageDetails.getYears());
        if (byYear.size() > 1) {
            for (ChildLevel level : byYear) {
                if (level.contains(ageDetails)) {
                    return level;
                }
            }
        }
        return byYear.get(0);
    }

    private boolean contains(AgeDetails ageDetails) {
        if (yearsStart <= ageDetails.getYears() && ageDetails.getYears() <= yearsEnd) {
            if (monthsStart <= ageDetails.getMonths() && ageDetails.getMonths() <= monthsEnd) {
                if (daysStart <= ageDetails.getDays() && ageDetails.getDays() <= daysEnd) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<ChildLevel> findByYear(int years) {
        return JPA.query("SELECT l FROM ChildLevel l WHERE l.yearsStart = ?1 OR l.yearsEnd = ?2 ORDER BY l.monthsStart", years, years);
    }
}
