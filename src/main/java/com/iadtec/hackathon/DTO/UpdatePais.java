package com.iadtec.hackathon.DTO;

import javax.validation.constraints.NotNull;

public class UpdatePais {

    @NotNull(message = "ID deve ser informado ")
    private Long id;

    @NotNull(message = "Nome deve ser informado ")
    private String nome;

    public UpdatePais() {
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
}
