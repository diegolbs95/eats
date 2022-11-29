package hungryfood.eats.controller;

import hungryfood.eats.exception.NaoExisteCadastroException;
import hungryfood.eats.model.Grupo;
import hungryfood.eats.servico.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService service;

    @GetMapping
    public ResponseEntity<?> listAll (){
      try {
          return ResponseEntity.ok(service.listAll());
      }catch (NaoExisteCadastroException e){
          return ResponseEntity.ok(e.getMessage());
      }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable Long id){
        try {
            return ResponseEntity.ok(service.findById(id));
        }catch (NaoExisteCadastroException e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Grupo> save (@RequestBody Grupo grupo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(grupo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> update (@PathVariable Long id, @RequestBody Grupo grupo){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, grupo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.badRequest().build();
    }

}
