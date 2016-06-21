package cl.wisc3.model.definitions;

import cl.wisc3.config.JPA;
import cl.wisc3.enums.Scale;
import cl.wisc3.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Entity
public class ScaleDefinition extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private Scale scale;
    private Integer scaleScoreSum;
    private Integer ci;
    private Double rankPercentage;
    private Integer minConfidenceIntervalNinetyPercent;
    private Integer maxConfidenceIntervalNinetyPercent;
    private Integer minConfidenceIntervalNinetyFivePercent;
    private Integer maxConfidenceIntervalNinetyFivePercent;

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public Integer getScaleScoreSum() {
        return scaleScoreSum;
    }

    public void setScaleScoreSum(Integer scaleScoreSum) {
        this.scaleScoreSum = scaleScoreSum;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public Double getRankPercentage() {
        return rankPercentage;
    }

    public void setRankPercentage(Double rankPercentage) {
        this.rankPercentage = rankPercentage;
    }

    public Integer getMinConfidenceIntervalNinetyPercent() {
        return minConfidenceIntervalNinetyPercent;
    }

    public void setMinConfidenceIntervalNinetyPercent(Integer minConfidenceIntervalNinetyPercent) {
        this.minConfidenceIntervalNinetyPercent = minConfidenceIntervalNinetyPercent;
    }

    public Integer getMaxConfidenceIntervalNinetyPercent() {
        return maxConfidenceIntervalNinetyPercent;
    }

    public void setMaxConfidenceIntervalNinetyPercent(Integer maxConfidenceIntervalNinetyPercent) {
        this.maxConfidenceIntervalNinetyPercent = maxConfidenceIntervalNinetyPercent;
    }

    public Integer getMinConfidenceIntervalNinetyFivePercent() {
        return minConfidenceIntervalNinetyFivePercent;
    }

    public void setMinConfidenceIntervalNinetyFivePercent(Integer minConfidenceIntervalNinetyFivePercent) {
        this.minConfidenceIntervalNinetyFivePercent = minConfidenceIntervalNinetyFivePercent;
    }

    public Integer getMaxConfidenceIntervalNinetyFivePercent() {
        return maxConfidenceIntervalNinetyFivePercent;
    }

    public void setMaxConfidenceIntervalNinetyFivePercent(Integer maxConfidenceIntervalNinetyFivePercent) {
        this.maxConfidenceIntervalNinetyFivePercent = maxConfidenceIntervalNinetyFivePercent;
    }

    public void loadFromScaleDefinition(ScaleDefinition scaleDefinition) {
        this.ci = scaleDefinition.getCi();
        this.rankPercentage = scaleDefinition.getRankPercentage();
        this.minConfidenceIntervalNinetyPercent = scaleDefinition.getMinConfidenceIntervalNinetyPercent();
        this.maxConfidenceIntervalNinetyPercent = scaleDefinition.getMaxConfidenceIntervalNinetyPercent();
        this.minConfidenceIntervalNinetyFivePercent = scaleDefinition.getMinConfidenceIntervalNinetyFivePercent();
        this.maxConfidenceIntervalNinetyFivePercent = scaleDefinition.getMaxConfidenceIntervalNinetyFivePercent();
    }

    public static List<ScaleDefinition> findByScale(Scale scale) {
        return JPA.query("SELECT s FROM ScaleDefinition s WHERE s.scale = ?1 ORDER BY s.scaleScoreSum", scale);
    }

    public static ScaleDefinition findByScaleAndSum(Scale scale, Integer scaleScoreSum) {
        return JPA.querySingle("SELECT s FROM ScaleDefinition s WHERE s.scale = ?1 AND s.scaleScoreSum = ?2", scale, scaleScoreSum);
    }
}
