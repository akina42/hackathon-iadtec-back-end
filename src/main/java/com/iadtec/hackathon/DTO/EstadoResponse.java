package com.iadtec.hackathon.DTO;

import com.iadtec.hackathon.Entity.Pais;

public class EstadoResponse {

    private Long id;
    private String nome;
    private String uf;
    private Pais pais;

    public EstadoResponse() {
    }

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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
