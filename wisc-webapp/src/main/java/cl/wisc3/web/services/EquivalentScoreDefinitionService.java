package cl.wisc3.web.services;

import cl.wisc3.model.child.ChildLevel;
import cl.wisc3.model.definitions.EquivalentScoreDefinition;
import cl.wisc3.model.definitions.EquivalentScoreTableColumn;
import cl.wisc3.model.definitions.EvaluationDefinition;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EquivalentScoreDefinitionService {
    private static final String SPLIT_KEY_VALUES = "#";

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

    public boolean saveScore(ChildLevel childLevel, Map<String, String> values) {
        Map<String, String[]> scoreByAltKey = new HashMap<>();
        for (Map.Entry<String, String> valuesEntry : values.entrySet()) {
            String[] keyArray = valuesEntry.getKey().split(SPLIT_KEY_VALUES);
            int equivalentScore = NumberUtils.toInt(keyArray[0]);
            String evaluationDefinitionAltKey = keyArray[1];
            if (scoreByAltKey.get(evaluationDefinitionAltKey) == null) {
                scoreByAltKey.put(evaluationDefinitionAltKey, new String[EquivalentScoreDefinition.MAX_EQUIVALENT_SCORE]);
            }
            scoreByAltKey.get(evaluationDefinitionAltKey)[equivalentScore - 1] = valuesEntry.getValue().trim();
        }
        for (Map.Entry<String, String[]> scoreEntry : scoreByAltKey.entrySet()) {
            EvaluationDefinition evaluationDefinition = EvaluationDefinition.findByAltKey(scoreEntry.getKey());
            EquivalentScoreTableColumn column = EquivalentScoreTableColumn.findByChildLevenAndEvaluationDefinition(childLevel.getAltKey(), evaluationDefinition.getAltKey());
            if (column == null) {
                column = new EquivalentScoreTableColumn(childLevel, evaluationDefinition);
            }
            column.loadRowStringFromList(Arrays.asList(scoreEntry.getValue()));
            if (column.validate()) {
                if (!column.save()) {
                    return false;
                }
                Map<Integer, Integer> equivalentScores = column.getEquivalentScores();
                for (Map.Entry<Integer, Integer> equivalentScoreEntry : equivalentScores.entrySet()) {
                    Integer evaluationScore = equivalentScoreEntry.getKey();
                    Integer equivalentScore = equivalentScoreEntry.getValue();
                    EquivalentScoreDefinition definition = EquivalentScoreDefinition.findByChildLevelAndEvaluationDefinitionAndEvaluationScore(childLevel.getAltKey(), evaluationDefinition.getAltKey(), evaluationScore);
                    if (definition == null) {
                        definition = new EquivalentScoreDefinition(evaluationScore, equivalentScore, childLevel, evaluationDefinition);
                    } else {
                        definition.setEquivalentScore(equivalentScore);
                    }
                    if (!definition.save()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
