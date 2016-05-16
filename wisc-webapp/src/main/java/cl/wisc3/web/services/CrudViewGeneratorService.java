package cl.wisc3.web.services;

import cl.wisc3.web.beans.crud.CrudType;
import cl.wisc3.web.beans.crud.CrudView;
import cl.wisc3.web.services.crud.CrudEvaluationDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrudViewGeneratorService {

    @Autowired
    private CrudEvaluationDefinition crudEvaluationDefinition;

    public CrudView getCrudByType(CrudType type, String altKey) {
        switch (type) {
            case EVALUATION_DEFINITION:
                return crudEvaluationDefinition.getCrudView(altKey);
            default:
                return new CrudView("");
        }
    }

    public List<CrudView> getCrudListByType(CrudType type) {
        switch (type) {
            case EVALUATION_DEFINITION:
                return crudEvaluationDefinition.getList();
            default:
                return new ArrayList<>();
        }
    }
}