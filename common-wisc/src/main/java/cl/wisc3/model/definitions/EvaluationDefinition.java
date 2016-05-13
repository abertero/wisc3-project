package cl.wisc3.model.definitions;

import cl.wisc3.enums.EvaluationType;
import cl.wisc3.model.base.NamedBaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class EvaluationDefinition extends NamedBaseEntity {

    private int maxRange;
    @Enumerated(EnumType.STRING)
    private EvaluationType type;

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public EvaluationType getType() {
        return type;
    }

    public void setType(EvaluationType type) {
        this.type = type;
    }
}
