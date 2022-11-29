package hungryfood.eats.exceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import hungryfood.eats.exception.EntidadeEmUsoException;
import hungryfood.eats.exception.EntidadeNaoEncontradaException;
import hungryfood.eats.exception.EstadoNaoEncontradoException;
import hungryfood.eats.exception.NegocioException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandle extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCausa = ExceptionUtils.getRootCause(ex);

        if (rootCausa instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException)rootCausa, headers, status, request);
        }
        ProblemaType problemaType = ProblemaType.MENSAGEM_INCOMPREENSIVEL;
        String detalhe = "O corpo da requisiçao esta invalido. Verifique erro de sintaxe.";

        Problema problema = criaProblemaBuilder(status, problemaType, detalhe).build();
        return  handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ProblemaType problemaType = ProblemaType.MENSAGEM_INCOMPREENSIVEL;
        String detalhe = String.format("A propriedade '%s' recebeu o valor '%s'," +
                "que é de um tipo invalido. Corrija e informe um valor compativel com o tipo %s.",path, ex.getValue(), ex.getTargetType().getSimpleName());
        Problema problema = criaProblemaBuilder(status, problemaType, detalhe).build();


        return handleExceptionInternal(ex, problema, headers, status, request);

    }


    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrataException (
            EstadoNaoEncontradoException e, WebRequest request){

            HttpStatus status = HttpStatus.NOT_FOUND;
            var problemaType = ProblemaType.ENTIDADE_NAO_ENCONTRADA;
            String detalhe = e.getMessage();

            var problema = criaProblemaBuilder(status, problemaType, detalhe).build();

        return handleExceptionInternal(e, problema,new HttpHeaders(), status,request);
    }
    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException (
            EstadoNaoEncontradoException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(),new HttpHeaders(), HttpStatus.CONFLICT,request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException (NegocioException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(),new HttpHeaders(), HttpStatus.BAD_REQUEST,request);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleExceptionInternal(@NotNull Exception ex, Object body, @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {
        if (body == null){
            body = Problema.builder()
                    .titulo(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        }else if (body instanceof String){
            body = Problema.builder()
                    .titulo((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema.ProblemaBuilder criaProblemaBuilder (HttpStatus status, ProblemaType problemaType, String detalhe){

        return Problema.builder()
                .status(status.value())
                .tipo(problemaType.getUri())
                .titulo(problemaType.getTitulo())
                .detalhe(detalhe);
    }
}
