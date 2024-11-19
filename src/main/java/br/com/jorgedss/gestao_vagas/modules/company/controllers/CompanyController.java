package br.com.jorgedss.gestao_vagas.modules.company.controllers;

import br.com.jorgedss.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.jorgedss.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.jorgedss.gestao_vagas.modules.company.useCases.CreateCompanyUseCases;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCases createCompanyUseCases;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity){
        try {
            var result = this.createCompanyUseCases.execute(companyEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
