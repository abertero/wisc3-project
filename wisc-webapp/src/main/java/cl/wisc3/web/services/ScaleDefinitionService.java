package cl.wisc3.web.services;

import cl.wisc3.enums.Scale;
import cl.wisc3.model.definitions.ScaleDefinition;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ScaleDefinitionService {

    public Map<Integer, ScaleDefinition> getFromScale(Scale scale) {
        List<ScaleDefinition> byScale = ScaleDefinition.findByScale(scale);
        Map<Integer, ScaleDefinition> result = new LinkedHashMap<>();
        for (ScaleDefinition definition : byScale) {
            result.put(definition.getScaleScoreSum(), definition);
        }
        return result;
    }
}
