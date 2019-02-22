package com.iadtec.hackathon.Entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", length = 150, nullable = false)
    private String nome;

    @Column(name = "CPF", length = 11, nullable = false, unique = true)
    private String cpf;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private Date dataNascimento;

    @Column(name = "EMAIL", length = 50, nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private EnumSituacao situacao;

    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Estado estado;

    public Cliente() { }

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
