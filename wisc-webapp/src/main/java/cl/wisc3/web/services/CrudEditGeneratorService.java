package cl.wisc3.web.services;

import cl.wisc3.enums.EvaluationType;
import cl.wisc3.model.definitions.EvaluationDefinition;
import cl.wisc3.web.beans.crud.CrudEdit;
import cl.wisc3.web.beans.crud.CrudEditType;
import cl.wisc3.web.beans.crud.CrudRow;
import cl.wisc3.web.beans.crud.CrudType;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CrudEditGeneratorService {

    public CrudEdit getCrudByType(CrudType type, String altKey) {
        switch (type) {
            case EVALUATION_DEFINITION:
                return getEvaluationDefinitionCrud(altKey);
            default:
                return new CrudEdit();
        }
    }

    public CrudEdit getEvaluationDefinitionCrud(String altKey) {
        if (StringUtils.isNotBlank(altKey)) {
            return getEvaluationDefinitionCrud(EvaluationDefinition.findByAltKey(altKey));
        }
        return getEvaluationDefinitionCrud(new EvaluationDefinition());
    }

    public CrudEdit getEvaluationDefinitionCrud(EvaluationDefinition definition) {
        CrudEdit crudEdit = new CrudEdit();
        crudEdit.getRows().add(new CrudRow("id", definition.getId() != null ? String.valueOf(definition.getId()) : "", CrudEditType.TEXT));
        crudEdit.getRows().add(new CrudRow("altKey", definition.getAltKey(), CrudEditType.TEXT));
        crudEdit.getRows().add(new CrudRow("name", definition.getName(), CrudEditType.TEXT));
        crudEdit.getRows().add(new CrudRow("description", definition.getDescription(), CrudEditType.TEXT));
        crudEdit.getRows().add(new CrudRow("maxRange", String.valueOf(definition.getMaxRange()), CrudEditType.TEXT));
        CrudRow typeSelectorRow = new CrudRow("type", definition.getType() != null ? definition.getType().name() : "", CrudEditType.SELECT);
        typeSelectorRow.addValues(EvaluationType.values());
        crudEdit.getRows().add(typeSelectorRow);
        return crudEdit;
    }
}
