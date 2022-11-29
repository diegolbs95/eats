package hungryfood.eats.servico;

import hungryfood.eats.exception.CozinhaNaoEncontradoException;
import hungryfood.eats.exception.EntidadeEmUsoException;
import hungryfood.eats.model.Cozinha;
import hungryfood.eats.repository.CozinhaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CozinhaService {

    private static final String MSG_COZINHA_NAO_ENCONTRADA = "Cozinha com id: %s não encontrada";
    private static final String MSG_COZINHA_EM_USO = "Cozinha com o codigo %d não pode ser excluida, pois esta em uso!";

    @Autowired
    private CozinhaRepository repository;

    public List<Cozinha> findAll (){
        return repository.findAll();
    }

    public Cozinha findById (Long id){
        return buscarFalhar(id);
    }

    public Cozinha adicionar (Cozinha cozinha){
        var cozi = repository.save(cozinha);
        log.info("Cozinha com o nome "+ cozi.getNome()+" foi adicionada");
        return cozi;
    }

    public void  delete (Long id){
        try {
            repository.deleteById(id);
        }catch (NoSuchElementException e){
            throw new CozinhaNaoEncontradoException(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
        }
    }

    public Cozinha buscarFalhar (Long id){
        return repository.findById(id).orElseThrow(
                () -> new CozinhaNaoEncontradoException(id)
        );
    }
}
