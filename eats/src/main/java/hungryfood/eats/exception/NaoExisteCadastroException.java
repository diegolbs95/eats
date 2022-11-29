package hungryfood.eats.exception;

public class NaoExisteCadastroException extends NegocioException{

    public NaoExisteCadastroException (String msg){
        super(msg);
    }
}
