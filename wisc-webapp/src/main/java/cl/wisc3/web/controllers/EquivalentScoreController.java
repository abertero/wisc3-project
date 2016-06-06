package cl.wisc3.web.controllers;

import cl.wisc3.enums.EvaluationType;
import cl.wisc3.model.child.ChildLevel;
import cl.wisc3.model.definitions.EquivalentScoreDefinition;
import cl.wisc3.web.services.ChildService;
import cl.wisc3.web.services.EquivalentScoreDefinitionService;
import cl.wisc3.web.services.EvaluationDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/definition/score/*")
public class EquivalentScoreController {
    @Autowired
    private ChildService childService;
    @Autowired
    private EvaluationDefinitionService evaluationDefinitionService;
    @Autowired
    private EquivalentScoreDefinitionService equivalentScoreDefinitionService;

    @RequestMapping(value = "equivalent/level", method = RequestMethod.GET)
    public ModelAndView selectLevel() {
        ModelAndView mv = new ModelAndView("equivalent-score-select-level");
        mv.addObject("levels", childService.getAllLevels());
        return mv;
    }

    @RequestMapping(value = "equivalent/edit/{childLevelAltKey}", method = RequestMethod.GET)
    public ModelAndView editEquivalentScore(@PathVariable String childLevelAltKey) {
        ModelAndView mv = new ModelAndView("equivalent-score-edit");
        ChildLevel level = childService.getLevelByAltKey(childLevelAltKey);
        if (level != null) {
            mv.addObject("childLevel", level);
            mv.addObject("tableColumnsByDefinition", equivalentScoreDefinitionService.getTableColumnsMapWithDefinitionKeyFromChildLevelKey(childLevelAltKey));
            mv.addObject("executionDefinitions", evaluationDefinitionService.findByEvaluationType(EvaluationType.EXECUTION));
            mv.addObject("verbalDefinitions", evaluationDefinitionService.findByEvaluationType(EvaluationType.VERBAL));
            mv.addObject("maxEquivalentScore", EquivalentScoreDefinition.MAX_EQUIVALENT_SCORE);
            return mv;
        } else {
            return new ModelAndView("redirect:/definition/score/equivalent/level");
        }
    }

    @RequestMapping(value = "equivalent/view/{childLevelAltKey}", method = RequestMethod.GET)
    public ModelAndView viewEquivalentScore(@PathVariable String childLevelAltKey) {
        ModelAndView mv = new ModelAndView("equivalent-score-view");
        ChildLevel level = childService.getLevelByAltKey(childLevelAltKey);
        if (level != null) {
            mv.addObject("childLevel", level);
            mv.addObject("tableColumnsByDefinition", equivalentScoreDefinitionService.getTableColumnsMapWithDefinitionKeyFromChildLevelKey(childLevelAltKey));
            mv.addObject("executionDefinitions", evaluationDefinitionService.findByEvaluationType(EvaluationType.EXECUTION));
            mv.addObject("verbalDefinitions", evaluationDefinitionService.findByEvaluationType(EvaluationType.VERBAL));
            mv.addObject("maxEquivalentScore", EquivalentScoreDefinition.MAX_EQUIVALENT_SCORE);
            return mv;
        } else {
            return new ModelAndView("redirect:/definition/score/equivalent/level");
        }
    }


    @RequestMapping(value = "equivalent/save/{childLevelAltKey}", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveEquivalentScore(@PathVariable String childLevelAltKey, @RequestParam Map<String, String> values) {
        ChildLevel childLevel = childService.getLevelByAltKey(childLevelAltKey);
        if (childLevel != null) {
            equivalentScoreDefinitionService.saveScore(childLevel, values);
            return new ModelAndView(String.format("redirect:/definition/score/equivalent/view/%s", childLevelAltKey));
        } else {
            return new ModelAndView("redirect:/definition/score/equivalent/level");
        }
    }
}
