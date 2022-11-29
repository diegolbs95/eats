package hungryfood.eats.exception;

public class NegocioException extends RuntimeException{

    public NegocioException (String e){
        super(e);
    }

    public NegocioException (String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
