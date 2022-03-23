package com.project.conquer.school.teacher.domain.service;

import com.project.conquer.school.teacher.domain.dto.ProfessorDTO;
import com.project.conquer.school.teacher.domain.model.GeneroEnum;
import com.project.conquer.school.teacher.domain.model.Professor;
import com.project.conquer.school.teacher.domain.repository.ProfessorRepository;
import com.project.conquer.school.teacher.domain.validator.EmailValidator;
import com.project.conquer.school.teacher.exception.CustomException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private static final Log LOG = LogFactory.getLog(ProfessorService.class);

    private final ProfessorRepository professorRepository;
    private final EmailValidator emailValidator;

    public ProfessorService(ProfessorRepository professorRepository, EmailValidator emailValidator) {
        this.professorRepository = professorRepository;
        this.emailValidator = emailValidator;
    }

    @Async
    public void cadastrar(ProfessorDTO professorDTO) {
        LOG.info("Cadastrando um novo professor nome: " + professorDTO.getNome());
        Professor novoProfessor = professorDTO.toObject();
        novoProfessor.setDataCriacao(LocalDateTime.now());
        novoProfessor.setIdade(calculaIdade(professorDTO.getDataNascimento()));
        novoProfessor.setGenero(GeneroEnum.valueOf(professorDTO.getGenero()));
        novoProfessor.setEmail(professorDTO.getEmail());
        salvar(novoProfessor);
    }

    @Async
    public void atualizar(Long id, ProfessorDTO professorDTO) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new CustomException("Professor não encontrado"));

        LOG.info("Atualizando o professor nome: " + professorDTO.getNome());
        professor.setDataAlteracao(LocalDateTime.now());
        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());
        professor.setGenero(GeneroEnum.valueOf(professorDTO.getGenero()));
        professor.setEmail(professorDTO.getEmail());
        salvar(professor);
    }

    public List<ProfessorDTO> getAllProfessores() {
        return professorRepository.findAll().stream().map(Professor::getDto).collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }

    public void validaEmail(ProfessorDTO professorDTO) throws CustomException {
        if(Objects.nonNull(professorDTO.getEmail())) {
            boolean validado = emailValidator.validaEmail(professorDTO.getEmail());
            if(!validado){
                throw new CustomException("Email no formato inválido!");
            }
        }
    }

    private Integer calculaIdade(LocalDate dataNascimento) {
        Integer anoAtual = LocalDate.now().getYear();
        Integer anoNascimento = dataNascimento.getYear();
        return anoAtual - anoNascimento;
    }

    private void salvar(Professor professor) {
        try {
            LOG.info("Salvando professor nome: " + professor.getNome());
            professorRepository.save(professor);
        } catch (Exception e) {
            LOG.error("Não foi possível salvar os dados do professor: " + e);
            throw new CustomException("Não foi possível salvar os dados do professor: " + e);
        }
    }
}
