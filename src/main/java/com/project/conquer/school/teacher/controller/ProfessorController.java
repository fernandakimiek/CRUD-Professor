package com.project.conquer.school.teacher.controller;

import com.project.conquer.school.teacher.domain.dto.ProfessorDTO;
import com.project.conquer.school.teacher.domain.model.Professor;
import com.project.conquer.school.teacher.domain.service.ProfessorService;
import com.project.conquer.school.teacher.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastrar(@RequestBody ProfessorDTO professorDTO) {
        try{
            if(Objects.isNull(professorDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if(Objects.nonNull(professorDTO.getEmail())) professorService.validaEmail(professorDTO);
            professorService.cadastrar(professorDTO);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        }

        return ResponseEntity.ok().body("Registro salvo com sucesso");
    }

    @PutMapping("/atualizacao/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        if(Objects.isNull(professorDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(Objects.nonNull(professorDTO.getEmail())) professorService.validaEmail(professorDTO);
        professorService.atualizar(id, professorDTO);

        return new ResponseEntity<>("Registro atualizado com sucesso", HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProfessorDTO>> listagemProfessores() {
        List<ProfessorDTO> list = professorService.getAllProfessores();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/exclusao/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        if(Objects.isNull(id)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        professorService.deleteById(id);

        return new ResponseEntity<>("Registro deletado com sucesso", HttpStatus.OK);
    }
}

