package cl.wisc3.web.services.crud;

import cl.wisc3.enums.EvaluationType;
import cl.wisc3.model.definitions.EvaluationDefinition;
import cl.wisc3.web.beans.crud.*;
import org.apache.commons.lang.BooleanUtils;
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
        definition.setEntityOrder(NumberUtils.toLong(crudEdit.getValue("entityOrder"), 1000));
        definition.setName(crudEdit.getValue("name"));
        definition.setDescription(crudEdit.getValue("description"));
        definition.setType(EvaluationType.getByName(crudEdit.getValue("type")));
        definition.setMaxRange(NumberUtils.toInt(crudEdit.getValue("maxRange")));
        definition.setAlternativeMaxRange(NumberUtils.toInt(crudEdit.getValue("alternativeMaxRange")));
        definition.setComplementary(CrudEditRow.TRUE_STRING.equals(crudEdit.getValue("complementary")));
        if (StringUtils.isNotBlank(crudEdit.getValue("complementOf"))) {
            definition.setComplementOf(EvaluationDefinition.findByAltKey(crudEdit.getValue("complementOf")));
        } else {
            definition.setComplementOf(null);
        }
        definition.save();
    }

    private CrudEdit getCrudEdit(EvaluationDefinition definition) {
        CrudType type = CrudType.EVALUATION_DEFINITION;
        CrudEdit crudEdit = new CrudEdit(definition.getAltKey(), type);
        crudEdit.addRow(new CrudEditRow(type, "id", definition.getId() != null ? String.valueOf(definition.getId()) : "", CrudEditType.READONLY));
        crudEdit.addRow(new CrudEditRow(type, "altKey", definition.getAltKey(), CrudEditType.READONLY));
        crudEdit.addRow(new CrudEditRow(type, "entityOrder", definition.getEntityOrder() != null ? String.valueOf(definition.getEntityOrder()) : "", CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "name", definition.getName(), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "description", definition.getDescription(), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "maxRange", String.valueOf(definition.getMaxRange()), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "alternativeMaxRange", String.valueOf(definition.getAlternativeMaxRange()), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "complementary", String.valueOf(definition.isComplementary()), CrudEditType.CHECKBOX));
        CrudEditRow typeSelectorRow = new CrudEditRow(type, "type", definition.getType() != null ? definition.getType().name() : "", CrudEditType.SELECT);
        typeSelectorRow.addValues(EvaluationType.values());
        crudEdit.addRow(typeSelectorRow);
        CrudEditRow complementOfRow = new CrudEditRow(type, "complementOf", definition.getComplementOf() != null ? definition.getComplementOf().getAltKey() : "", CrudEditType.SELECT);
        complementOfRow.getMappedValues().put("", "Seleccione");
        for (EvaluationDefinition evaluationDefinition : EvaluationDefinition.findAll()) {
            complementOfRow.getMappedValues().put(evaluationDefinition.getAltKey(), evaluationDefinition.getName());
        }
        crudEdit.addRow(complementOfRow);
        return crudEdit;
    }

    private CrudView getCrudView(EvaluationDefinition definition) {
        CrudView crudView = new CrudView(definition.getAltKey());
        crudView.getAttributeValue().put("crud.evaluacion.displayName.id", definition.getId() != null ? String.valueOf(definition.getId()) : "");
        crudView.getAttributeValue().put("crud.evaluacion.displayName.altKey", definition.getAltKey());
        crudView.getAttributeValue().put("crud.evaluacion.displayName.entityOrder", definition.getEntityOrder() != null ? String.valueOf(definition.getEntityOrder()) : "");
        crudView.getAttributeValue().put("crud.evaluacion.displayName.name", definition.getName());
        crudView.getAttributeValue().put("crud.evaluacion.displayName.description", definition.getDescription());
        crudView.getAttributeValue().put("crud.evaluacion.displayName.maxRange", String.valueOf(definition.getMaxRange()));
        crudView.getAttributeValue().put("crud.evaluacion.displayName.alternativeMaxRange", String.valueOf(definition.getAlternativeMaxRange()));
        crudView.getAttributeValue().put("crud.evaluacion.displayName.type", definition.getType() != null ? definition.getType().name() : "");
        crudView.getAttributeValue().put("crud.evaluacion.displayName.complementary", String.valueOf(definition.isComplementary()));
        crudView.getAttributeValue().put("crud.evaluacion.displayName.complementOf", definition.getComplementOf() != null ? definition.getComplementOf().getName() : "");
        return crudView;
    }
}
