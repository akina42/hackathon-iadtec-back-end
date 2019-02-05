package com.iadtec.hackathon.Entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Component
@Table(name = "REGISTER_ONE")
public class RegisterOne implements Serializable {

    public RegisterOne() {
    }

    public RegisterOne(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
