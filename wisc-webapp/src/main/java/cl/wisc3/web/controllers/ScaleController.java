package cl.wisc3.web.controllers;

import cl.wisc3.enums.Scale;
import cl.wisc3.model.definitions.ScaleDefinition;
import cl.wisc3.web.services.ScaleDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/scale/*")
public class ScaleController {

    @Autowired
    private ScaleDefinitionService scaleDefinitionService;

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public ModelAndView selectType() {
        ModelAndView mv = new ModelAndView("scale-definition-select-scale");
        mv.addObject("scales", Arrays.asList(Scale.values()));
        return mv;
    }

    @RequestMapping(value = "edit/{scaleStr}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String scaleStr) {
        Scale scale = Scale.fromCode(scaleStr);
        if (scale == null) {
            return new ModelAndView("redirect:/scale/type");
        }
        ModelAndView mv = new ModelAndView("scale-definition-edit");
        Map<Integer, ScaleDefinition> definitions = scaleDefinitionService.getFromScale(scale);
        mv.addObject("scale", scale);
        mv.addObject("definitions", definitions);
        return mv;
    }
}
