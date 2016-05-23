package cl.wisc3.web.services;

import cl.wisc3.web.beans.crud.CrudType;
import cl.wisc3.web.beans.crud.CrudView;
import cl.wisc3.web.services.crud.CrudChildInfo;
import cl.wisc3.web.services.crud.CrudChildLevel;
import cl.wisc3.web.services.crud.CrudEvaluationDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrudViewGeneratorService {

    @Autowired
    private CrudEvaluationDefinition crudEvaluationDefinition;
    @Autowired
    private CrudChildLevel crudChildLevel;
    @Autowired
    private CrudChildInfo crudChildInfo;

    public CrudView getCrudByType(CrudType type, String altKey) {
        switch (type) {
            case EVALUATION_DEFINITION:
                return crudEvaluationDefinition.getCrudView(altKey);
            case CHILD_LEVEL:
                return crudChildLevel.getCrudView(altKey);
            case CHILD_INFO:
                return crudChildInfo.getCrudView(altKey);
            default:
                return new CrudView("");
        }
    }

    public List<CrudView> getCrudListByType(CrudType type) {
        switch (type) {
            case EVALUATION_DEFINITION:
                return crudEvaluationDefinition.getList();
            case CHILD_LEVEL:
                return crudChildLevel.getList();
            case CHILD_INFO:
                return crudChildInfo.getList();
            default:
                return new ArrayList<>();
        }
    }
}