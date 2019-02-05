package com.iadtec.hackathon.Repository;

import com.iadtec.hackathon.Entity.RegisterOne;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegisterOneRepository extends CrudRepository<RegisterOne, Long> {
    @Override
    <S extends RegisterOne> S save(S s);

    @Override
    <S extends RegisterOne> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<RegisterOne> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<RegisterOne> findAll();

    @Override
    Iterable<RegisterOne> findAllById(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(RegisterOne registerOne);

    @Override
    void deleteAll(Iterable<? extends RegisterOne> iterable);

    @Override
    void deleteAll();
}
