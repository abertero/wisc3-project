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
    private int scaleScoreSum;
    private int ci;
    private double rankPercentage;
    private int minConfidenceIntervalNinetyPercent;
    private int maxConfidenceIntervalNinetyPercent;
    private int minConfidenceIntervalNinetyFivePercent;
    private int maxConfidenceIntervalNinetyFivePercent;

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public int getScaleScoreSum() {
        return scaleScoreSum;
    }

    public void setScaleScoreSum(int scaleScoreSum) {
        this.scaleScoreSum = scaleScoreSum;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public double getRankPercentage() {
        return rankPercentage;
    }

    public void setRankPercentage(double rankPercentage) {
        this.rankPercentage = rankPercentage;
    }

    public int getMinConfidenceIntervalNinetyPercent() {
        return minConfidenceIntervalNinetyPercent;
    }

    public void setMinConfidenceIntervalNinetyPercent(int minConfidenceIntervalNinetyPercent) {
        this.minConfidenceIntervalNinetyPercent = minConfidenceIntervalNinetyPercent;
    }

    public int getMaxConfidenceIntervalNinetyPercent() {
        return maxConfidenceIntervalNinetyPercent;
    }

    public void setMaxConfidenceIntervalNinetyPercent(int maxConfidenceIntervalNinetyPercent) {
        this.maxConfidenceIntervalNinetyPercent = maxConfidenceIntervalNinetyPercent;
    }

    public int getMinConfidenceIntervalNinetyFivePercent() {
        return minConfidenceIntervalNinetyFivePercent;
    }

    public void setMinConfidenceIntervalNinetyFivePercent(int minConfidenceIntervalNinetyFivePercent) {
        this.minConfidenceIntervalNinetyFivePercent = minConfidenceIntervalNinetyFivePercent;
    }

    public int getMaxConfidenceIntervalNinetyFivePercent() {
        return maxConfidenceIntervalNinetyFivePercent;
    }

    public void setMaxConfidenceIntervalNinetyFivePercent(int maxConfidenceIntervalNinetyFivePercent) {
        this.maxConfidenceIntervalNinetyFivePercent = maxConfidenceIntervalNinetyFivePercent;
    }

    public static List<ScaleDefinition> findByScale(Scale scale) {
        return JPA.query("SELECT s FROM ScaleDefinition s WHERE s.scale = ?1 ORDER BY s.scaleScoreSum", scale);
    }
}
