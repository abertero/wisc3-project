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
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.*;

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
        Integer testDay = values.get(DAY_VALUE);
        Integer testMonth = values.get(MONTH_VALUE);
        Integer testYear = values.get(YEAR_VALUE);
        evaluation.setTestDay(testDay);
        evaluation.setTestMonth(testMonth);
        evaluation.setTestYear(testYear);
        Calendar calendar = Calendar.getInstance();
        calendar.set(testYear, testMonth, testDay);
        evaluation.setTestDate(calendar.getTime());
        AgeDetails ageDetails = AgeCalculator.INSTANCE.calculate(evaluation.getTestDateDetails(), child.getBirthDetail());
        evaluation.setAgeDay(ageDetails.getDays());
        evaluation.setAgeMonth(ageDetails.getMonths());
        evaluation.setAgeYear(ageDetails.getYears());
        evaluation.save();
        values.remove(DAY_VALUE);
        values.remove(MONTH_VALUE);
        values.remove(YEAR_VALUE);
        Map<String, Integer> realScoreDefinition = new HashMap<>();
        for (Map.Entry<String, Integer> entry : values.entrySet()) {
            EvaluationDefinition definition = EvaluationDefinition.findByAltKey(entry.getKey());
            if (definition != null) {
                int realScore = calculateScore(definition, evaluation, entry.getValue());
                realScoreDefinition.put(definition.getAltKey(), realScore);
            } else {
                continue;
            }
        }
        Map<Scale, Integer> scaleSum = calculateScaleSum(realScoreDefinition);
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

    private Map<Scale, Integer> calculateScaleSum(Map<String, Integer> realScoreDefinition) {
        Map<Scale, Integer> scaleSum = new HashMap<>();
        Map<Scale, List<EvaluationDefinitionScale>> evaluationDefinitionScalesAsMap = EvaluationDefinitionScale.findAllByScaleAsMap();
        for (Scale scale : Scale.values()) {
            int scaleSumValue = 0;
            List<EvaluationDefinitionScale> definitionScales = evaluationDefinitionScalesAsMap.get(scale);
            if (scale.isCheckForComplementary()) {
                Set<String> hasComplementSet = new HashSet<>();
                EvaluationDefinitionScale complement = null;
                Set<String> keysWithZeroScore = new HashSet<>();
                for (EvaluationDefinitionScale definitionScale : definitionScales) {
                    if (definitionScale.getDefinition().isComplementary()) {
                        if (definitionScale.getDefinition().getComplementOf() != null) {
                            hasComplementSet.add(definitionScale.getDefinition().getComplementOf().getAltKey());
                            Integer complementRealScore = realScoreDefinition.get(definitionScale.getDefinition().getComplementOf().getAltKey());
                            if (complementRealScore == null || complementRealScore == 0) {
                                Integer realScore = realScoreDefinition.get(definitionScale.getDefinition().getAltKey());
                                scaleSumValue += realScore != null ? realScore : 0;
                            }
                        } else {
                            complement = definitionScale;
                        }
                    } else {
                        Integer realScore = realScoreDefinition.get(definitionScale.getDefinition().getAltKey());
                        if (realScore != null && realScore > 0) {
                            scaleSumValue += realScore;
                        } else {
                            keysWithZeroScore.add(definitionScale.getDefinition().getAltKey());
                        }
                    }
                }
                if (complement != null && !keysWithZeroScore.isEmpty()) {
                    for (String keyWithZero : keysWithZeroScore) {
                        if (!hasComplementSet.contains(keyWithZero)) {
                            Integer realScore = realScoreDefinition.get(complement.getDefinition().getAltKey());
                            scaleSumValue += realScore != null ? realScore : 0;
                            break;
                        }
                    }
                }
            } else {
                for (EvaluationDefinitionScale definitionScale : definitionScales) {
                    Integer realScore = realScoreDefinition.get(definitionScale.getDefinition().getAltKey());
                    scaleSumValue += realScore != null ? realScore : 0;
                }
            }
            scaleSum.put(scale, scaleSumValue);
        }
        return scaleSum;
    }

    private int calculateScore(EvaluationDefinition definition, ChildEvaluation evaluation, int scoreInt) {
        ChildEvaluationScore score = new ChildEvaluationScore();
        score.setDefinition(definition);
        score.setEvaluation(evaluation);
        score.setScore(scoreInt);
        ChildLevel level = ChildLevel.findByChildAge(evaluation.getChildAgeDetails());
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
        EvaluationDefinitionScale.deleteAll();
        for (String scaleDefinition : scales) {
            String[] scaleDefinitionArray = scaleDefinition.split("#");
            String definitionAltKey = scaleDefinitionArray[0];
            String scaleCode = scaleDefinitionArray[1];
            EvaluationDefinition definition = EvaluationDefinition.findByAltKey(definitionAltKey);
            Scale scale = Scale.fromCode(scaleCode);
            EvaluationDefinitionScale evaluationDefinitionScale = new EvaluationDefinitionScale();
            evaluationDefinitionScale.setDefinition(definition);
            evaluationDefinitionScale.setScale(scale);
            evaluationDefinitionScale.save();
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

    public List<ChildScaleScore> findScaleScoresByEvaluationAltKey(String altKey) {
        return ChildScaleScore.findByChildEvaluationAltKey(altKey);
    }
}
