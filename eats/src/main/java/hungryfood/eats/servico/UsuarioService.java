package hungryfood.eats.servico;

import hungryfood.eats.exception.NaoExisteCadastroException;
import hungryfood.eats.model.Usuario;
import hungryfood.eats.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> list (){
        if (repository.findAll().isEmpty()){
            throw new NaoExisteCadastroException("Não existe Usuario cadastrado");
        }
        return repository.findAll();
    }
    public Usuario findById (Long id){
        if (repository.findById(id).isEmpty()){
            throw new NaoExisteCadastroException(String.format("Usuario com id: %s não cadastrado", id));
        }
        return repository.findById(id).get();
    }
    public Usuario save (Usuario usuario){
        return repository.save(usuario);
    }
    public Usuario update (Long id, Usuario usuario){
        var usuarioAtual = findById(id);
        if (usuarioAtual == null){
            throw new NaoExisteCadastroException(String.format("Não existe Usuario cadastrado com id: %s", id));
        }
        BeanUtils.copyProperties(usuario, usuarioAtual, "id");
        return save(usuarioAtual);
    }
    public void delete (Long id){
        repository.deleteById(id);
    }
}
