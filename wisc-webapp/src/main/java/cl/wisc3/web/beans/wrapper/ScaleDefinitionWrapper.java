package cl.wisc3.web.beans.wrapper;


import cl.wisc3.model.definitions.ScaleDefinition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScaleDefinitionWrapper implements Serializable {

    private List<ScaleDefinition> definitions = new ArrayList<>();

    public List<ScaleDefinition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<ScaleDefinition> definitions) {
        this.definitions = definitions;
    }
}
