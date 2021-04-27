package com.hescha.barbershop.service;

public interface CrudService<Entity> {

	boolean create(Entity entity) throws Exception;

	Entity read(long id);

	boolean update(Entity entity) throws Exception;

	boolean delete(long id) throws Exception;
}