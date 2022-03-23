package com.project.conquer.school.teacher.domain.model;

import com.project.conquer.school.teacher.domain.dto.ProfessorDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

@Entity
@Table(name = "PROFESSOR")
public class Professor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Max(100)
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Max(11)
    @Column(name = "CPF", nullable = false, length = 11)
    private String cpf;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Max(1)
    @Column(name = "GENERO", nullable = false)
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IDADE")
    private Integer idade;

    @Column(name = "DATA_CRIACAO")
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ALTERACAO")
    private LocalDateTime dataAlteracao;

    public Professor() {
    }

    public Professor(Long id, String nome, String cpf, LocalDate dataNascimento, GeneroEnum genero, String email, Integer idade, LocalDateTime dataCriacao, LocalDateTime dataAlteracao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.email = email;
        this.idade = idade;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
    }

    public Professor(Long id, String nome, String cpf, LocalDate dataNascimento, GeneroEnum genero, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.email = email;
    }

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

    public GeneroEnum getGenero() {
        return genero;
    }

    public String getEmail() {
        return email;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfessorDTO getDto() {
        return new ProfessorDTO(getId(), getNome(), getCpf(), getDataNascimento(), getGenero().getTipo(), getEmail());
    }
}
