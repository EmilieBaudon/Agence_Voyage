package com.fr.adaming.restController;

import java.util.List;

public interface IController<T> {

	public String createObject(T obj);

	public String updateObject(T obj);

	public T readById(Long id);

	public List<T> readAll();

	public String delete(Long id);
}