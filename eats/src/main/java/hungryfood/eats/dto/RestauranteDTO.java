package hungryfood.eats.dto;

import hungryfood.eats.model.Cozinha;
import hungryfood.eats.model.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDTO {

    private Long id;
    private String nome;
    private BigDecimal taxaFrente;

    public RestauranteDTO (Restaurante restaurante){
        id = restaurante.getId();
        nome = restaurante.getNome();
        taxaFrente = restaurante.getTaxaFrente();
    }

}
