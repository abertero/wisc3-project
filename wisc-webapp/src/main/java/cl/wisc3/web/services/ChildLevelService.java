package cl.wisc3.web.services;

import cl.wisc3.model.child.ChildLevel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChildLevelService {
    public List<ChildLevel> getAll() {
        return ChildLevel.findAll();
    }

    public ChildLevel getByAltKey(String altKey) {
        return ChildLevel.findByAltKey(altKey);
    }
}
