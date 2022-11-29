package hungryfood.eats.exception;

public class CidadeNaoEncontradoException extends NegocioException{

    public CidadeNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public CidadeNaoEncontradoException(Long estadoId){
        this(String.format("Não existe um cadastro de Cidade com código %d", estadoId));
    }
}
