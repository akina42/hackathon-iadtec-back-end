package com.iadtec.hackathon.Repository;

import com.iadtec.hackathon.Entity.Cliente;
import com.iadtec.hackathon.Entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> { }
