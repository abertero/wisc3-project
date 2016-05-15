package cl.wisc3.web.beans.crud;

import java.util.HashMap;
import java.util.Map;

public class CrudView {
    private String altKey;
    private Map<String, String> attributeValue = new HashMap<>();

    public CrudView(String altKey) {
        this.altKey = altKey;
    }

    public String getAltKey() {
        return altKey;
    }

    public Map<String, String> getAttributeValue() {
        return attributeValue;
    }
}
