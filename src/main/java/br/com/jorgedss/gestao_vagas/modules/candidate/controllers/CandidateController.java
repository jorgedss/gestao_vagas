package br.com.jorgedss.gestao_vagas.modules.candidate.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jorgedss.gestao_vagas.modules.candidate.CandidateEntity;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    public void create(@Valid @RequestBody CandidateEntity candidateEntity){
        System.out.println("Candidato");
        System.out.println(candidateEntity.getName());
    }
    
}
