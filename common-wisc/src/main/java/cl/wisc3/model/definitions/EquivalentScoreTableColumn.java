package cl.wisc3.model.definitions;

import cl.wisc3.config.JPA;
import cl.wisc3.model.base.BaseEntity;
import cl.wisc3.model.child.ChildLevel;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.*;

@Entity
public class EquivalentScoreTableColumn extends BaseEntity {

    private static final String ROW_STRING_REGEX = "#!#";
    private String rowString;
    @ManyToOne(fetch = FetchType.LAZY)
    private ChildLevel childLevel;
    @ManyToOne(fetch = FetchType.LAZY)
    private EvaluationDefinition evaluationDefinition;
    @Transient
    public List<String> rowList = new ArrayList<String>();

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
}
