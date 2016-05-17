package cl.wisc3.web.services;

import cl.wisc3.enums.EvaluationType;
import cl.wisc3.model.definitions.EvaluationDefinition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EvaluationDefinitionService {

    public List<EvaluationDefinition> findByEvaluationType(EvaluationType type) {
        return EvaluationDefinition.findByEvaluationType(type);
    }
}
