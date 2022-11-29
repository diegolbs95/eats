package hungryfood.eats.exception;


public abstract class EntidadeNaoEncontradaException extends NegocioException{

    public EntidadeNaoEncontradaException (String mensagem){
        super(mensagem);
    }
}
