package hungryfood.eats.exception;

public class EstadoNaoEncontradoException extends NegocioException{

    public EstadoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long estadoId){
        this(String.format("Não existe um cadastro de Estado com código %d", estadoId));
    }
}
