package br.com.jorgedss.gestao_vagas.modules.candidate.controllers;

import br.com.jorgedss.gestao_vagas.exceptions.UserFoundException;
import br.com.jorgedss.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.jorgedss.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.jorgedss.gestao_vagas.modules.candidate.CandidateEntity;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {


    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity){
       try{
           var result =  this.createCandidateUseCase.execute(candidateEntity);
           return ResponseEntity.ok().body(result);
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @GetMapping("/")
    public ResponseEntity<Object> get(HttpServletRequest request){
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    
}
