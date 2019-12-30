package com.dch.core.service.cassandra.impl;

import com.dch.core.service.cassandra.GenericReactiveService;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * This class serves as the Base class for all other Services - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * @param <T>  entity.
 * @param <ID> the primary key for that type.
 * @author david.christianto
 * @version 2.0.0
 * @see GenericReactiveService
 */
public class GenericReactiveServiceImpl<T, ID extends Serializable> implements GenericReactiveService<T, ID> {

    private final ReactiveCassandraRepository<T, ID> cassandraRepository;

    public GenericReactiveServiceImpl(ReactiveCassandraRepository<T, ID> cassandraRepository) {
        this.cassandraRepository = cassandraRepository;
    }

    @Override
    public Mono<T> save(T entity) {
        return cassandraRepository.save(entity);
    }

    @Override
    public Mono<T> update(T entity) {
        return cassandraRepository.save(entity);
    }

    @Override
    public Mono<Boolean> exists(ID id) {
        return cassandraRepository.existsById(id);
    }

    @Override
    public Mono<T> get(ID id) {
        return cassandraRepository.findById(id);
    }

    @Override
    public Flux<T> getAll() {
        return cassandraRepository.findAll();
    }

    @Override
    public Mono<Void> delete(ID id) {
        return cassandraRepository.deleteById(id);
    }

    @Override
    public Mono<Void> delete(T entity) {
        return cassandraRepository.delete(entity);
    }
}
