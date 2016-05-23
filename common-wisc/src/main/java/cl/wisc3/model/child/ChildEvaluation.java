package cl.wisc3.model.child;

import cl.wisc3.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChildEvaluation extends BaseEntity {
    @ManyToOne
    private ChildInfo child;
    @OneToMany
    private List<ChildEvaluationScore> scores = new ArrayList<ChildEvaluationScore>();
    private int testDay;
    private int testMonth;
    private int testYear;

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
}
