package hungryfood.eats.controller;

import hungryfood.eats.exception.EntidadeEmUsoException;
import hungryfood.eats.exception.EntidadeNaoEncontradaException;
import hungryfood.eats.exception.EstadoNaoEncontradoException;
import hungryfood.eats.exception.NegocioException;
import hungryfood.eats.exceptionHandler.Problema;
import hungryfood.eats.model.Cidade;
import hungryfood.eats.repository.CidadeRepository;
import hungryfood.eats.servico.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> listAll (){
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cidade findById (@PathVariable Long id) {
        return cidadeService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade salvar (@RequestBody Cidade cidade){
        try {
            return cidadeService.adicionar(cidade);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }
    @PutMapping("/{id}")
    public Cidade atualizar (@PathVariable Long id,@RequestBody Cidade cidade) {
        try {
            return cidadeService.atualizar(id, cidade);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar (@PathVariable Long id) throws Exception {
       try {
           cidadeService.deletar(id);
       }catch (EntidadeEmUsoException e){
           throw new NegocioException(e.getMessage(), e);
       }
    }
}
