package hungryfood.eats.servico;

import hungryfood.eats.exception.EntidadeEmUsoException;
import hungryfood.eats.exception.GrupoNaoEncontradoException;
import hungryfood.eats.exception.NaoExisteCadastroException;
import hungryfood.eats.model.Grupo;
import hungryfood.eats.repository.GrupoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository repository;

    public List<Grupo> listAll (){
        if (repository.findAll().isEmpty()) {
            throw new NaoExisteCadastroException("Nao existe nenhum Usuario cadastrado");
        }return repository.findAll();
    }

    public Grupo findById (Long id){
       if (repository.findById(id).isEmpty()){
           throw new NaoExisteCadastroException(String.format("Nao existe Usuario com id: %s cadastrado", id));
       }
        return repository.findById(id).get();
    }

    public Grupo save (Grupo grupo){
        return repository.save(grupo);
    }

    public Grupo update (Long id, Grupo grupo){
        try {
            var grupoAtual = findById(id);
            BeanUtils.copyProperties(grupo, grupoAtual, "id");
            return repository.save(grupoAtual);

        }catch (EmptyResultDataAccessException e){
            throw new GrupoNaoEncontradoException(id);
        }
    }
    public void delete (Long id){
        try {
            repository.findById(id);
        }catch (EmptyResultDataAccessException e){
            throw new GrupoNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Grupo com o codigo %d não pode ser excluida, pois esta em uso!", id));
        }
    }
}
