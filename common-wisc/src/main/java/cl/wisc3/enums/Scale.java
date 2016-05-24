package cl.wisc3.enums;

public enum Scale {
    VERBAL("verbal", "Verbal", 5, 95),
    VERBAL_UNDERSTANDING("cv", "Comprensión verbal", 5, 95),
    ABSENCE_OF_DISTRACTIBILITY("ad", "Ausencia de distractibilidad", 3, 57),
    EXECUTION("execution", "De ejecución", 5, 95),
    PROCESSING_SPEED("vp", "Velocidad de procesamiento", 2, 38),
    PERCEPTUAL_ORGANIZATION("op", "Organización perceptual", 4, 76),
    TOTAL("total", "Total", 10, 190);

    private String code;
    private String displayName;
    private int minRange;
    private int maxRange;

    Scale(String code, String displayName, int minRange, int maxRange) {
        this.code = code;
        this.displayName = displayName;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public static Scale fromCode(String type) {
        for (Scale scale : values()) {
            if (scale.getCode().equals(type)) {
                return scale;
            }
        }
        return null;
    }
}
