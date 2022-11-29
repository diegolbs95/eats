package hungryfood.eats.controller;

import hungryfood.eats.model.Restaurante;
import hungryfood.eats.repository.CozinhaRepository;
import hungryfood.eats.repository.RestauranteRepository;
import hungryfood.eats.servico.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private RestauranteService service;

    @GetMapping
    public List<Restaurante> listar (){
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Restaurante buscar (@PathVariable Long id){
        return service.buscarOuFalhar(id);
    }

    @PostMapping
    public Restaurante adicionar (@RequestBody Restaurante restaurante){
        return service.adicionar(restaurante);
    }

    @PutMapping("/{id}")
    public Restaurante atualizar (@PathVariable Long id, @RequestBody @Validated Restaurante restaurante){
        return service.atualizar(id, restaurante);
    }

    @DeleteMapping("/{id}")
    public void deletar (@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/consultar-por-nome")
    public List<Restaurante> consultaNome (String nome, Long cozinhaId){
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }
}
