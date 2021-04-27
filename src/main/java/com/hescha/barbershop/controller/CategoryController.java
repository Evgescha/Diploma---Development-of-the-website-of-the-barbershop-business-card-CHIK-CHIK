package com.hescha.barbershop.controller;

import java.util.List;

import com.hescha.barbershop.entity.Category;
import com.hescha.barbershop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService service;

	@GetMapping
	String get(Model model) {
		List<Category> list = service.repository.findAll();
		model.addAttribute("list", list);
		return "categories";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
		if (id != null) {
			Category entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Category());
		}
		return "category-add-edit";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Category entity) throws Exception {
		service.create(entity);
		return "redirect:/service";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/service";
	}

	
}
