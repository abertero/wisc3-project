package cl.wisc3.web.beans.crud;

import java.util.ArrayList;
import java.util.List;

public class CrudEdit {

    private String altKey;
    private CrudType type;
    private List<CrudRow> rows = new ArrayList<>();

    public CrudEdit(String altKey, CrudType type) {
        this.altKey = altKey;
        this.type = type;
    }

    public String getAltKey() {
        return altKey;
    }

    public CrudType getType() {
        return type;
    }

    public List<CrudRow> getRows() {
        return rows;
    }
}
