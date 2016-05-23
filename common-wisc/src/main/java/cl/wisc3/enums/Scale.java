package cl.wisc3.enums;

public enum Scale {
    VERBAL(EvaluationType.VERBAL, "Verbal"),
    VERBAL_UNDERSTANDING(EvaluationType.VERBAL, "Comprensión verbal"),
    ABSENCE_OF_DISTRACTIBILITY(EvaluationType.VERBAL, "Ausencia de distractibilidad"),
    EXECUTION(EvaluationType.EXECUTION, "De ejecución"),
    PROCESSING_SPEED(EvaluationType.EXECUTION, "Velocidad de procesamiento"),
    PERCEPTUAL_ORGANIZATION(EvaluationType.EXECUTION, "Organización perceptual");


    private EvaluationType type;
    private String displayName;

    Scale(EvaluationType type, String displayName) {
        this.type = type;
        this.displayName = displayName;
    }

    public EvaluationType getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }
}
