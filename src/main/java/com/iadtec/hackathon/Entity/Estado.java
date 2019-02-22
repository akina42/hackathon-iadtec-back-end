package com.iadtec.hackathon.Entity;

import javax.persistence.*;

@Entity
@Table(name = "ESTADO")
public class Estado {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "UF", length = 2, nullable = false)
    private String uf;

    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Pais pais;

    public Estado() { }

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
