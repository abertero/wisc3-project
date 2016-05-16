package cl.wisc3.web.services.crud;

import cl.wisc3.enums.EvaluationType;
import cl.wisc3.model.definitions.EvaluationDefinition;
import cl.wisc3.web.beans.crud.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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

    @Override
    public void save(CrudEdit crudEdit) {
        EvaluationDefinition definition = new EvaluationDefinition();
        if (StringUtils.isNotBlank(crudEdit.getAltKey())) {
            definition = EvaluationDefinition.findByAltKey(crudEdit.getAltKey());
        }
        definition.setName(crudEdit.getValue("name"));
        definition.setDescription(crudEdit.getValue("description"));
        definition.setType(EvaluationType.getByName(crudEdit.getValue("type")));
        definition.setMaxRange(NumberUtils.toInt(crudEdit.getValue("maxRange")));
        definition.save();
    }

    private CrudEdit getCrudEdit(EvaluationDefinition definition) {
        CrudType type = CrudType.EVALUATION_DEFINITION;
        CrudEdit crudEdit = new CrudEdit(definition.getAltKey(), type);
        crudEdit.addRow(new CrudEditRow(type, "id", definition.getId() != null ? String.valueOf(definition.getId()) : "", CrudEditType.READONLY));
        crudEdit.addRow(new CrudEditRow(type, "altKey", definition.getAltKey(), CrudEditType.READONLY));
        crudEdit.addRow(new CrudEditRow(type, "name", definition.getName(), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "description", definition.getDescription(), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "maxRange", String.valueOf(definition.getMaxRange()), CrudEditType.TEXT));
        CrudEditRow typeSelectorRow = new CrudEditRow(type, "type", definition.getType() != null ? definition.getType().name() : "", CrudEditType.SELECT);
        typeSelectorRow.addValues(EvaluationType.values());
        crudEdit.addRow(typeSelectorRow);
        return crudEdit;
    }

    private CrudView getCrudView(EvaluationDefinition definition) {
        CrudView crudView = new CrudView(definition.getAltKey());
        crudView.getAttributeValue().put("crud.evaluacion.displayName.id", definition.getId() != null ? String.valueOf(definition.getId()) : "");
        crudView.getAttributeValue().put("crud.evaluacion.displayName.altKey", definition.getAltKey());
        crudView.getAttributeValue().put("crud.evaluacion.displayName.name", definition.getName());
        crudView.getAttributeValue().put("crud.evaluacion.displayName.description", definition.getDescription());
        crudView.getAttributeValue().put("crud.evaluacion.displayName.maxRange", String.valueOf(definition.getMaxRange()));
        crudView.getAttributeValue().put("crud.evaluacion.displayName.type", definition.getType() != null ? definition.getType().name() : "");
        return crudView;
    }
}
