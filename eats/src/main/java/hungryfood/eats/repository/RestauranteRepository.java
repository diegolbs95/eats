package hungryfood.eats.repository;

import hungryfood.eats.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    //@Query("from Restaurante where nome like %:nome% and cozinha.id= :id")//Query nomeada usando ->JPQL
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

    //List<Restaurante> findBytaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
}
