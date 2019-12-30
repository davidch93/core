package com.dch.core.service.cassandra.impl;

import com.dch.core.service.cassandra.GenericService;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * This class serves as the Base class for all other Services - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * @param <T>  the type of entity.
 * @param <ID> the type of primary key.
 * @author david.christianto
 * @version 2.0.0
 * @see com.dch.core.service.cassandra.GenericService
 */
public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    private final CassandraRepository<T, ID> cassandraRepository;

    public GenericServiceImpl(CassandraRepository<T, ID> cassandraRepository) {
        this.cassandraRepository = cassandraRepository;
    }

    @Override
    public Optional<T> save(T entity) {
        return Optional.of(cassandraRepository.save(entity));
    }

    @Override
    public Optional<T> update(T entity) {
        return Optional.of(cassandraRepository.save(entity));
    }

    @Override
    public boolean exists(ID id) {
        return cassandraRepository.existsById(id);
    }

    @Override
    public Optional<T> get(ID id) {
        return cassandraRepository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return cassandraRepository.findAll();
    }

    @Override
    public void delete(ID id) {
        cassandraRepository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        cassandraRepository.delete(entity);
    }
}
