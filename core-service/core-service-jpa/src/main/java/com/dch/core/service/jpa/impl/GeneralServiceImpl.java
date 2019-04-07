package com.dch.core.service.jpa.impl;

import com.dch.core.dataaccess.BaseEntity;
import com.dch.core.datastatic.DataStatus;
import com.dch.core.service.jpa.GeneralService;
import com.dch.core.service.jpa.exception.ServiceException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * This class serves as the Base class for all other Services - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * @param <T>  entity.
 * @param <ID> the primary key for that type.
 * @author david.christianto
 * @version 2.0.0
 * @see com.dch.core.service.jpa.GeneralService
 * @since 1.0.0
 */
public abstract class GeneralServiceImpl<T extends BaseEntity, ID extends Serializable>
        implements GeneralService<T, ID> {

    private final JpaRepository<T, ID> repository;

    protected GeneralServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> save(T entity) {
        entity.setStatus(DataStatus.ACTIVATED);
        return Optional.of(repository.save(entity));
    }

    @Override
    public Optional<T> update(T entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public boolean exists(ID id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<T> get(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void remove(ID id) {
        T entity = get(id).orElseThrow(() -> new ServiceException("ID not found!"));
        entity.setStatus(DataStatus.DELETED);
        update(entity);
    }

    @Override
    public void remove(T entity) {
        entity.setStatus(DataStatus.DELETED);
        update(entity);
    }
}
