package cl.wisc3.model.definitions;

import cl.wisc3.config.JPA;
import cl.wisc3.enums.Scale;
import cl.wisc3.model.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List<EvaluationDefinitionScale> findAll() {
        return JPA.findAll(EvaluationDefinitionScale.class);
    }

    public static EvaluationDefinitionScale findByEvaluationDefinitionAltKeyAndScale(String definitionAltKey, Scale scale) {
        return JPA.querySingle("SELECT ds FROM EvaluationDefinitionScale ds WHERE ds.definition.altKey = ?1 AND scale = ?2", definitionAltKey, scale);
    }

    public static int deleteAll() {
        return JPA.update("DELETE FROM EvaluationDefinitionScale");
    }

    public static Map<Scale, List<EvaluationDefinitionScale>> findAllByScaleAsMap() {
        Map<Scale, List<EvaluationDefinitionScale>> byMap = new HashMap<Scale, List<EvaluationDefinitionScale>>();
        for (EvaluationDefinitionScale evaluationDefinitionScale : findAll()) {
            if (byMap.get(evaluationDefinitionScale.getScale()) == null) {
                byMap.put(evaluationDefinitionScale.getScale(), new ArrayList<EvaluationDefinitionScale>());
            }
            byMap.get(evaluationDefinitionScale.getScale()).add(evaluationDefinitionScale);
        }
        return byMap;
    }
}
