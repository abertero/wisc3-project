package cl.wisc3.model.definitions;

import cl.wisc3.config.JPA;
import cl.wisc3.model.base.BaseEntity;
import cl.wisc3.model.child.ChildLevel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class EquivalentScoreDefinition extends BaseEntity {
    public static int MAX_EQUIVALENT_SCORE = 19;

    private int evaluationScore;
    private int equivalentScore;
    @ManyToOne(fetch = FetchType.LAZY)
    private ChildLevel childLevel;
    @ManyToOne(fetch = FetchType.LAZY)
    private EvaluationDefinition evaluationDefinition;

    public EquivalentScoreDefinition() {
    }

    public EquivalentScoreDefinition(int evaluationScore, int equivalentScore, ChildLevel childLevel, EvaluationDefinition evaluationDefinition) {
        this.evaluationScore = evaluationScore;
        this.equivalentScore = equivalentScore;
        this.childLevel = childLevel;
        this.evaluationDefinition = evaluationDefinition;
    }

    public int getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(int evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public int getEquivalentScore() {
        return equivalentScore;
    }

    public void setEquivalentScore(int equivalentScore) {
        this.equivalentScore = equivalentScore;
    }

    public ChildLevel getChildLevel() {
        return childLevel;
    }

    public void setChildLevel(ChildLevel childLevel) {
        this.childLevel = childLevel;
    }

    public EvaluationDefinition getEvaluationDefinition() {
        return evaluationDefinition;
    }

    public void setEvaluationDefinition(EvaluationDefinition evaluationDefinition) {
        this.evaluationDefinition = evaluationDefinition;
    }

    public static EquivalentScoreDefinition findByChildLevelAndEvaluationDefinitionAndEvaluationScore(String childLevelAltKey, String evaluationDefinitionAltKey, Integer evaluationScore) {
        return JPA.queryFirst("SELECT ec FROM EquivalentScoreDefinition ec INNER JOIN ec.childLevel cl INNER JOIN ec.evaluationDefinition ed WHERE cl.altKey = ?1 AND ed.altKey = ?2 AND ec.evaluationScore = ?3", childLevelAltKey, evaluationDefinitionAltKey, evaluationScore);
    }
}
