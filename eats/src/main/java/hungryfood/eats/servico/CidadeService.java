package hungryfood.eats.servico;

import hungryfood.eats.exception.*;
import hungryfood.eats.model.Cidade;
import hungryfood.eats.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoService estadoService;

    public Cidade adicionar (Cidade cidade){
            var estado = estadoService.buscarOuFalhar(cidade.getEstado().getId());
            cidade.setEstado(estado);
            return cidadeRepository.save(cidade);

    }

    public Cidade atualizar (Long id, Cidade cidade){

            var estadoId = estadoService.buscarOuFalhar(cidade.getEstado().getId());
            var cidadeAtual = buscarOuFalhar(id);
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
            cidadeAtual.setEstado(estadoId);
            return cidadeRepository.save(cidadeAtual);

    }
    public void deletar (Long id){
      try {
          buscarOuFalhar(id);
          cidadeRepository.deleteById(id);
      }catch (DataIntegrityViolationException e){
          throw new EntidadeEmUsoException(e.getMessage());
      }

    }

    public Cidade buscarOuFalhar (Long id){
        return cidadeRepository.findById(id).orElseThrow(
                () -> new CidadeNaoEncontradoException(id));
    }
}

