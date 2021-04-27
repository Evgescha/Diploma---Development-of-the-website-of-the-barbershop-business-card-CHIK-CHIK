package com.hescha.barbershop.controller;

import com.hescha.barbershop.entity.News;
import com.hescha.barbershop.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService service;

    @GetMapping
    String get(Model model) {
        List<News> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "news-list";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {
        if (id != null) {
            News entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            News news = new News();
            news.setDates(new Date(System.currentTimeMillis()));
            model.addAttribute("entity", news);
        }
        return "news-add-edit";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(News entity) throws Exception {
        service.create(entity);
        return "redirect:/news";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/news";
    }

    @RequestMapping("/get/{id}")
    public String getView(Model model, @PathVariable(name = "id", required =
            true) Long id) {
        News entity = service.read(id);
        model.addAttribute("entity", entity);
        return "news-item-view";
    }

}
