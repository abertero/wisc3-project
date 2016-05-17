package cl.wisc3.web.controllers;

import cl.wisc3.beans.AgeDetails;
import cl.wisc3.web.beans.AgeCalculatorHelper;
import cl.wisc3.web.services.AgeCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller
@RequestMapping("/utils/*")
public class UtilsController {

    @Autowired
    private AgeCalculatorService ageCalculatorService;

    @RequestMapping(value = "ageCalculator", method = RequestMethod.GET)
    public ModelAndView ageCalculator() {
        ModelAndView mv = new ModelAndView("age-calculator");
        Calendar calendar = Calendar.getInstance();
        AgeCalculatorHelper helper = new AgeCalculatorHelper();
        helper.setTestDay(calendar.get(Calendar.DATE));
        helper.setTestMonth(calendar.get(Calendar.MONTH) + 1);
        helper.setTestYear(calendar.get(Calendar.YEAR));
        mv.addObject("helper", helper);
        return mv;
    }

    @RequestMapping(value = "calculateAge", method = RequestMethod.POST)
    public
    @ResponseBody
    AgeDetails calculateAge(@ModelAttribute(value = "helper") AgeCalculatorHelper helper) {
        if (helper.hasErrors()) {
            return null;
        }
        return ageCalculatorService.calculateAge(helper.getTestDateDetail(), helper.getBirthDateDetail());
    }
}
