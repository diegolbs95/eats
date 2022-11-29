package hungryfood.eats.servico;

import hungryfood.eats.model.FormaPagamento;
import hungryfood.eats.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository repository;

    public List<FormaPagamento> buscar (){
        return repository.findAll();
    }
    public FormaPagamento adicionar (FormaPagamento formaPagamento){
        var fmPagamento = repository.save(formaPagamento);
        return fmPagamento;
    }
}
