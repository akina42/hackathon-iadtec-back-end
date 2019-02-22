package com.iadtec.hackathon.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CriarEstadoDTO {

    @NotBlank(message = "Nome é obrigatório ")
    private String nome;
    @NotBlank(message = "Uf é obrigatório ")
    private String uf;
    @NotNull(message = "Id do Pais é obrigatório ")
    private Long pais;

    public CriarEstadoDTO() {
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

    public Long getPais() {
        return pais;
    }

    public void setPais(Long pais) {
        this.pais = pais;
    }
}
