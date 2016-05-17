package cl.wisc3.model.definitions;

import cl.wisc3.config.JPA;
import cl.wisc3.model.base.BaseEntity;
import cl.wisc3.model.child.ChildLevel;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.*;

@Entity
public class EquivalentScoreTableColumn extends BaseEntity {

    public static final String ROW_STRING_REGEX = "#!#";
    private static final String CELL_SPLIT_CHAR = "-";
    private static final int WRONG_INT_VALUE = -1;

    private String rowString;
    @ManyToOne(fetch = FetchType.LAZY)
    private ChildLevel childLevel;
    @ManyToOne(fetch = FetchType.LAZY)
    private EvaluationDefinition evaluationDefinition;
    @Transient
    public List<String> rowList = new ArrayList<String>();

    public EquivalentScoreTableColumn() {
    }

    public EquivalentScoreTableColumn(ChildLevel childLevel, EvaluationDefinition evaluationDefinition) {
        this.childLevel = childLevel;
        this.evaluationDefinition = evaluationDefinition;
    }

    public String getRowString() {
        return rowString;
    }

    public void setRowString(String rowString) {
        this.rowString = rowString;
        this.rowList.clear();
    }

    public ChildLevel getChildLevel() {
        return childLevel;
    }

    public void setChildLevel(ChildLevel childLevel) {
        this.childLevel = childLevel;
    }

    public EvaluationDefinition getEvaluationDefinition() {
        return evaluationDefinition;
    }

    public void setEvaluationDefinition(EvaluationDefinition evaluationDefinition) {
        this.evaluationDefinition = evaluationDefinition;
    }

    public List<String> getRowList() {
        if (rowList.isEmpty() && StringUtils.isNotBlank(rowString)) {
            String[] splitRow = rowString.split(ROW_STRING_REGEX);
            rowList.addAll(Arrays.asList(splitRow));
        }
        return rowList;
    }

    public void loadRowStringFromList(List<String> rowList) {
        StringBuilder builder = new StringBuilder();
        for (String element : rowList) {
            builder.append(element).append(ROW_STRING_REGEX);
        }
        this.rowString = builder.toString();
        this.rowList.clear();
        this.rowList.addAll(rowList);
    }

    public static List<EquivalentScoreTableColumn> findListByChildLevel(String childLevelAltKey) {
        return JPA.query("SELECT es FROM EquivalentScoreTableColumn es INNER JOIN es.childLevel cl WHERE cl.altKey = ?1 ORDER BY es.entityOrder", childLevelAltKey);
    }

    public boolean validate() {
        int currentMax = -1;
        for (String element : rowList) {
            if (StringUtils.isNotBlank(element)) {
                if (element.indexOf(CELL_SPLIT_CHAR) == WRONG_INT_VALUE) {
                    int nextValue = validateValueIsOneGreaterThanCurrent(element, currentMax);
                    if (nextValue != WRONG_INT_VALUE) {
                        currentMax = nextValue;
                    } else {
                        return false;
                    }
                } else {
                    String[] splitElements = element.split(CELL_SPLIT_CHAR);
                    if (splitElements.length != 2) {
                        return false;
                    }
                    int minValue = validateValueIsOneGreaterThanCurrent(splitElements[0], currentMax);
                    if (minValue != WRONG_INT_VALUE) {
                        int maxValue = validateIfValueIsGreater(splitElements[1], minValue);
                        if (maxValue != WRONG_INT_VALUE) {
                            currentMax = maxValue;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        int maxRange = evaluationDefinition.getMaxRange();
        if (currentMax != maxRange) {
            return false;
        }
        return true;
    }

    public Map<Integer, Integer> getEquivalentScores() {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        int i = 1;
        for (String elements : rowList) {
            if (StringUtils.isNotBlank(elements)) {
                if (elements.indexOf(CELL_SPLIT_CHAR) == WRONG_INT_VALUE) {
                    result.put(NumberUtils.toInt(elements), i);
                } else {
                    String[] splitElements = elements.split(CELL_SPLIT_CHAR);
                    for (int j = NumberUtils.toInt(splitElements[0]); j <= NumberUtils.toInt(splitElements[1]); ++j) {
                        result.put(j, i);
                    }
                }
            }
            ++i;
        }
        return result;
    }

    private int validateValueIsOneGreaterThanCurrent(String valueToValidate, int currentMaxValue) {
        if (!NumberUtils.isNumber(valueToValidate)) {
            return WRONG_INT_VALUE;
        }
        int intValue = NumberUtils.toInt(valueToValidate);
        if (intValue - currentMaxValue != 1) {
            return WRONG_INT_VALUE;
        }
        return intValue;
    }

    private int validateIfValueIsGreater(String valueToValidate, int valueToCompare) {
        if (!NumberUtils.isNumber(valueToValidate)) {
            return WRONG_INT_VALUE;
        }
        int intValue = NumberUtils.toInt(valueToValidate);
        if (intValue > valueToCompare) {
            return intValue;
        }
        return WRONG_INT_VALUE;
    }

    public static EquivalentScoreTableColumn findByChildLevenAndEvaluationDefinition(String childLevelAltKey, String evaluationDefinitionAltKey) {
        return JPA.queryFirst("SELECT tc FROM EquivalentScoreTableColumn tc INNER JOIN tc.childLevel cl INNER JOIN tc.evaluationDefinition ed WHERE cl.altKey = ?1 AND ed.altKey = ?2", childLevelAltKey, evaluationDefinitionAltKey);
    }
}
