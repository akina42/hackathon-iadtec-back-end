package com.iadtec.hackathon.DTO;

import com.iadtec.hackathon.Entity.Pais;

import javax.validation.constraints.NotNull;

public class UpdateEstado {

    @NotNull(message = "ID deve ser informado ")
    private Long id;

    @NotNull(message = "Nome deve ser informado ")
    private String nome;

    @NotNull(message = "UF deve ser informado ")
    private String uf;

    @NotNull(message = "ID do Pais deve ser informado ")
    private Long IdPais;

    public UpdateEstado() {
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

    public Long getIdPais() {
        return IdPais;
    }

    public void setIdPais(Long idPais) {
        IdPais = idPais;
    }
}
