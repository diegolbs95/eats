package hungryfood.eats.servico;

import hungryfood.eats.exception.EntidadeEmUsoException;
import hungryfood.eats.exception.EstadoNaoEncontradoException;
import hungryfood.eats.exception.NegocioException;
import hungryfood.eats.model.Cidade;
import hungryfood.eats.model.Estado;
import hungryfood.eats.repository.CidadeRepository;
import hungryfood.eats.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    private static final String MSG_ESTADO_EM_USO = "Estado com id %s esta em uso";

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    public Estado adicionar (Estado estado){

        return estadoRepository.save(estado);
    }

    public Estado atualziar (Long id, Estado estado){

       var estadoAtual = buscarOuFalhar(id);
       BeanUtils.copyProperties(estado, estadoAtual, "id");
       return estadoRepository.save(estadoAtual);
    }
    public void deletar(Long id){
        try{
            estadoRepository.deleteById(buscarOuFalhar(id).getId());
       }
       catch (DataIntegrityViolationException e){
           throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
       }
    }

    public Estado buscarOuFalhar(Long id){
        return estadoRepository.findById(id).orElseThrow(
                () -> new EstadoNaoEncontradoException(id));
    }
}
