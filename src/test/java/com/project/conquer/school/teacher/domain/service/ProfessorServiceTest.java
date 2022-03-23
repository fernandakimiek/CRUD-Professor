package com.project.conquer.school.teacher.domain.service;

import com.project.conquer.school.teacher.domain.dto.ProfessorDTO;
import com.project.conquer.school.teacher.domain.model.GeneroEnum;
import com.project.conquer.school.teacher.domain.model.Professor;
import com.project.conquer.school.teacher.domain.repository.ProfessorRepository;
import com.project.conquer.school.teacher.domain.validator.EmailValidator;
import com.project.conquer.school.teacher.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateProfessor() {
        ProfessorDTO professorDTO = new ProfessorDTO(null, "Teste", "12127145812", LocalDate.of(1989, 05, 04), "M", "teste@teste.com");

        professorService.cadastrar(professorDTO);

        verify(professorRepository, only()).save(any());
    }

    @Test
    void shouldUpdateProfessor() {
        ProfessorDTO professorDTO = new ProfessorDTO(1L, "Teste", "12127145812", LocalDate.of(1989, 05, 04), "M", "teste@teste.com");
        Professor professor = new Professor(1L, "Teste Maria", "12345678910", LocalDate.of(1989, 05, 04), GeneroEnum.F, "teste@teste.com");
        when(professorRepository.findById(anyLong())).thenReturn(Optional.of(professor));

        professorService.atualizar(1L, professorDTO);

        verify(professorRepository, times(1)).save(professor);
    }

    @Test
    void shouldGetAllProfessores() {
        Professor professor1 = new Professor(1L, "Teste 1", "12345678912", LocalDate.of(1989, 05, 04), GeneroEnum.F, "teste@teste.com");
        Professor professor2 = new Professor(2L, "Teste 2", "12345678913", LocalDate.of(1989, 05, 04), GeneroEnum.M, "teste@teste.com");
        Professor professor3 = new Professor(3L, "Teste 3", "12345678914", LocalDate.of(1989, 05, 04), GeneroEnum.O, "teste@teste.com");
        List<Professor> listaProfessores = new ArrayList<>();
        listaProfessores.add(professor1);
        listaProfessores.add(professor2);
        listaProfessores.add(professor3);

        when(professorRepository.findAll()).thenReturn(listaProfessores);

        professorService.getAllProfessores();

        assertEquals(3, listaProfessores.size());
    }

    @Test
    void shouldDeleteProfessor() {
        professorService.deleteById(1L);

        verify(professorRepository, only()).deleteById(1L);
    }

    @Test
    void shouldValidaEmail() {
        ProfessorDTO professorDTO = new ProfessorDTO(1L, "Teste", "12127145812", LocalDate.of(1989, 05, 04), "M", "teste@teste.com");
        when(emailValidator.validaEmail(professorDTO.getEmail())).thenReturn(true);

        verify(emailValidator, only()).validaEmail(any());
    }

    @Test
    void shouldThrowExceptionValidaEmail() {
        ProfessorDTO professorDTO = new ProfessorDTO(1L, "Teste", "12127145812", LocalDate.of(1989, 05, 04), "M", "teste.com");
        when(emailValidator.validaEmail(professorDTO.getEmail())).thenReturn(false);

        Assertions.assertThrows(CustomException.class, () -> professorService.validaEmail(professorDTO));
    }
}