package cl.wisc3.web.controllers;

import cl.wisc3.model.child.ChildEvaluation;
import cl.wisc3.model.child.ChildInfo;
import cl.wisc3.web.beans.crud.CrudType;
import cl.wisc3.web.services.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/evaluation/*")
public class EvaluationController {

    @Autowired
    private ChildService childService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listChildren() {
        List<ChildInfo> children = childService.getAllChildren();
        ModelAndView mv = new ModelAndView("child-list");
        mv.addObject("children", children);
        mv.addObject("entity", CrudType.CHILD_INFO.getName());
        return mv;
    }

    @RequestMapping(value = "child/{altKey}", method = RequestMethod.GET)
    public ModelAndView editChildren(@PathVariable String altKey) {
        ChildInfo child = childService.getChildByAltKey(altKey);
        List<ChildEvaluation> evaluations = childService.getEvaluationsByChild(altKey);
        ModelAndView mv = new ModelAndView("child-edit");
        mv.addObject("child", child);
        mv.addObject("evaluations", evaluations);
        mv.addObject("entity", CrudType.CHILD_INFO.getName());
        return mv;
    }

}
