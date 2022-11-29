package hungryfood.eats.controller;

import hungryfood.eats.dto.CozinhaDTO;
import hungryfood.eats.exception.EntidadeEmUsoException;
import hungryfood.eats.exception.EntidadeNaoEncontradaException;
import hungryfood.eats.model.Cozinha;
import hungryfood.eats.repository.CozinhaRepository;
import hungryfood.eats.servico.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CozinhaService service;

    @GetMapping
    public List<Cozinha> listar (){
         return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cozinha buscarId (@PathVariable Long id){
        return service.buscarFalhar(id);
    }

    @PostMapping
    public Cozinha adicionar (@RequestBody Cozinha cozinha){
        return service.adicionar(cozinha);
    }

    @PutMapping("/{id}")
    public Cozinha atualizar (@PathVariable Long id, @RequestBody @Validated Cozinha cozinha){
        var cozinhaAtual = service.buscarFalhar(id);

            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            return service.adicionar(cozinhaAtual);

    }

    @DeleteMapping("/{id}")
    public void deletar (@PathVariable Long id) {
        service.delete(id);
    }
}
