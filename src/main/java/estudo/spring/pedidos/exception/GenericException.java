package estudo.spring.pedidos.exception;

import java.util.List;

public class GenericException extends RuntimeException {

    private String erro;
    private List<String> fields;

    public GenericException(){
        super();
    }

    public GenericException(String msg, String erro) {
        super(msg);
        this.erro = erro;
    }
    
    public GenericException(String msg, String erro, List<String> fields) {
        super(msg);
        this.erro = erro;
        this.fields = fields;
    }


    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }    
}
