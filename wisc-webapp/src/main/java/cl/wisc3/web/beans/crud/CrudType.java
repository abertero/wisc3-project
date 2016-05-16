package cl.wisc3.web.beans.crud;

public enum CrudType {
    EVALUATION_DEFINITION("evaluacion", "crud.evaluacion.title");

    private String name;
    private String title;

    CrudType(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
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
