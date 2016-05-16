package cl.wisc3.web.services;

import cl.wisc3.web.beans.crud.CrudEdit;
import cl.wisc3.web.beans.crud.CrudType;
import cl.wisc3.web.services.crud.CrudEvaluationDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudEditGeneratorService {

    @Autowired
    private CrudEvaluationDefinition crudEvaluationDefinition;

    public CrudEdit getCrudByType(CrudType type, String altKey) {
        switch (type) {
            case EVALUATION_DEFINITION:
                return crudEvaluationDefinition.getCrudEdit(altKey);
            default:
                return new CrudEdit("", null);
        }
    }

    public void save(CrudEdit crud) {
        switch (crud.getType()) {
            case EVALUATION_DEFINITION:
                crudEvaluationDefinition.save(crud);
                break;
        }
    }
}
