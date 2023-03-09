package br.com.os.dtos;

import br.com.os.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Integer id;
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;
    @CPF
    @NotBlank(message = "O campo CPF é obrigatório")
    private String cpf;
    @NotBlank
    private String telefone;

}
