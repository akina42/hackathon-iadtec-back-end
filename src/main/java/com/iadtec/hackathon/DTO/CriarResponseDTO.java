package com.iadtec.hackathon.DTO;

import javax.validation.constraints.NotBlank;

public class CriarResponseDTO {

    @NotBlank(message = "Nome é obrigatório ")
    private String nome;

    public CriarResponseDTO() { }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
