package com.fr.adaming.Service;

import java.util.List;

public interface IService<T> {
	public T create(T activity);

	public T update(T activity);

	public T readById(Long id);

	public List<T> readAll();

	public void deleteById(Long id);
}
