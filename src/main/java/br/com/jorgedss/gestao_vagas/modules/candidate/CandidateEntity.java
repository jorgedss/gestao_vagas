package br.com.jorgedss.gestao_vagas.modules.candidate;


import java.util.UUID;
import jakarta.validation.constraints.Email;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data


public class CandidateEntity {
    
    private UUID id;
    private String name;

    @NotBlank()
    @Pattern(regexp = "\\s+", message = "O campo [username] não deve conter espaço")
    private String username;

    @Email(message = "O campo [email] deve conter um email válido")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve ter entre 10 e 100 caracteres")
    private String password;
    private String description;
    private String curriculum;

    
}
