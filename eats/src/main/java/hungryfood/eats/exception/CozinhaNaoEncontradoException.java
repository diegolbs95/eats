package hungryfood.eats.exception;

public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException{

    public CozinhaNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public CozinhaNaoEncontradoException(Long estadoId){
        this(String.format("Não existe um cadastro de Cozinha com código %d", estadoId));
    }
}
