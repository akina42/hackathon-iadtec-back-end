package com.iadtec.hackathon.DTO;

import java.io.Serializable;

public class RegisterOneResponseDTO implements Serializable {
    private Long id;

    private String name;

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
