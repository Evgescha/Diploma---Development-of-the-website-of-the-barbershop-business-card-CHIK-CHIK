package com.hescha.barbershop.controller;

import com.hescha.barbershop.entity.Category;
import com.hescha.barbershop.entity.MService;
import com.hescha.barbershop.service.CategoryService;
import com.hescha.barbershop.service.MServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/service")
public class MServiceController {

    @Autowired
    MServiceService service;

    @Autowired
    CategoryService serviceCategory;

    @GetMapping
    String get(Model model) {
        model.addAttribute("services", service.repository.findAll());
        model.addAttribute("categories", serviceCategory.repository.findAll());
        return "service-list";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {
        model.addAttribute("categories", serviceCategory.repository.findAll());
        if (id != null) {
            MService entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new MService());
        }
        return "service-add-edit";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(MService entity, @Param("catId") Long catId) throws Exception {
        entity.setCategory(serviceCategory.read(catId));
        service.create(entity);
        return "redirect:/service";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/service";
    }

    @RequestMapping("/get/{id}")
    public String getByCategory(Model model, @PathVariable(name = "id", required =
            true) Long id) {
        model.addAttribute("categories", serviceCategory.repository.findAll());
        if (id != null) {
            MService entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new MService());
        }
        Category category = serviceCategory.read(id);

        model.addAttribute("categories", serviceCategory.repository.findAll());
        model.addAttribute("services", category.getServices());
        model.addAttribute("current", category.getName());
        return "service-list";
    }
}
