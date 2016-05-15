package cl.wisc3.web.services;

import cl.wisc3.model.definitions.EvaluationDefinition;
import cl.wisc3.web.beans.crud.CrudType;
import cl.wisc3.web.beans.crud.CrudView;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrudViewGeneratorService {

    public CrudView getCrudByType(CrudType type, String altKey) {
        switch (type) {
            case EVALUATION_DEFINITION:
                return getEvaluationDefinitionCrud(altKey);
            default:
                return new CrudView("");
        }
    }

    public List<CrudView> getCrudListByType(CrudType type) {
        switch (type) {
            case EVALUATION_DEFINITION:
                return getEvaluationDefinitionCrudList();
            default:
                return new ArrayList<>();
        }
    }

    public CrudView getEvaluationDefinitionCrud(String altKey) {
        if (StringUtils.isNotBlank(altKey)) {
            return getEvaluationDefinitionCrud(EvaluationDefinition.findByAltKey(altKey));
        }
        return getEvaluationDefinitionCrud(new EvaluationDefinition());
    }

    public List<CrudView> getEvaluationDefinitionCrudList() {
        List<CrudView> cruds = new ArrayList<>();
        for (EvaluationDefinition evaluationDefinition : EvaluationDefinition.findAll()) {
            cruds.add(getEvaluationDefinitionCrud(evaluationDefinition));
        }
        return cruds;
    }

    public CrudView getEvaluationDefinitionCrud(EvaluationDefinition definition) {
        CrudView crudView = new CrudView(definition.getAltKey());
        crudView.getAttributeValue().put("Id", definition.getId() != null ? String.valueOf(definition.getId()) : "");
        crudView.getAttributeValue().put("AltKey", definition.getAltKey());
        crudView.getAttributeValue().put("Nombre", definition.getName());
        crudView.getAttributeValue().put("Descripción", definition.getDescription());
        crudView.getAttributeValue().put("Rango máximo", String.valueOf(definition.getMaxRange()));
        crudView.getAttributeValue().put("Tipo", definition.getType() != null ? definition.getType().name() : "");
        return crudView;
    }
}