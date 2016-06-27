package cl.wisc3.enums;

public enum Scale {
    VERBAL("verbal", "Verbal", 5, 95, true),
    VERBAL_UNDERSTANDING("cv", "Comprensión verbal", 5, 95, false),
    ABSENCE_OF_DISTRACTIBILITY("ad", "Ausencia de distractibilidad", 3, 57, false),
    EXECUTION("execution", "De ejecución", 5, 95, true),
    PROCESSING_SPEED("vp", "Velocidad de procesamiento", 2, 38, false),
    PERCEPTUAL_ORGANIZATION("op", "Organización perceptual", 4, 76, false),
    TOTAL("total", "Total", 10, 190, true);

    private String code;
    private String displayName;
    private int minRange;
    private int maxRange;
    private boolean checkForComplementary;

    Scale(String code, String displayName, int minRange, int maxRange, boolean checkForComplementary) {
        this.code = code;
        this.displayName = displayName;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.checkForComplementary = checkForComplementary;
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

    public boolean isCheckForComplementary() {
        return checkForComplementary;
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
