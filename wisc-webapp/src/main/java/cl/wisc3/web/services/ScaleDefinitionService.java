package cl.wisc3.web.services;

import cl.wisc3.enums.Scale;
import cl.wisc3.model.definitions.ScaleDefinition;
import cl.wisc3.web.beans.wrapper.ScaleDefinitionWrapper;
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

    public boolean saveScaleDefinitions(Scale scale, ScaleDefinitionWrapper wrapper) {
        Map<Integer, ScaleDefinition> definitionsMap = getFromScale(scale);
        if (definitionsMap.isEmpty()) {
            for (ScaleDefinition scaleDefinition : wrapper.getDefinitions()) {
                scaleDefinition.setScale(scale);
                if (!scaleDefinition.save()) {
                    return false;
                }
            }
        } else {
            for (ScaleDefinition scaleDefinition : wrapper.getDefinitions()) {
                ScaleDefinition fromDb = definitionsMap.get(scaleDefinition.getScaleScoreSum());
                fromDb.loadFromScaleDefinition(scaleDefinition);
                if (!scaleDefinition.save()) {
                    return false;
                }
            }
        }
        return true;
    }
}
