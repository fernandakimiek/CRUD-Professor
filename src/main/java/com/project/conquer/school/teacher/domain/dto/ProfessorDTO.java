package com.project.conquer.school.teacher.domain.dto;

import com.project.conquer.school.teacher.domain.model.GeneroEnum;
import com.project.conquer.school.teacher.domain.model.Professor;

import java.time.LocalDate;

public class ProfessorDTO {

    private Long id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private String genero;

    private String email;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public String getEmail() {
        return email;
    }

    public ProfessorDTO(Long id, String nome, String cpf, LocalDate dataNascimento, String genero, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.email = email;
    }

    public Professor toObject() {
        return new Professor(id, nome, cpf, dataNascimento, GeneroEnum.valueOf(genero), email);
    }

}
