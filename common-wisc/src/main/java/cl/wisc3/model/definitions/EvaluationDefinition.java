package cl.wisc3.model.definitions;

import cl.wisc3.config.JPA;
import cl.wisc3.enums.EvaluationType;
import cl.wisc3.model.base.NamedBaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Entity
public class EvaluationDefinition extends NamedBaseEntity {

    private int maxRange;
    private int alternativeMaxRange;
    @Enumerated(EnumType.STRING)
    private EvaluationType type;

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public int getAlternativeMaxRange() {
        return alternativeMaxRange;
    }

    public void setAlternativeMaxRange(int alternativeMaxRange) {
        this.alternativeMaxRange = alternativeMaxRange;
    }

    public EvaluationType getType() {
        return type;
    }

    public void setType(EvaluationType type) {
        this.type = type;
    }

    public static EvaluationDefinition findByAltKey(String altKey) {
        return JPA.findByAltKey(EvaluationDefinition.class, altKey);
    }

    public static List<EvaluationDefinition> findAll() {
        return JPA.findAll(EvaluationDefinition.class, ORDER);
    }

    public static List<EvaluationDefinition> findByEvaluationType(EvaluationType type) {
        return JPA.query("SELECT d FROM EvaluationDefinition d WHERE d.type = ?1 ORDER BY d.entityOrder", type);
    }
}
