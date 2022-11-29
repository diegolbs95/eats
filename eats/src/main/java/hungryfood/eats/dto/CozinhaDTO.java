package hungryfood.eats.dto;

import hungryfood.eats.model.Cozinha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CozinhaDTO {

    private Long id;
    private String nome;

    public CozinhaDTO (Cozinha cozinha){
        id = cozinha.getId();
        nome = cozinha.getNome();
    }

}
