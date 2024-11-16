package br.com.jorgedss.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// cria um construtor com todos os atributos
public class AuthCompanyDTO {

    private  String password;
    private  String username;

}
