package cl.wisc3.web.services;

import cl.wisc3.model.child.ChildEvaluation;
import cl.wisc3.model.child.ChildInfo;
import cl.wisc3.model.child.ChildLevel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChildService {

    public List<ChildInfo> getAllChildren() {
        return ChildInfo.findAll();
    }

    public ChildInfo getChildByAltKey(String altKey) {
        return ChildInfo.findByAltKey(altKey);
    }

    public List<ChildLevel> getAllLevels() {
        return ChildLevel.findAll();
    }

    public ChildLevel getLevelByAltKey(String altKey) {
        return ChildLevel.findByAltKey(altKey);
    }

    public List<ChildEvaluation> getEvaluationsByChild(String altKey) {
        return ChildEvaluation.getByChild(altKey);
    }
}
