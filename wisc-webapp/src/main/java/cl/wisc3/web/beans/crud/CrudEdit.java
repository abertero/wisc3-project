package cl.wisc3.web.beans.crud;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudEdit {

    private String altKey;
    private CrudType type;
    private Map<String, CrudEditRow> rows = new HashMap<>();
    private Map<String, String> values = new HashMap<>();

    public CrudEdit() {
    }

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

    public List<CrudEditRow> getRows() {
        return new ArrayList<>(rows.values());
    }

    public String getValue(String key) {
        return values.get(key);
    }

    public CrudEditRow getRowByKey(String key) {
        return rows.get(key);
    }

    public void addRow(CrudEditRow row) {
        rows.put(row.getName(), row);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, new StandardToStringStyle());
    }
}
