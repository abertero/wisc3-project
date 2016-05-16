package cl.wisc3.web.services.crud;

import cl.wisc3.enums.EvaluationType;
import cl.wisc3.model.definitions.EvaluationDefinition;
import cl.wisc3.web.beans.crud.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrudEvaluationDefinition implements CrudBaseEntity {

    @Override
    public CrudEdit getCrudEdit(String altKey) {
        if (StringUtils.isNotBlank(altKey)) {
            return getCrudEdit(EvaluationDefinition.findByAltKey(altKey));
        }
        return getCrudEdit(new EvaluationDefinition());
    }

    @Override
    public CrudView getCrudView(String altKey) {
        if (StringUtils.isNotBlank(altKey)) {
            return getCrudView(EvaluationDefinition.findByAltKey(altKey));
        }
        return getCrudView(new EvaluationDefinition());

    }

    @Override
    public List<CrudView> getList() {
        List<CrudView> cruds = new ArrayList<>();
        for (EvaluationDefinition evaluationDefinition : EvaluationDefinition.findAll()) {
            cruds.add(getCrudView(evaluationDefinition));
        }
        return cruds;
    }

    private CrudEdit getCrudEdit(EvaluationDefinition definition) {
        CrudEdit crudEdit = new CrudEdit(definition.getAltKey(), CrudType.EVALUATION_DEFINITION);
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

    private CrudView getCrudView(EvaluationDefinition definition) {
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
