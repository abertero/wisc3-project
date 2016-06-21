package cl.wisc3.model.child;

import cl.wisc3.beans.AgeDetails;
import cl.wisc3.config.JPA;
import cl.wisc3.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ChildEvaluation extends BaseEntity {
    @ManyToOne
    private ChildInfo child;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ChildEvaluationScore> scores = new ArrayList<ChildEvaluationScore>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<ChildScaleScore> scaleScores = new ArrayList<ChildScaleScore>();
    private int testDay;
    private int testMonth;
    private int testYear;
    private Date testDate;
    private int ageYear;
    private int ageMonth;
    private int ageDay;

    public ChildInfo getChild() {
        return child;
    }

    public void setChild(ChildInfo child) {
        this.child = child;
    }

    public List<ChildEvaluationScore> getScores() {
        return scores;
    }

    public void setScores(List<ChildEvaluationScore> scores) {
        this.scores = scores;
    }

    public List<ChildScaleScore> getScaleScores() {
        return scaleScores;
    }

    public void setScaleScores(List<ChildScaleScore> scaleScores) {
        this.scaleScores = scaleScores;
    }

    public int getTestDay() {
        return testDay;
    }

    public void setTestDay(int testDay) {
        this.testDay = testDay;
    }

    public int getTestMonth() {
        return testMonth;
    }

    public void setTestMonth(int testMonth) {
        this.testMonth = testMonth;
    }

    public int getTestYear() {
        return testYear;
    }

    public void setTestYear(int testYear) {
        this.testYear = testYear;
    }

    public int getAgeYear() {
        return ageYear;
    }

    public void setAgeYear(int ageYear) {
        this.ageYear = ageYear;
    }

    public int getAgeMonth() {
        return ageMonth;
    }

    public void setAgeMonth(int ageMonth) {
        this.ageMonth = ageMonth;
    }

    public int getAgeDay() {
        return ageDay;
    }

    public void setAgeDay(int ageDay) {
        this.ageDay = ageDay;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public AgeDetails getTestDateDetails() {
        return new AgeDetails(testDay, testMonth, testYear);
    }

    public AgeDetails getChildAgeDetails() {
        return new AgeDetails(ageDay, ageMonth, ageYear);
    }

    public static List<ChildEvaluation> getByChild(String altKey) {
        return JPA.query("SELECT e FROM ChildEvaluation e INNER JOIN e.child c WHERE c.altKey = ?1 ORDER BY e.testDate", altKey);
    }

    public static ChildEvaluation findByAltKey(String altKey) {
        return JPA.findByAltKey(ChildEvaluation.class, altKey);
    }
}
