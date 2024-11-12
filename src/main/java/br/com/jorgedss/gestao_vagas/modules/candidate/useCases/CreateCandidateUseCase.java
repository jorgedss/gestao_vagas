package br.com.jorgedss.gestao_vagas.modules.candidate.useCases;

import br.com.jorgedss.gestao_vagas.exceptions.UserFoundException;
import br.com.jorgedss.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.jorgedss.gestao_vagas.modules.candidate.controllers.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;
    public CandidateEntity execute(CandidateEntity candidateEntity){
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(),candidateEntity.getEmail())
                .ifPresent((user)->{
                    throw new UserFoundException();
                });


        return this.candidateRepository.save(candidateEntity);
    }
}