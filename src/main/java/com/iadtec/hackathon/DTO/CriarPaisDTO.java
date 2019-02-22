package com.iadtec.hackathon.DTO;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CriarPaisDTO {

    @NotBlank(message = "Nome é obrigatório ")
    private String nome;

    public CriarPaisDTO() { }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
