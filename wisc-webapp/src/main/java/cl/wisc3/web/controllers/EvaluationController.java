package cl.wisc3.web.controllers;

import cl.wisc3.enums.EvaluationType;
import cl.wisc3.enums.Scale;
import cl.wisc3.model.child.ChildEvaluation;
import cl.wisc3.model.child.ChildEvaluationScore;
import cl.wisc3.model.child.ChildInfo;
import cl.wisc3.model.definitions.EvaluationDefinition;
import cl.wisc3.web.beans.crud.CrudType;
import cl.wisc3.web.services.ChildService;
import cl.wisc3.web.services.EvaluationDefinitionService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/evaluation/*")
public class EvaluationController {

    @Autowired
    private ChildService childService;
    @Autowired
    private EvaluationDefinitionService evaluationDefinitionService;

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
        if (child != null) {
            List<ChildEvaluation> evaluations = childService.getEvaluationsByChild(altKey);
            ModelAndView mv = new ModelAndView("child-edit");
            mv.addObject("child", child);
            mv.addObject("evaluations", evaluations);
            mv.addObject("entity", CrudType.CHILD_INFO.getName());
            return mv;
        } else {
            return new ModelAndView("redirect:/evaluation/list");
        }
    }

    @RequestMapping(value = "new/{altKey}", method = RequestMethod.GET)
    public ModelAndView newEvaluation(@PathVariable String altKey) {
        ChildInfo child = childService.getChildByAltKey(altKey);
        if (child != null) {
            Calendar calendar = Calendar.getInstance();
            List<EvaluationDefinition> definitions = evaluationDefinitionService.findByEvaluationType(EvaluationType.VERBAL);
            definitions.addAll(evaluationDefinitionService.findByEvaluationType(EvaluationType.EXECUTION));
            ModelAndView mv = new ModelAndView("evaluation-edit");
            mv.addObject("child", child);
            mv.addObject("currentDay", calendar.get(Calendar.DATE));
            mv.addObject("currentMonth", calendar.get(Calendar.MONTH) + 1);
            mv.addObject("currentYear", calendar.get(Calendar.YEAR));
            mv.addObject("definitions", definitions);
            return mv;
        } else {
            return new ModelAndView("redirect:/evaluation/list");
        }
    }

    @RequestMapping(value = "view/{altKey}", method = RequestMethod.GET)
    public ModelAndView viewEvaluation(@PathVariable String altKey) {
        ChildEvaluation evaluation = evaluationDefinitionService.findEvaluationByAltKey(altKey);
        List<ChildEvaluationScore> scores = evaluationDefinitionService.findScoresByEvaluationAltKey(altKey);
        if (evaluation != null) {
            ModelAndView mv = new ModelAndView("evaluation-view");
            mv.addObject("evaluation", evaluation);
            mv.addObject("scores", scores);
            return mv;
        } else {
            return new ModelAndView("redirect:/evaluation/list");
        }
    }

    @RequestMapping(value = "save/{altKey}", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveEvaluation(@PathVariable String altKey, @RequestParam Map<String, String> values) {
        ChildInfo child = childService.getChildByAltKey(altKey);
        if (child != null) {
            Map<String, Integer> valuesAsIntegers = new HashMap<>();
            for (Map.Entry<String, String> entry : values.entrySet()) {
                valuesAsIntegers.put(entry.getKey(), NumberUtils.toInt(entry.getValue()));
            }
            evaluationDefinitionService.saveEvaluation(child, valuesAsIntegers);
            return new ModelAndView(String.format("redirect:/evaluation/child/%s", altKey));
        } else {
            return new ModelAndView("redirect:/evaluation/list");
        }
    }

    @RequestMapping(value = "scale", method = RequestMethod.GET)
    public ModelAndView selectScale() {
        ModelAndView mv = new ModelAndView("evaluation-scale");
        List<EvaluationDefinition> definitions = evaluationDefinitionService.findByEvaluationType(EvaluationType.VERBAL);
        definitions.addAll(evaluationDefinitionService.findByEvaluationType(EvaluationType.EXECUTION));
        List<Scale> scales = Arrays.asList(Scale.values());
        mv.addObject("scales", scales);
        mv.addObject("definitions", definitions);
        Map<String, Map<String, Boolean>> values = evaluationDefinitionService.getScalesAsMap();
        mv.addObject("values", values);
        return mv;
    }

    @RequestMapping(value = "scale/save", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveScale(@RequestParam List<String> scales) {
        evaluationDefinitionService.saveScales(scales);
        return new ModelAndView("redirect:/evaluation/scale");
    }
}
