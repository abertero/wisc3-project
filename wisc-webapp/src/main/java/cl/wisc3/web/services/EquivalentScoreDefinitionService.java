package cl.wisc3.web.services;

import cl.wisc3.model.definitions.EquivalentScoreTableColumn;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EquivalentScoreDefinitionService {

    public Map<String, Map<Integer, String>> getTableColumnsMapWithDefinitionKeyFromChildLevelKey(String childLevelAltKey) {
        Map<String, Map<Integer, String>> result = new HashMap<>();
        List<EquivalentScoreTableColumn> listByChildLevel = EquivalentScoreTableColumn.findListByChildLevel(childLevelAltKey);
        for (EquivalentScoreTableColumn equivalentScoreTableColumn : listByChildLevel) {
            List<String> rowList = equivalentScoreTableColumn.getRowList();
            Map<Integer, String> valuesByEquivalentScore = new HashMap<>();
            int i = 1;
            for (String value : rowList) {
                valuesByEquivalentScore.put(i, value);
                ++i;
            }
            result.put(equivalentScoreTableColumn.getEvaluationDefinition().getAltKey(), valuesByEquivalentScore);
        }
        return result;
    }
}
