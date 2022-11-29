package hungryfood.eats.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public GrupoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long estadoId){
        this(String.format("Não existe um cadastro de Grupo com código %d", estadoId));
    }
}
