package cl.wisc3.model.child;

import cl.wisc3.model.base.BaseEntity;
import cl.wisc3.model.definitions.EvaluationDefinition;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class ChildEvaluationScore extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private ChildEvaluation evaluation;
    @ManyToOne(fetch = FetchType.EAGER)
    private EvaluationDefinition definition;
    private int score;
    private int realScore;

    public ChildEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(ChildEvaluation evaluation) {
        this.evaluation = evaluation;
    }

    public EvaluationDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(EvaluationDefinition definition) {
        this.definition = definition;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRealScore() {
        return realScore;
    }

    public void setRealScore(int realScore) {
        this.realScore = realScore;
    }
}
