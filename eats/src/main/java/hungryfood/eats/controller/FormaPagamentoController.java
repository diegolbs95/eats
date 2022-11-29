package hungryfood.eats.controller;

import hungryfood.eats.model.FormaPagamento;
import hungryfood.eats.servico.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formasPagamentos")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService service;

    @GetMapping
    public List<FormaPagamento> listar(){
        return service.buscar();
    }
    @PostMapping
    public FormaPagamento adicionar (@RequestBody FormaPagamento formaPagamento){
        return service.adicionar(formaPagamento);
    }
}
