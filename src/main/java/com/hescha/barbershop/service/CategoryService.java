package com.hescha.barbershop.service;


import com.hescha.barbershop.entity.Category;
import com.hescha.barbershop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryService extends CrudImpl<Category> {

	public CategoryRepository repository;

	@Autowired
	public CategoryService(CategoryRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
