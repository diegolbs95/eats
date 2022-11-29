package hungryfood.eats.controller;

import hungryfood.eats.exception.EntidadeEmUsoException;
import hungryfood.eats.exception.EntidadeNaoEncontradaException;
import hungryfood.eats.exception.EstadoNaoEncontradoException;
import hungryfood.eats.exception.NegocioException;
import hungryfood.eats.model.Estado;
import hungryfood.eats.repository.EstadoRepository;
import hungryfood.eats.servico.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    public EstadoService estadoService;

    @Autowired
    public EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> lista (){
        return estadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estado estadoId (@PathVariable Long id){

        return estadoService.buscarOuFalhar(id);
    }

    @PostMapping
    public Estado adicionar (@RequestBody Estado estado){
        return estadoService.adicionar(estado);
    }
    @PutMapping("/{id}")
    public Estado atualziar (@PathVariable Long id,@RequestBody Estado estado){
        return estadoService.atualziar(id, estado);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar (@PathVariable Long id) {
        estadoService.deletar(id);

    }
}
