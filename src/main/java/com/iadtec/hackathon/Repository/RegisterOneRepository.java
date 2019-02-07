package com.iadtec.hackathon.Repository;

import com.iadtec.hackathon.Entity.RegisterOne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RegisterOneRepository extends PagingAndSortingRepository<RegisterOne, Long> {

    @Override
    Iterable<RegisterOne> findAll(Sort sort);

    @Override
    Page<RegisterOne> findAll(Pageable pageable);

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
