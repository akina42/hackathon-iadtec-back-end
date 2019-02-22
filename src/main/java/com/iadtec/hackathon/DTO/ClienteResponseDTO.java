package com.iadtec.hackathon.DTO;

import com.iadtec.hackathon.Entity.EnumSituacao;
import com.iadtec.hackathon.Entity.Estado;

import java.io.Serializable;
import java.util.Date;

public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private EnumSituacao situacao;
    private EstadoResponse estado;

    public ClienteResponseDTO() { }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnumSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacao situacao) {
        this.situacao = situacao;
    }

    public EstadoResponse getEstado() {
        return estado;
    }

    public void setEstado(EstadoResponse estado) {
        this.estado = estado;
    }
}
