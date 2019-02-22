package com.iadtec.hackathon.Entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "PAIS")
public class Pais {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", length = 150, nullable = false)
    private String nome;

    public Pais() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
