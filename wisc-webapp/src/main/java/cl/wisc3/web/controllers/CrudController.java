package cl.wisc3.web.controllers;

import cl.wisc3.web.beans.crud.CrudEdit;
import cl.wisc3.web.beans.crud.CrudType;
import cl.wisc3.web.beans.crud.CrudView;
import cl.wisc3.web.services.CrudEditGeneratorService;
import cl.wisc3.web.services.CrudViewGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/crud/*")
public class CrudController {

    @Autowired
    private CrudEditGeneratorService editGeneratorService;
    @Autowired
    private CrudViewGeneratorService viewGeneratorService;

    @RequestMapping(value = "list/{entity}")
    public ModelAndView list(@PathVariable String entity) {
        CrudType type = CrudType.getByName(entity);
        if (type != null) {
            ModelAndView mv = new ModelAndView("crud-list");
            List<CrudView> cruds = viewGeneratorService.getCrudListByType(type);
            mv.addObject("cruds", cruds);
            mv.addObject("title", type.getTitle());
            mv.addObject("entity", entity);
            return mv;
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping(value = "edit/{entity}", method = RequestMethod.GET)
    public ModelAndView editNew(@PathVariable String entity) {
        CrudType type = CrudType.getByName(entity);
        if (type != null) {
            ModelAndView mv = new ModelAndView("crud-edit");
            CrudEdit crud = editGeneratorService.getCrudByType(type, null);
            mv.addObject("crud", crud);
            mv.addObject("title", type.getTitle());
            mv.addObject("entity", entity);
            return mv;
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping(value = "edit/{entity}/{altKey}")
    public ModelAndView edit(@PathVariable String entity, @PathVariable String altKey) {
        CrudType type = CrudType.getByName(entity);
        if (type != null) {
            ModelAndView mv = new ModelAndView("crud-edit");
            CrudEdit crud = editGeneratorService.getCrudByType(type, altKey);
            mv.addObject("crud", crud);
            mv.addObject("title", type.getTitle());
            mv.addObject("entity", entity);
            return mv;
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping(value = "view/{entity}/{altKey}")
    public ModelAndView view(@PathVariable String entity, @PathVariable String altKey) {
        CrudType type = CrudType.getByName(entity);
        if (type != null) {
            ModelAndView mv = new ModelAndView("crud-view");
            CrudView crud = viewGeneratorService.getCrudByType(type, altKey);
            mv.addObject("crud", crud);
            mv.addObject("title", type.getTitle());
            mv.addObject("entity", entity);
            return mv;
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping(value = "save/{entity}", method = RequestMethod.POST)
    public ModelAndView save(@PathVariable String entity, @ModelAttribute CrudEdit crud) {
        CrudType type = CrudType.getByName(entity);
        if (type != null) {
            editGeneratorService.save(crud);
            return new ModelAndView(String.format("redirect:/crud/list/%s", entity));
        } else {
            return new ModelAndView("redirect:/");
        }
    }
}
