package hungryfood.eats.servico;

import hungryfood.eats.exception.NaoExisteCadastroException;
import hungryfood.eats.model.Permissao;
import hungryfood.eats.model.Usuario;
import hungryfood.eats.repository.PermissaoRepository;
import hungryfood.eats.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository repository;

    public List<Permissao> list (){
        if (repository.findAll().isEmpty()){
            throw new NaoExisteCadastroException("Não existe Usuario cadastrado");
        }
        return repository.findAll();
    }
    public Permissao findById (Long id){
        if (repository.findById(id).isEmpty()){
            throw new NaoExisteCadastroException(String.format("Usuario com id: %s não cadastrado", id));
        }
        return repository.findById(id).get();
    }
    public Permissao save (Permissao permissao){
        return repository.save(permissao);
    }
    public Permissao update (Long id, Permissao permissao){
        var permissaoAtual = findById(id);
        if (permissaoAtual == null){
            throw new NaoExisteCadastroException(String.format("Não existe Usuario cadastrado com id: %s", id));
        }
        BeanUtils.copyProperties(permissao, permissaoAtual, "id");
        return save(permissao);
    }
    public void delete (Long id){
        repository.deleteById(id);
    }
}
