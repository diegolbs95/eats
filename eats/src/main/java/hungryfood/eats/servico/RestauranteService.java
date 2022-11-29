package hungryfood.eats.servico;

import hungryfood.eats.exception.*;
import hungryfood.eats.model.Restaurante;
import hungryfood.eats.repository.CozinhaRepository;
import hungryfood.eats.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private static final String MSG_COZINHA_ESTA_EM_USO = "Cozinha com id: %s não pode ser excluida, pois esta em uso!";

    @Autowired
    private RestauranteRepository repository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante adicionar (Restaurante restaurante){
        var cozinhaId = restaurante.getCozinha().getId();

        var cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(()-> new CozinhaNaoEncontradoException(cozinhaId));

        restaurante.setCozinha(cozinha);
        return repository.save(restaurante);

    }
    public Restaurante atualizar (Long id,Restaurante restaurante){
        var restauranteAtual = buscarOuFalhar(id);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id","formaPagamentos","dataCadastro");//EXETO "ID" e "formaPagamentos", "dataCadastro"
        return repository.save(restauranteAtual);
    }

    public void delete (Long id)  {
        try {
        repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new RestauranteNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(MSG_COZINHA_ESTA_EM_USO, id));
        }
    }

    public Restaurante buscarOuFalhar (Long id){
        return repository.findById(id).orElseThrow(
                () -> new RestauranteNaoEncontradoException(id));
    }
}
