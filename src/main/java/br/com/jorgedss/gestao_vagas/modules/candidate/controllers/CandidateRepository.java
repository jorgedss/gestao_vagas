package br.com.jorgedss.gestao_vagas.modules.candidate.controllers;

import br.com.jorgedss.gestao_vagas.modules.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
}
