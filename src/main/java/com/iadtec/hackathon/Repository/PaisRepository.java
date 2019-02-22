package com.iadtec.hackathon.Repository;

import com.iadtec.hackathon.Entity.Estado;
import com.iadtec.hackathon.Entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> { }
