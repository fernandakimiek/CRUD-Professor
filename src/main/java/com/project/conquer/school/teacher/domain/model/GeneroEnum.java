package com.project.conquer.school.teacher.domain.model;

public enum GeneroEnum {

    F("FEMININO"), M("MASCULINO"), O("OUTRO");

    private String tipo;

    GeneroEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
