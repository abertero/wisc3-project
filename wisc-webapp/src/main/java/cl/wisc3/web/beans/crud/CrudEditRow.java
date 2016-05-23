package cl.wisc3.web.beans.crud;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CrudEditRow {
    private static final String TEXTAREA_COLS = "cols";
    private static final String TEXTAREA_ROWS = "rows";
    private static final String TEXTAREA_COLS_DEFAULT = "20";
    private static final String TEXTAREA_ROWS_DEFAULT = "3";

    private String name;
    private String displayName;
    private String value;
    private CrudEditType type;
    private Map<String, String> mappedValues = new LinkedHashMap<>();
    private List<String> listValues = new ArrayList<>();

    public CrudEditRow(CrudType type, String name, String value, CrudEditType editType) {
        this.name = name;
        this.value = value;
        this.type = editType;
        this.displayName = String.format("crud.%s.displayName.%s", type.getName(), name);
    }

    public String getName() {
        return name;
    }

    public CrudEditType getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getValue() {
        return value;
    }

    public Map<String, String> getMappedValues() {
        return mappedValues;
    }

    public List<String> getListValues() {
        return listValues;
    }

    public void addMappedValues(Object... pairParams) {
        if (pairParams.length % 2 == 0) {
            for (int i = 1; i < pairParams.length; i += 2) {
                mappedValues.put(pairParams[i - 1].toString(), pairParams[i].toString());
            }
        }
    }

    public void addValues(Object... values) {
        for (Object value : values) {
            listValues.add(value.toString());
        }
    }

    public String getHtml() {
        StringBuilder builder = new StringBuilder();
        switch (type) {
            case READONLY:
                builder.append(String.format("<input type='text' class='form-control' disabled name='values[%s]' id='%s' value='%s'/>", name, name, value));
                break;
            case TEXT:
                builder.append(String.format("<input type='text' class='form-control' name='values[%s]' id='%s' value='%s'/>", name, name, value));
                break;
            case TEXTAREA:
                builder.append(String.format("<textarea class='form-control' name='values[%s]' id='%s' cols='%s' rows='%s'>%s</textarea>",
                        name,
                        name,
                        StringUtils.defaultIfEmpty(mappedValues.get(TEXTAREA_COLS), TEXTAREA_COLS_DEFAULT),
                        StringUtils.defaultIfEmpty(mappedValues.get(TEXTAREA_ROWS), TEXTAREA_ROWS_DEFAULT),
                        value));
                break;
            case SELECT:
                builder.append(String.format("<select class='form-control' name='values[%s]' id='%s'>", name, name));
                for (Map.Entry<String, String> entry : mappedValues.entrySet()) {
                    builder.append(String.format("<option %s value='%s'>%s</option>", entry.getKey().equals(value) ? "selected" : "", entry.getKey(), entry.getValue()));
                }
                for (String listValue : listValues) {
                    builder.append(String.format("<option %s value='%s'>%s</option>", listValue.equals(value) ? "selected" : "", listValue, listValue));
                }
                builder.append("</select>");
                break;
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, new StandardToStringStyle());
    }
}
