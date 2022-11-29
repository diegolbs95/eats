package hungryfood.eats.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemaType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negocio");

    private String titulo;
    private String uri;

    ProblemaType (String path, String titulo){
        this.uri = "https://hungryfood.eats.com.br"+path;
        this.titulo = titulo;
    }
}
