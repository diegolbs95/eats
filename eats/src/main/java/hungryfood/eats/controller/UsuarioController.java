package hungryfood.eats.controller;

import hungryfood.eats.exception.NaoExisteCadastroException;
import hungryfood.eats.model.Usuario;
import hungryfood.eats.servico.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    public ResponseEntity<?> listar(){
        try {
            return ResponseEntity.ok(service.list());
        }catch (NaoExisteCadastroException e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable Long id){
        try {
            return ResponseEntity.ok(service.findById(id));
        }catch (NaoExisteCadastroException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> save (@RequestBody Usuario usuario){
        return ResponseEntity.ok(service.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id,@RequestBody Usuario usuario
    ){
        try {
            return ResponseEntity.ok(service.update(id, usuario));
        }catch (NaoExisteCadastroException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        try {
            var usuario = service.findById(id);
            service.delete(id);
           return ResponseEntity.ok(usuario);
        }catch (NaoExisteCadastroException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
