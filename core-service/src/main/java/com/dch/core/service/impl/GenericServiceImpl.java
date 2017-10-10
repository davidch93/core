package com.dch.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dch.core.dataaccess.BaseEntity;
import com.dch.core.datastatic.DataStatus;
import com.dch.core.service.GenericService;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic. This class implements
 * {@link GenericService}.
 * 
 * @author david.christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Aug 29, 2016
 * @param <T>
 *            entity.
 * @param <ID>
 *            the primary key for that type.
 */
public abstract class GenericServiceImpl<T extends BaseEntity, ID extends Serializable>
		implements GenericService<T, ID> {

	@Autowired
	private JpaRepository<T, ID> repository;

	@Override
	public T save(T entity) {
		entity.setStatus(DataStatus.ACTIVATED);
		return repository.save(entity);
	}

	@Override
	public T update(T entity) {
		return repository.save(entity);
	}

	@Override
	public boolean exists(ID id) {
		return repository.exists(id);
	}

	@Override
	public T get(ID id) {
		return repository.findOne(id);
	}

	@Override
	public List<T> getAll() {
		return repository.findAll();
	}

	@Override
	public void delete(ID id) {
		repository.delete(id);
	}

	@Override
	public void delete(T entity) {
		repository.delete(entity);
	}

	@Override
	public void remove(ID id) {
		T entity = get(id);
		entity.setStatus(DataStatus.DELETED);
		update(entity);
	}

	@Override
	public void remove(T entity) {
		entity.setStatus(DataStatus.DELETED);
		update(entity);
	}
}
