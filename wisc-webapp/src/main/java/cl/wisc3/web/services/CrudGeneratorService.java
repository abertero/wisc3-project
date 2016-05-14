package cl.wisc3.web.services;

import cl.wisc3.enums.EvaluationType;
import cl.wisc3.model.definitions.EvaluationDefinition;
import cl.wisc3.web.beans.crud.Crud;
import cl.wisc3.web.beans.crud.CrudRow;
import cl.wisc3.web.beans.crud.CrudType;
import org.springframework.stereotype.Service;

@Service
public class CrudGeneratorService {

    public Crud getEvaluationDefinitionCrud() {
        return getEvaluationDefinitionCrud(new EvaluationDefinition());
    }

    public Crud getEvaluationDefinitionCrud(EvaluationDefinition definition) {
        Crud crud = new Crud();
        crud.getRows().add(new CrudRow("id", String.valueOf(definition.getId()), CrudType.TEXT));
        crud.getRows().add(new CrudRow("altKey", definition.getAltKey(), CrudType.TEXT));
        crud.getRows().add(new CrudRow("name", definition.getName(), CrudType.TEXT));
        crud.getRows().add(new CrudRow("description", definition.getDescription(), CrudType.TEXT));
        crud.getRows().add(new CrudRow("maxRange", String.valueOf(definition.getMaxRange()), CrudType.TEXT));
        CrudRow typeSelectorRow = new CrudRow("type", definition.getType().name(), CrudType.SELECT);
        typeSelectorRow.addMappedValues(EvaluationType.values());
        crud.getRows().add(typeSelectorRow);
        return crud;
    }
}
