package cl.wisc3.web.services;

import cl.wisc3.beans.AgeDetails;
import cl.wisc3.calculators.AgeCalculator;
import cl.wisc3.enums.EvaluationType;
import cl.wisc3.enums.Scale;
import cl.wisc3.model.child.*;
import cl.wisc3.model.definitions.EquivalentScoreDefinition;
import cl.wisc3.model.definitions.EvaluationDefinition;
import cl.wisc3.model.definitions.EvaluationDefinitionScale;
import cl.wisc3.model.definitions.ScaleDefinition;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EvaluationDefinitionService {

    private static final String DAY_VALUE = "DAY";
    private static final String MONTH_VALUE = "MONTH";
    private static final String YEAR_VALUE = "YEAR";

    public List<EvaluationDefinition> findByEvaluationType(EvaluationType type) {
        return EvaluationDefinition.findByEvaluationType(type);
    }

    public void saveEvaluation(ChildInfo child, Map<String, Integer> values) {
        ChildEvaluation evaluation = new ChildEvaluation();
        evaluation.setChild(child);
        evaluation.setAgeDay(child.getBirthDay());
        evaluation.setAgeMonth(child.getBirthMonth());
        evaluation.setAgeYear(child.getBirthYear());
        Integer testDay = values.get(DAY_VALUE);
        Integer testMonth = values.get(MONTH_VALUE);
        Integer testYear = values.get(YEAR_VALUE);
        evaluation.setTestDay(testDay);
        evaluation.setTestMonth(testMonth);
        evaluation.setTestYear(testYear);
        Calendar calendar = Calendar.getInstance();
        calendar.set(testYear, testMonth, testDay);
        evaluation.setTestDate(calendar.getTime());
        evaluation.save();
        values.remove(DAY_VALUE);
        values.remove(MONTH_VALUE);
        values.remove(YEAR_VALUE);
        Map<Scale, Integer> scaleSum = new HashMap<>();
        for (Scale scale : Scale.values()) {
            scaleSum.put(scale, 0);
        }
        for (Map.Entry<String, Integer> entry : values.entrySet()) {
            EvaluationDefinition definition = EvaluationDefinition.findByAltKey(entry.getKey());
            if (definition != null) {
                int realScore = calculateScore(definition, evaluation, entry.getValue());
                for (EvaluationDefinitionScale evaluationDefinitionScale : definition.getScales()) {
                    Integer currentScore = scaleSum.get(evaluationDefinitionScale.getScale());
                    scaleSum.put(evaluationDefinitionScale.getScale(), currentScore + realScore);
                }
            } else {
                continue;
            }
        }
        for (Map.Entry<Scale, Integer> scaleSumEntry : scaleSum.entrySet()) {
            ChildScaleScore scaleScore = new ChildScaleScore();
            scaleScore.setEvaluation(evaluation);
            scaleScore.setSumScore(scaleSumEntry.getValue());
            ScaleDefinition scaleDefinition = ScaleDefinition.findByScaleAndSum(scaleSumEntry.getKey(), scaleSumEntry.getValue());
            scaleScore.setDefinition(scaleDefinition);
            scaleScore.setCi(scaleDefinition.getCi());
            scaleScore.save();
        }
    }

    private int calculateScore(EvaluationDefinition definition, ChildEvaluation evaluation, int scoreInt) {
        ChildEvaluationScore score = new ChildEvaluationScore();
        score.setDefinition(definition);
        score.setEvaluation(evaluation);
        score.setScore(scoreInt);
        AgeDetails ageDetails = AgeCalculator.INSTANCE.calculate(evaluation.getTestDateDetails(), evaluation.getChildAgeDetails());
        ChildLevel level = ChildLevel.findByChildAge(ageDetails);
        EquivalentScoreDefinition equivalentScore = EquivalentScoreDefinition.findByChildLevelAndEvaluationDefinitionAndEvaluationScore(level.getAltKey(), definition.getAltKey(), scoreInt);
        int realTotal = equivalentScore.getEquivalentScore();
        score.setRealScore(realTotal);
        score.save();
        return realTotal;
    }

    public ChildEvaluation findEvaluationByAltKey(String altKey) {
        return ChildEvaluation.findByAltKey(altKey);
    }

    public List<ChildEvaluationScore> findScoresByEvaluationAltKey(String altKey) {
        return ChildEvaluationScore.findByChildEvaluationAltKey(altKey);
    }

    public void saveScales(List<String> scales) {
        for (String scaleDefinition : scales) {
            String[] scaleDefinitionArray = scaleDefinition.split("#");
            String definitionAltKey = scaleDefinitionArray[0];
            String scaleCode = scaleDefinitionArray[1];
            EvaluationDefinition definition = EvaluationDefinition.findByAltKey(definitionAltKey);
            Scale scale = Scale.fromCode(scaleCode);
            EvaluationDefinitionScale evaluationDefinitionScale = EvaluationDefinitionScale.findByEvaluationDefinitionAltKeyAndScale(definitionAltKey, scale);
            if(evaluationDefinitionScale == null) {
                evaluationDefinitionScale = new EvaluationDefinitionScale();
                evaluationDefinitionScale.setDefinition(definition);
                evaluationDefinitionScale.setScale(scale);
                evaluationDefinitionScale.save();
            }
        }
    }

    public Map<String, Map<String, Boolean>> getScalesAsMap() {
        HashMap<String, Map<String, Boolean>> result = new HashMap<>();
        List<EvaluationDefinitionScale> definitionScales = EvaluationDefinitionScale.findAll();
        for (EvaluationDefinitionScale evaluationDefinitionScale : definitionScales) {
            if (result.get(evaluationDefinitionScale.getDefinition().getAltKey()) == null) {
                result.put(evaluationDefinitionScale.getDefinition().getAltKey(), new HashMap<String, Boolean>());
            }
            result.get(evaluationDefinitionScale.getDefinition().getAltKey()).put(evaluationDefinitionScale.getScale().getCode(), true);
        }
        return result;
    }
}
