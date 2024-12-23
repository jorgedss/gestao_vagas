package br.com.jorgedss.gestao_vagas.modules.company.useCases;

import br.com.jorgedss.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.jorgedss.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.jorgedss.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    private static final Logger logger = LoggerFactory.getLogger(AuthCompanyUseCase.class);

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) {
        var company = this.companyRepository
                .findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });

        boolean passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            logger.error("Invalid credentials for username: " + authCompanyDTO.getUsername());
            throw new BadCredentialsException("Invalid credentials");
        }


        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        String token = JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var roles = Arrays.asList("COMPANY");

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .access_token(token)
                        .expires_in(expiresIn.toEpochMilli())
                .roles(roles)
                .build();


        return authCompanyResponseDTO;
    }
}
