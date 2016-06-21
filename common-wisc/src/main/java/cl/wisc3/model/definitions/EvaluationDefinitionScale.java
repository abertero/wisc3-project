package cl.wisc3.model.definitions;

import cl.wisc3.enums.Scale;
import cl.wisc3.model.base.BaseEntity;

import javax.persistence.*;

@Entity
public class EvaluationDefinitionScale extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private EvaluationDefinition definition;
    @Enumerated(EnumType.STRING)
    private Scale scale;

    public EvaluationDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(EvaluationDefinition definition) {
        this.definition = definition;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }
}
