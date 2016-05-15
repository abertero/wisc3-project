package cl.wisc3.web.beans.crud;

public enum CrudType {
    EVALUATION_DEFINITION("evaluacion");

    private String name;

    CrudType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CrudType getByName(String name) {
        for (CrudType type : values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }
}
