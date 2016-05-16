package cl.wisc3.enums;

public enum EvaluationType {
    EXECUTION, VERBAL;

    public static EvaluationType getByName(String typeStr) {
        for (EvaluationType type : values()) {
            if (type.name().equals(typeStr)) {
                return type;
            }
        }
        return null;
    }
}
