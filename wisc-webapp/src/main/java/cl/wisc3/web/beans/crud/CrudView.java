package cl.wisc3.web.beans.crud;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class CrudView implements Serializable {
    private String altKey;
    private Map<String, String> attributeValue = new LinkedHashMap<>();

    public CrudView(String altKey) {
        this.altKey = altKey;
    }

    public String getAltKey() {
        return altKey;
    }

    public Map<String, String> getAttributeValue() {
        return attributeValue;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, new StandardToStringStyle());
    }
}
