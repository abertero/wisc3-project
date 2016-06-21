package cl.wisc3.model.child;

import cl.wisc3.model.base.BaseEntity;
import cl.wisc3.model.definitions.EvaluationDefinition;
import cl.wisc3.model.definitions.ScaleDefinition;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class ChildScaleScore extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private ChildEvaluation evaluation;
    @ManyToOne(fetch = FetchType.EAGER)
    private ScaleDefinition definition;
    private int sumScore;
    private int ci;

    public ChildEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(ChildEvaluation evaluation) {
        this.evaluation = evaluation;
    }

    public ScaleDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(ScaleDefinition definition) {
        this.definition = definition;
    }

    public int getSumScore() {
        return sumScore;
    }

    public void setSumScore(int sumScore) {
        this.sumScore = sumScore;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }
}
