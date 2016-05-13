package cl.wisc3.web.beans.crud;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudRow {
    private static final String TEXTAREA_COLS = "cols";
    private static final String TEXTAREA_ROWS = "rows";
    private static final String TEXTAREA_COLS_DEFAULT = "3";
    private static final String TEXTAREA_ROWS_DEFAULT = "20";

    private String name;
    private String value;
    private CrudType type;
    private Map<String, String> mappedValues = new HashMap<>();
    private List<String> listValues = new ArrayList<>();

    public CrudRow(String name, String value, CrudType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public CrudRow(String name, String value, CrudType type, Map<String, String> mappedValues) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.mappedValues = mappedValues;
    }

    public CrudRow(String name, String value, CrudType type, List<String> listValues) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.listValues = listValues;
    }

    public String getName() {
        return name;
    }

    public CrudType getType() {
        return type;
    }

    public Map<String, String> getMappedValues() {
        return mappedValues;
    }

    public List<String> getListValues() {
        return listValues;
    }

    public void addMappedValues(String... pairParams) {
        if (pairParams.length % 2 == 0) {
            for (int i = 1; i < pairParams.length; i += 2) {
                mappedValues.put(pairParams[i - 1], pairParams[i]);
            }
        }
    }

    public void addValues(String... values) {
        for (String value : values) {
            listValues.add(value);
        }
    }

    public String getHtml() {
        StringBuilder builder = new StringBuilder();
        switch (type) {
            case TEXT:
                builder.append(String.format("<input type='text' name='%s' id='%s' value='%s'/>", name, name, value));
                break;
            case TEXTAREA:
                builder.append(String.format("<textarea name='%s' id='%s' cols='%s' rows='%d'>%s</textarea>",
                        name,
                        name,
                        StringUtils.defaultIfEmpty(mappedValues.get(TEXTAREA_COLS), TEXTAREA_COLS_DEFAULT),
                        StringUtils.defaultIfEmpty(mappedValues.get(TEXTAREA_ROWS), TEXTAREA_ROWS_DEFAULT),
                        value));
                break;
            case SELECT:
                builder.append(String.format("<select name='%s' id='%s'>", name, name));
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
