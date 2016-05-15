package cl.wisc3.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/crud/*")
public class CrudController {

    @RequestMapping(value = "list/{entity}")
    public ModelAndView list(@PathVariable String entity) {
        ModelAndView mv = new ModelAndView("crud-list");
        return mv;
    }

    @RequestMapping(value = "edit/{entity}")
    public ModelAndView editNew(@PathVariable String entity) {
        ModelAndView mv = new ModelAndView("crud-edit");
        return mv;
    }

    @RequestMapping(value = "edit/{entity}/{altKey}")
    public ModelAndView edit(@PathVariable String entity, @PathVariable String altKey) {
        ModelAndView mv = new ModelAndView("crud-edit");
        return mv;
    }

    @RequestMapping(value = "view/{entity}/{altKey}")
    public ModelAndView view(@PathVariable String entity, @PathVariable String altKey) {
        ModelAndView mv = new ModelAndView("crud-see");
        return mv;
    }

    @RequestMapping(value = "save/{entity}")
    public ModelAndView save(@PathVariable String entity) {
        return new ModelAndView(String.format("redirect:crud/list/%s", entity));
    }
}
