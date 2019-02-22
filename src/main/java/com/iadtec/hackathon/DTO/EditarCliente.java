package com.iadtec.hackathon.DTO;

import com.iadtec.hackathon.Entity.EnumSituacao;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class EditarCliente {

    @NotNull(message = "ID deve ser informado ")
    private Long id;

    @NotBlank(message = "Nome é obrigatório ")
    private String nome;

    @CPF(message = "CPF informado é invalido ")
    private String cpf;

    @NotNull(message = "Data de nascimento obrigatória ")
    private Date dataNascimento;

    @NotBlank(message = "Email é obrigatório ")
    @Email(message = "Email não é válido ")
    private String email;

    @NotNull(message = "Situação é obrigatória ")
    private EnumSituacao situacao;

    public EditarCliente() { }

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
}
