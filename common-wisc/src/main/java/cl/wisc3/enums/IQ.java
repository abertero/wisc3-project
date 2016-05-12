package cl.wisc3.enums;

public enum IQ {
    INTELLECTUALLY_DEFICIENT(0, 69, "Intelectualmente deficiente"),
    BOUNDARY(70, 79, "Limítrofe"),
    LOW(80, 89, "Media baja"),
    AVERAGE(90, 109, "Promedio"),
    HIGH(110, 119, "Media alta"),
    SUPERIOR(120, 129, "Superior"),
    VERY_SUPERIOR(130, Integer.MAX_VALUE, "Muy superior");

    private int minRange;
    private int maxRange;
    private String label;

    IQ(int minRange, int maxRange, String label) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.label = label;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public String getLabel() {
        return label;
    }

    public static IQ qualifyIQ(int value) {
        if (value <= INTELLECTUALLY_DEFICIENT.maxRange) {
            return INTELLECTUALLY_DEFICIENT;
        } else if (value <= BOUNDARY.maxRange) {
            return BOUNDARY;
        } else if (value <= LOW.maxRange) {
            return LOW;
        } else if (value <= AVERAGE.maxRange) {
            return AVERAGE;
        } else if (value <= HIGH.maxRange) {
            return HIGH;
        } else if (value <= SUPERIOR.maxRange) {
            return SUPERIOR;
        } else {
            return VERY_SUPERIOR;
        }
    }
}
